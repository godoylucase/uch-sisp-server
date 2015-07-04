package com.uch.sisp.server.database.dao;

import java.util.List;

import com.uch.sisp.server.database.entity.structure.GenericDomainObject;
import com.uch.sisp.server.database.exception.EntityNotFoundException;

/**
 * Dicha interface permite reunir los métodos comunes tales como operaciones
 * CRUD y otras a partir de objetos genéricos.
 * 
 * @author ASAT Consulting
 * 
 * @param <T>
 */
public interface GenericDAO<T extends GenericDomainObject>
{

	public T getById(Long id) throws EntityNotFoundException;

	public T getById(Integer id) throws EntityNotFoundException;

	public List<T> getAll();

	public void save(T object);
	
	public void saveNonTransactional(T object);

	public void delete(T object);

	public Object updateAndReturn(T object);
	
	public void update(T object);

	public Object saveOrUpdateAndReturn(T object);
}
