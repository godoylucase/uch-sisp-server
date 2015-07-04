package com.uch.sisp.server.http.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
public class RegisterDeviceResponse implements Serializable
{
	private int id;
	private String email;
	private String registerId;
}
