package com.ti.soap.documentsuploader.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://www.documentsuploader.soap.ti.com/documents}003_USER_NOT_FOUND")
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8467680953780714383L;

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
