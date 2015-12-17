package com.paytm.coordination.framework.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.paytm.coordination.framework.services.ResourceLockingService;

@Path("/coordination-services")
public class CoordinationServices {

	@POST
	@Path("/locking/v1/acquire-lock/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response acquireLock(com.paytm.coordination.framework.model.v1.req.LockRequest req) {
		
		String output = ":" + req.getRequestURI().getReqURL() + "," + req.getLockPathID();
		System.out.println("output:"+output);		
		com.paytm.coordination.framework.model.v1.res.LockResponse res = null; 
		
		try{
			ResourceLockingService resourceLockObj = new ResourceLockingService(req);
			res = resourceLockObj.dowork();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return Response.status(201).entity(res).build();
		
	} 

}
