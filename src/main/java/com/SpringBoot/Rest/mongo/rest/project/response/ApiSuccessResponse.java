package com.SpringBoot.Rest.mongo.rest.project.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * POJO for response body on success
 * @author ugaumat
 *
 */
public class ApiSuccessResponse {
	
	static Logger logger = LogManager.getLogger(ApiSuccessResponse.class);
	
	private String status;
    
	public ApiSuccessResponse() {		
	}
	
	
	public ApiSuccessResponse(String status) {
		super();
		this.status = status;
	}


	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ApiSuccessResponse [status=" + status + "]";
	}
	
	
	
	
}