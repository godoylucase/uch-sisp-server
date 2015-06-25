package com.uch.sisp.server.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
public class UnregisterDeviceRequest implements Serializable
{
	int id;
}
