package com.SpringBoot.Rest.mongo.rest.project.responseHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;

import com.SpringBoot.Rest.mongo.rest.project.response.ApiFailureResponse;
import com.SpringBoot.Rest.mongo.rest.project.response.ApiSuccessResponse;

@Component
public class ResponseHandler {

	private static final Logger logger = LogManager.getLogger(ResponseHandler.class);

	public ApiSuccessResponse returnSuccessResponse(String status) {
		logger.info("Succes Response status is " + status);
		return new ApiSuccessResponse(status);
	}

	public ApiFailureResponse returnFailureResponse(String status, String error) {
		logger.info("Failure Response status " + status + " error is " + error);
		return new ApiFailureResponse(status, error);
	}
	
}
