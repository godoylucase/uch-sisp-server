package com.uch.sisp.server.http.request;

import java.io.Serializable;
import java.util.List;

import com.uch.sisp.server.http.domain.GPSPosition;
import com.uch.sisp.server.http.domain.NotificationTag;

@SuppressWarnings("serial")
public class SendNotificationRequest implements Serializable
{
	private List<String> destinationEmails;
	private String originUserEmail;
	private GPSPosition position;
	private NotificationTag tag;

	public List<String> getDestinationEmails()
	{
		return destinationEmails;
	}

	public String getOriginUserEmail()
	{
		return originUserEmail;
	}

	public GPSPosition getPosition()
	{
		return position;
	}

	public NotificationTag getTag()
	{
		return tag;
	}

	public void setDestinationEmails(List<String> destinationEmails)
	{
		this.destinationEmails = destinationEmails;
	}

	public void setOriginUserEmail(String originUserEmail)
	{
		this.originUserEmail = originUserEmail;
	}

	public void setPosition(GPSPosition position)
	{
		this.position = position;
	}

	public void setTag(NotificationTag tag)
	{
		this.tag = tag;
	}

}
