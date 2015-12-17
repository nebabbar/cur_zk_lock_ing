package com.paytm.coordination.framework.model.v1.req;

import java.util.Map;

public class RequestURI {
	
		
	private String reqURL ;
	private String reqMethod ;
	private Map<String,String> reqHeader;
	private String reqPayload;
   
	public RequestURI(String reqURL, String reqMethod, Map<String, String> map, String reqPayload) {
		super();
		this.reqURL = reqURL;
		this.reqMethod = reqMethod;
		this.reqHeader = map;
		this.reqPayload = reqPayload;
	}
   
	public RequestURI() {
		// TODO Auto-generated constructor stub
	}
	
	public String getReqPayload() {
		return reqPayload;
	}
	public void setReqPayload(String reqPayload) {
		this.reqPayload = reqPayload;
	}
	public String getReqURL() {
		return reqURL;
	}
	public void setReqURL(String reqURL) {
		this.reqURL = reqURL;
	}
	public String getReqMethod() {
		return reqMethod;
	}
	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}
	public Map<String, String> getReqHeader() {
		return reqHeader;
	}
	public void setReqHeader(Map<String, String> reqHeader) {
		this.reqHeader = reqHeader;
	}
}
