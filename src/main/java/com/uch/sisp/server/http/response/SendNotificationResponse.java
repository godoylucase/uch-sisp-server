package com.uch.sisp.server.http.response;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
public class SendNotificationResponse implements Serializable
{
	private List<String> unsentDestinationUsers;
	private List<String> unsuscribedDestinationUsers;

}
