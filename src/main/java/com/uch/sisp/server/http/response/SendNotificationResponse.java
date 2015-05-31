package com.uch.sisp.server.http.response;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SendNotificationResponse implements Serializable
{
	private List<String> unsentDestinationUsers;
	private List<String> unsuscribedDestinationUsers;

	public List<String> getUnsentDestinationUsers()
	{
		return unsentDestinationUsers;
	}

	public List<String> getUnsuscribedDestinationUsers()
	{
		return unsuscribedDestinationUsers;
	}

	public void setUnsentDestinationUsers(List<String> unsentDestinationUsers)
	{
		this.unsentDestinationUsers = unsentDestinationUsers;
	}

	public void setUnsuscribedDestinationUsers(List<String> unsuscribedDestinationUsers)
	{
		this.unsuscribedDestinationUsers = unsuscribedDestinationUsers;
	}

}
