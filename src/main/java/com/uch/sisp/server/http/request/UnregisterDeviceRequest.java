package com.uch.sisp.server.http.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter @Builder
public class UnregisterDeviceRequest implements Serializable
{
	int id;
}
