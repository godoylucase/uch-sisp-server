package com.uch.sisp.server.database.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO
{

	public UserDAOImpl()
	{
		super(User.class);
	}

	@Transactional(readOnly = true)
	@Override
	public User getUserByEmail(String originUserEmail) throws EntityNotFoundException
	{
		Query query = getHibernateCurrentSession()
				.createQuery(
						"from User as u where u.userEmail = :originUserEmail");
		query.setParameter("originUserEmail", originUserEmail);
		User user = (User) query.uniqueResult();
		if(user == null) throw new EntityNotFoundException();
		return user;
	}
	
	@Transactional(readOnly = true)
	@Override
	public User getByRegistrationId(String registrationId) throws EntityNotFoundException
	{
		Query query = getHibernateCurrentSession()
				.createQuery(
						"from User as u where u.registrationId = :registrationId");
		query.setParameter("registrationId", registrationId);
		User user = (User) query.uniqueResult();
		if(user == null) throw new EntityNotFoundException();
		return user;
	}
}
