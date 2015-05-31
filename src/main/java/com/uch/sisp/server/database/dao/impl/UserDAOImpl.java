package com.uch.sisp.server.database.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uch.sisp.server.database.dao.UserDAO;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.EntityNotFoundException;

@Repository
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO
{

	public UserDAOImpl(Class<User> type)
	{
		super(User.class);
	}

	@Override
	public User getUserByEmail(String originUserEmail) throws EntityNotFoundException
	{
		Query query = getHibernateCurrentSession()
				.createQuery(
						"from User as u where u.userEmail = :originUserEmail order by u.originUserEmail asc");
		query.setParameter("originUserEmail", originUserEmail);
		User user = (User) query.uniqueResult();
		if(user == null) throw new EntityNotFoundException();
		return user;
	}
}
