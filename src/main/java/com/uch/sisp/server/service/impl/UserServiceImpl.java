package com.uch.sisp.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDAO userDao;

	@Override
	public void replaceGCMRegistrationIdByCanonicalId(String regId, String canonicalRegId) throws EntityNotFoundException 
	{
		User userToChange = userDao.getByRegistrationId(regId);
		userToChange.setRegistrationId(canonicalRegId);
		userDao.save(userToChange);
	}

	@Override
	public void removeUserFromGCMService(String registrationIdToRemove) throws EntityNotFoundException
	{
		User removeFromGCM = userDao.getByRegistrationId(registrationIdToRemove);
		removeFromGCM.setRegistrationId(null);
		userDao.save(removeFromGCM);
	}

}
