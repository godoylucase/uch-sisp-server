package com.uch.sisp.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.service.GCMService;

@Service
public class GCMServiceImpl implements GCMService
{
	@Autowired
	UserDAO userDao;

	@Override
	public void registerDevice(RegisterDeviceRequest device) throws EntityNotFoundException
	{
		User user = (User) userDao.getById(device.getId());
		user.setRegistrationId(device.getRegisterId());
		userDao.update(user);
	}

}
