package com.paytm.coordination.framework.model.v1.req;

import java.io.Serializable;

public class LockRequest implements Serializable {
	 
	private RequestURI requestURI;
	private String lockPathID;
	
	public LockRequest() {	
	}
	
	public LockRequest(RequestURI reqURI, String lockPathID) {
		super();
		this.requestURI = reqURI;
		this.lockPathID = lockPathID;
	}
	
	public String getLockPathID() {
		return lockPathID;
	}

	public void setLockPathID(String lockPathID) {
		this.lockPathID = lockPathID;
	}

	public RequestURI getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(RequestURI reqURI) {
		this.requestURI = reqURI;
	}

}
