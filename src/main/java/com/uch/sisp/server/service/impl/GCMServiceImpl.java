package com.uch.sisp.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.database.exception.NullDestinationException;
import com.uch.sisp.server.gcm.GCMConnector;
import com.uch.sisp.server.gcm.helper.GCMHelper;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.request.UnregisterDeviceRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendNotificationResponse;
import com.uch.sisp.server.service.GCMService;

@Service
public class GCMServiceImpl implements GCMService
{
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private GCMHelper gcmHelper;
	
	@Autowired
	private GCMConnector gcmConnector;

	@Override
	public RegisterDeviceResponse registerDevice(RegisterDeviceRequest request)
			throws EntityNotFoundException
	{
		RegisterDeviceResponse response = null;
		User user = (User) userDao.getById(request.getId());
		user.setRegistrationId(request.getRegisterId());

		userDao.update(user);

		response = new RegisterDeviceResponse();
		response.setRegisterId(request.getRegisterId());
		return response;
	}

	@Override
	public void unregisterDevice(UnregisterDeviceRequest request) throws EntityNotFoundException
	{
		User user = (User) userDao.getById(request.getId());
		user.setRegistrationId(null);
		userDao.update(user);
	}

	@Override
	public SendNotificationResponse sendNotification(SendNotificationRequest request)
			throws EntityNotFoundException, NullDestinationException
	{
		// chequea si la lista de distrubución no está vacía
		if (request.getDestinationEmails().isEmpty())
			throw new NullDestinationException();

		SendNotificationResponse response = null;

		// busca al usuario solicitante y sus suscriptos 
		User user = userDao.getUserByEmail(request.getOriginUserEmail());
		List<String> devices = gcmHelper.getVerifiedRegistrationIds(user,
				request.getDestinationEmails());
		
		Message message = null;

		//TODO: registrar nueva transacción en pendiente
		
		switch (request.getTag())
		{
		case PANIC:
			message = gcmHelper.buildPanicNotification(request.getPosition());
			break;
		default:
			break;
		}
		
		//envia los mensajes
		gcmConnector.sendAllMessages(devices, message);

		return response;
	}
}
