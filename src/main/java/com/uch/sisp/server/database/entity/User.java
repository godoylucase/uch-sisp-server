package com.uch.sisp.server.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.uch.sisp.server.database.entity.structure.BaseEntity;
import com.uch.sisp.server.database.entity.structure.GenericDomainObject;

@SuppressWarnings("serial")
@Entity  
@Table(name="sisp_user")
public class User extends BaseEntity implements Serializable, GenericDomainObject
{
	private String userEmail;
	private String registrationId;

	@Column (name = "user_email")
	public String getUserEmail()
	{
		return userEmail;
	}
	
	@Column (name = "gcm_registration_id")
	public String getRegistrationId()
	{
		return registrationId;
	}
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}
	public void setRegistrationId(String registrationId)
	{
		this.registrationId = registrationId;
	}
}
