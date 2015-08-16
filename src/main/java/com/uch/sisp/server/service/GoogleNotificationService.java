package com.uch.sisp.server.service;

import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendPanicNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;

public interface GoogleNotificationService
{
	public RegisterDeviceResponse registerGCMDevice(RegisterDeviceRequest device)
			throws EntityNotFoundException;

	public User unregisterGCMDevice(int deviceId) throws EntityNotFoundException;

	public User replaceGCMRegistrationIdByCanonicalId(String regId, String canonicalRegId)
			throws EntityNotFoundException;

	public User unregisterGCMDevice(String registrationIdToRemove) throws EntityNotFoundException;

	public void sendPanicNotification(SendPanicNotificationRequest request) throws GCMServiceException;

}
