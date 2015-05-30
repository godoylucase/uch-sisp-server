package com.uch.sisp.server.http.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegisterDeviceResponse implements Serializable
{
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
