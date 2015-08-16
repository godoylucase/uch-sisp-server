package com.uch.sisp.server.http.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
public class GPSPosition implements Serializable
{
	private BigDecimal latitude;
	private BigDecimal longitude;
}
