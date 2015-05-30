package com.uch.sisp.server.http.response;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SendNotificationResponse implements Serializable
{
	private List<String> unsentDestinationIds;

	public List<String> getUnsentDestinationIds()
	{
		return unsentDestinationIds;
	}

	public void setUnsentDestinationIds(List<String> unsentDestinationIds)
	{
		this.unsentDestinationIds = unsentDestinationIds;
	}

}
