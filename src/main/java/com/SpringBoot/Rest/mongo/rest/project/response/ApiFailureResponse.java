package com.SpringBoot.Rest.mongo.rest.project.response;

public class ApiFailureResponse {
	
	private String status;
    private String error;
    
    public ApiFailureResponse() {
	}
    
    
	public ApiFailureResponse(String status, String error) {
		super();
		this.status = status;
		this.error = error;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getError() {
		return error;
	}
	
	
	public void setError(String error) {
		this.error = error;
	}
	
	
	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", error=" + error + "]";
	}
}
