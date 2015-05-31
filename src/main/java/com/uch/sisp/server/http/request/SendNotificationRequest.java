package com.uch.sisp.server.http.request;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SendNotificationRequest implements Serializable
{
	private String originUserEmail;
	private List<String> destinationEmails;

	public String getOriginUserEmail()
	{
		return originUserEmail;
	}

	public List<String> getDestinationEmails()
	{
		return destinationEmails;
	}

	public void setOriginUserEmail(String originUserEmail)
	{
		this.originUserEmail = originUserEmail;
	}

	public void setDestinationEmails(List<String> destinationEmails)
	{
		this.destinationEmails = destinationEmails;
	}

}
