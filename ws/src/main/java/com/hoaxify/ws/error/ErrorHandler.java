package com.hoaxify.ws.error;

import java.util.Map;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class ErrorHandler implements ErrorController{
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	@RequestMapping("/error")
	ApiError handleError(WebRequest webRequest) {
	 Map<String,Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, true);
	 String message = (String)attributes.get("message");
	 String path = (String)attributes.get("path");
	 int status = (Integer)attributes.get("status");
	 return new ApiError(status, message, path);
	}
	
	
}
