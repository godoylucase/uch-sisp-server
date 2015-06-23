package com.uch.sisp.server.http.response;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.RegisterDeviceRequest.RegisterDeviceRequestBuilder;

@SuppressWarnings("serial")
@Getter @Setter @Builder
public class SendNotificationResponse implements Serializable
{
	private List<String> unsentDestinationUsers;
	private List<String> unsuscribedDestinationUsers;

}
