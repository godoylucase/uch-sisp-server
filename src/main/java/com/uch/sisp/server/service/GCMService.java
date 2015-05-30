package com.uch.sisp.server.service;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;

public interface GCMService
{
	public void registerDevice(RegisterDeviceRequest device) throws EntityNotFoundException;
}
