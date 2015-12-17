package com.paytm.coordination.framework.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.recipes.locks.InterProcessMutex;
import com.netflix.curator.retry.ExponentialBackoffRetry;
import com.paytm.coordination.framework.model.v1.req.LockRequest;
import com.paytm.coordination.framework.model.v1.res.LockResponse;

public class ResourceLockingService {
	
	private InterProcessMutex lock;
	private com.paytm.coordination.framework.model.v1.req.LockRequest lockReq;
	
	public ResourceLockingService(LockRequest lockReq) {
		super();
		this.lockReq = lockReq;
	}
	
	public LockResponse dowork() throws Exception{
    	
    	LockResponse response;
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        try{
	        client.start();
	        lock = new InterProcessMutex(client, "/"+lockReq.getLockPathID());
	        
	        if ( !lock.acquire(60, TimeUnit.SECONDS) )
	        {
	            throw new IllegalStateException(" could not acquire the lock");
	        }
	        try
	        {
	            System.out.println("lock acquired");
	            System.out.println("working(sleeping)...");
	            response = sendGetRequest();
	            Thread.sleep(12000);
	            System.out.println("awake");
	        }
	        finally
	        {
	            System.out.println("releasing the lock");
	            lock.release(); // always release the lock in a finally block
	            System.out.println("lock released");
	        }
        }
        finally
        {
        	IOUtils.closeQuietly(client);
        }
        return response;
    }

	private LockResponse sendGetRequest() throws IOException {

		String url = lockReq.getRequestURI().getReqURL();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod(lockReq.getRequestURI().getReqMethod());
		
		Map<String,String> map = lockReq.getRequestURI().getReqHeader();
		
		for (Map.Entry<String, String> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    con.setRequestProperty(entry.getKey(), entry.getValue());
		}

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		LockResponse lockResponse = new LockResponse(response.toString());
		
		return lockResponse;
	}
}
