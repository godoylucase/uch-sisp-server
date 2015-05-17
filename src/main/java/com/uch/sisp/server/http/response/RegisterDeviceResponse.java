package com.uch.sisp.server.http.response;

import java.io.Serializable;

public class RegisterDeviceResponse implements Serializable
{
	private static final long serialVersionUID = -563379847361902895L;
	private String registerId;

	public String getRegisterId()
	{
		return registerId;
	}

	public void setRegisterId(String registerId)
	{
		this.registerId = registerId;
	}
}
