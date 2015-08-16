package com.uch.sisp.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.GCMConnector;
import com.uch.sisp.server.gcm.exception.GCMSendingMessageException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.gcm.helper.GCMHelper;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendPanicNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.service.GoogleNotificationService;

@Service
public class GoogleNotificationServiceImpl implements GoogleNotificationService {
	@Autowired
	private UserDAO userDao;

	@Autowired
	private GCMHelper gcmHelper;

	@Autowired
	private GCMConnector gcmConnector;

	@Override
	public RegisterDeviceResponse registerGCMDevice(RegisterDeviceRequest request) throws EntityNotFoundException {

		RegisterDeviceResponse response = null;
		User user = !StringUtils.isBlank(request.getEmail()) ? userDao.getUserByEmail(request.getEmail()) : userDao.getById(request
				.getId());

		user.setRegistrationId(request.getRegisterId());

		user = (User) userDao.updateAndReturn(user);

		response = new RegisterDeviceResponse();
		response.setRegisterId(user.getRegistrationId());
		response.setEmail(user.getUserEmail());
		response.setId(user.getId());
		return response;
	}

	@Override
	public User unregisterGCMDevice(int deviceId) throws EntityNotFoundException {
		User user = userDao.getById(deviceId);
		return updateRegistrationId(user, null);
	}

	@Override
	public User unregisterGCMDevice(String registrationIdToRemove) throws EntityNotFoundException {
		User user = userDao.getUserByGCMRegistrationId(registrationIdToRemove);
		return updateRegistrationId(user, null);
	}

	@Override
	public User replaceGCMRegistrationIdByCanonicalId(String regId, String canonicalRegId) throws EntityNotFoundException {
		User user = userDao.getUserByGCMRegistrationId(regId);
		return updateRegistrationId(user, canonicalRegId);
	}

	private User updateRegistrationId(User user, String registrationId) {
		user.setRegistrationId(registrationId);
		return (User) userDao.saveOrUpdateAndReturn(user);
	}

	@Override
	public void sendPanicNotification(SendPanicNotificationRequest request) throws GCMServiceException {
		try {
			// busca al usuario solicitante y sus suscriptos
			User user = userDao.getById(request.getId());
			List<String> devices = new ArrayList<String>();
			for (User father : user.getFathers()) {
				devices.add(father.getRegistrationId());
			}
			Message message = gcmHelper.buildPanicNotification(request.getPosition(), user);
			// envia los mensajes
			gcmConnector.sendAllMessages(devices, message);

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			throw new GCMServiceException(HttpStatus.NOT_FOUND);
		} catch (GCMSendingMessageException e) {
			e.printStackTrace();
			throw new GCMServiceException(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
