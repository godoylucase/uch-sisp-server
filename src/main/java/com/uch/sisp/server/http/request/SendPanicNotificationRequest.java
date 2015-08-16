package com.uch.sisp.server.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.uch.sisp.server.http.domain.GPSPosition;

@SuppressWarnings("serial")
@Getter @Setter
public class SendPanicNotificationRequest implements Serializable {
	private int id;
	private GPSPosition position;
}
