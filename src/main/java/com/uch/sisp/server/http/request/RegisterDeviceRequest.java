package com.uch.sisp.server.http.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegisterDeviceRequest implements Serializable
{
	private int id;
	private String registerId;

	public int getId()
	{
		return id;
	}

	public String getRegisterId()
	{
		return registerId;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setRegisterId(String registerId)
	{
		this.registerId = registerId;
	}
}
