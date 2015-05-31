package com.uch.sisp.server.database.dao;

import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;

public interface UserDAO extends GenericDAO<User>
{
	public User getUserByEmail(String originUserEmail) throws EntityNotFoundException;
	
}
