package com.uch.sisp.server.http.request;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SendNotificationRequest implements Serializable
{
	private String originId;
	private List<String> destinationIds;

	public String getOriginId()
	{
		return originId;
	}

	public List<String> getDestinationIds()
	{
		return destinationIds;
	}

	public void setOriginId(String originId)
	{
		this.originId = originId;
	}

	public void setDestinationIds(List<String> destinationIds)
	{
		this.destinationIds = destinationIds;
	}

}
