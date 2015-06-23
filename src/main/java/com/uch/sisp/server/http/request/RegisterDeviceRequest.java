package com.uch.sisp.server.http.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter @Builder
public class RegisterDeviceRequest implements Serializable
{
	private int id;
	private String email;
	private String registerId;

}
