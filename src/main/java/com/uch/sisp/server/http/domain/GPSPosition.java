package com.uch.sisp.server.http.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class GPSPosition implements Serializable
{
	private BigDecimal latitude;
	private BigDecimal altitude;

	public BigDecimal getLatitude()
	{
		return latitude;
	}

	public BigDecimal getAltitude()
	{
		return altitude;
	}

	public void setLatitude(BigDecimal latitude)
	{
		this.latitude = latitude;
	}

	public void setAltitude(BigDecimal altitude)
	{
		this.altitude = altitude;
	}

}
