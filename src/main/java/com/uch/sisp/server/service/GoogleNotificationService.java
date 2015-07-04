package com.uch.sisp.server.service;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendNotificationResponse;

public interface GoogleNotificationService
{
	public RegisterDeviceResponse registerGCMDevice(RegisterDeviceRequest device) throws EntityNotFoundException;
	
	public void unregisterGCMDevice(int deviceId) throws EntityNotFoundException;
	
	public SendNotificationResponse sendNotification(SendNotificationRequest request) throws GCMServiceException;
}
