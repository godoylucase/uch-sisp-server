package com.uch.sisp.server.http.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter @Builder
public class RegisterDeviceResponse implements Serializable
{
	private int id;
	private String registerId;
}
