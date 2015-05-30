package com.uch.sisp.server.database.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;

@Repository
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO
{

	public UserDAOImpl(Class<User> type)
	{
		super(User.class);
	}

}
