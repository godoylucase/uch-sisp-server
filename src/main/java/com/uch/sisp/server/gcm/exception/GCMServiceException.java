package com.uch.sisp.server.gcm.exception;

import org.springframework.http.HttpStatus;

public class GCMServiceException extends Exception
{
	private static final long serialVersionUID = 1L;
	HttpStatus httpStatus;
	
	public GCMServiceException(HttpStatus httpStatus)
	{
		super();
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}
}
