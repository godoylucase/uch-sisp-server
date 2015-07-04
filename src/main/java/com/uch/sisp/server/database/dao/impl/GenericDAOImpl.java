package com.uch.sisp.server.database.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.uch.sisp.server.database.dao.GenericDAO;
import com.uch.sisp.server.database.entity.structure.GenericDomainObject;
import com.uch.sisp.server.database.exception.EntityNotFoundException;

/**
 * Implementación de {@link GenericDAO}
 * 
 * @author ASAT Consulting
 * 
 * @param <T>
 */
@Transactional(rollbackFor = RuntimeException.class)
public abstract class GenericDAOImpl<T extends GenericDomainObject> implements GenericDAO<T>
{
	@Autowired
	private SessionFactory sessionFactory = null;
	protected Class<T> type;

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public Session getHibernateCurrentSession()
	{
		return this.sessionFactory.getCurrentSession();
	}

	public Class<T> getType()
	{
		return type;
	}

	public void setType(Class<T> type)
	{
		this.type = type;
	}
	
	public GenericDAOImpl(Class<T> type) {
		super();
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T getById(Long id) throws EntityNotFoundException
	{
		T entity = null;
		Session session = getHibernateCurrentSession();
		try
		{
			entity = (T) session.get(type, id);
			if (entity == null)
			{
				throw new EntityNotFoundException();
			}
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T getById(Integer id) throws EntityNotFoundException
	{
		T entity = null;
		Session session = getHibernateCurrentSession();
		try
		{
			entity = (T) session.get(type, id);

			if (entity == null)
			{
				throw new EntityNotFoundException();
			}
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll()
	{
		return findByCriteria();
	}

	@Override
	@Transactional(readOnly = false)
	public void save(T object)
	{
		try
		{
			getHibernateCurrentSession().save(object);
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void saveNonTransactional(T object)
	{
		try
		{
			getHibernateCurrentSession().save(object);
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T object)
	{
		try
		{
			getHibernateCurrentSession().update(object);
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public Object updateAndReturn(T object)
	{
		try
		{
			getHibernateCurrentSession().update(object);
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
		return object;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T object)
	{
		try
		{
			getHibernateCurrentSession().delete(object);
		} catch (HibernateException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion)
	{
		List<T> result = null;
		Session session = getHibernateCurrentSession();
		Criteria criteria = session.createCriteria(type);

		if (criterion.length != 0)
		{
			for (Criterion c : criterion)
			{
				criteria.add(c);
			}
			result = criteria.list();
		} else
		{
			result = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}

		return result;
	}
}
