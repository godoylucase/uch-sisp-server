package com.uch.sisp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.GCMConnector;
import com.uch.sisp.server.gcm.exception.GCMNullDestinationException;
import com.uch.sisp.server.gcm.exception.GCMSendingMessageException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.gcm.helper.GCMHelper;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendNotificationResponse;
import com.uch.sisp.server.service.GoogleNotificationService;

@Service
public class GCMServiceImpl implements GoogleNotificationService
{
	@Autowired
	private UserDAO userDao;

	@Autowired
	private GCMHelper gcmHelper;

	@Autowired
	private GCMConnector gcmConnector;

	@Override
	public RegisterDeviceResponse registerGCMDevice(RegisterDeviceRequest request)
			throws EntityNotFoundException
	{
		RegisterDeviceResponse response = null;
		User user = (User) userDao.getUserByEmail(request.getEmail());
		
		user.setRegistrationId(request.getRegisterId());

		user = (User) userDao.updateAndReturn(user);
		
		response = new RegisterDeviceResponse();
		response.setRegisterId(user.getRegistrationId());
		response.setEmail(user.getUserEmail());
		response.setId(user.getId());
		return response;
	}

	@Override
	public void unregisterGCMDevice(int deviceId) throws EntityNotFoundException
	{
		User user = (User) userDao.getById(deviceId);
		user.setRegistrationId(null);
		userDao.update(user);
	}

	@Override
	public SendNotificationResponse sendNotification(SendNotificationRequest request)
			throws GCMServiceException
	{
		SendNotificationResponse response = null;
		try
		{
			// chequea si la lista de distrubución no está vacía
			if (request.getDestinationEmails().isEmpty())
				throw new GCMNullDestinationException();

			// busca al usuario solicitante y sus suscriptos
			User user = userDao.getUserByEmail(request.getOriginUserEmail());
			List<String> devices = gcmHelper.getVerifiedRegistrationIds(user, request.getDestinationEmails());

			Message message = null;

			// TODO: registrar nueva transacción en pendiente

			switch (request.getTag())
			{
			case PANIC:
				message = gcmHelper.buildPanicNotification(request.getPosition());
				break;
			default:
				break;
			}
			// envia los mensajes
			gcmConnector.sendAllMessages(devices, message);

		} catch (GCMNullDestinationException e)
		{
			e.printStackTrace();
			throw new GCMServiceException(HttpStatus.BAD_REQUEST);
		} catch (EntityNotFoundException e)
		{
			e.printStackTrace();
			throw new GCMServiceException(HttpStatus.NOT_FOUND);
		} catch (GCMSendingMessageException e)
		{
			e.printStackTrace();
			throw new GCMServiceException(HttpStatus.SERVICE_UNAVAILABLE);
		}

		return response;
	}
}
