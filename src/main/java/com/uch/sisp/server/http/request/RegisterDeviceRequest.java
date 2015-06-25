package com.uch.sisp.server.http.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter @NoArgsConstructor
public class RegisterDeviceRequest implements Serializable
{
	private Integer id = null;
	private String email = null;
	private String registerId = null;

}
