package com.uch.sisp.server.service;

import com.uch.sisp.server.database.exception.EntityNotFoundException;

public interface UserService
{
	public void replaceGCMRegistrationIdByCanonicalId(String regId, String canonicalRegId) throws EntityNotFoundException;
}
