package com.uch.sisp.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
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
	UserDAO userDao;

	@Override
	public RegisterDeviceResponse registerDevice(RegisterDeviceRequest request) throws EntityNotFoundException
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
	{
		
		// TODO Auto-generated method stub
		return null;
	}

}
