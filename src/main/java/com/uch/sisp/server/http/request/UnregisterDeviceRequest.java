package com.uch.sisp.server.http.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UnregisterDeviceRequest implements Serializable
{

	int id;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
}
