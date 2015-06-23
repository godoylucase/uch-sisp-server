package com.uch.sisp.server.http.request;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.uch.sisp.server.http.domain.GPSPosition;
import com.uch.sisp.server.http.domain.NotificationTag;
import com.uch.sisp.server.http.request.RegisterDeviceRequest.RegisterDeviceRequestBuilder;

@SuppressWarnings("serial")
@Getter @Setter @Builder
public class SendNotificationRequest implements Serializable
{
	private List<String> destinationEmails;
	private String originUserEmail;
	private GPSPosition position;
	private NotificationTag tag;
}
