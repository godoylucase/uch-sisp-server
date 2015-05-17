package com.uch.sisp.server.http.request;

import java.io.Serializable;

public class RegisterDeviceRequest implements Serializable
{
	private static final long serialVersionUID = -1162998335130086507L;
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
