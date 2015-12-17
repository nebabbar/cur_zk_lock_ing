package com.paytm.coordination.framework.model.v1.res;

public class LockResponse {
	private String resStr;

	public LockResponse() {
	}
	
	public LockResponse(String resStr) {
		super();
		this.resStr = resStr;
	}

	public String getResStr() {
		return resStr;
	}

	public void setResStr(String resStr) {
		this.resStr = resStr;
	}
	
}
