package com.uch.sisp.server.service;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.request.UnregisterDeviceRequest;
import com.uch.sisp.server.http.response.SendNotificationResponse;

public interface GCMService
{
	public void registerDevice(RegisterDeviceRequest device) throws EntityNotFoundException;
	
	public void unregisterDevice(UnregisterDeviceRequest device) throws EntityNotFoundException;
	
	public SendNotificationResponse sendNotification(SendNotificationRequest request);
}
