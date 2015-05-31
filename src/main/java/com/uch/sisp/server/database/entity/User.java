package com.uch.sisp.server.database.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.uch.sisp.server.database.entity.structure.BaseEntity;
import com.uch.sisp.server.database.entity.structure.GenericDomainObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "sisp_user")
public class User extends BaseEntity implements Serializable, GenericDomainObject
{
	private String userEmail;
	private String registrationId;
	private Set<User> subscriptions;

	@Column(name = "user_email")
	public String getUserEmail()
	{
		return userEmail;
	}

	@Column(name = "gcm_registration_id")
	public String getRegistrationId()
	{
		return registrationId;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "sisp_subscriptions", 
				joinColumns = { @JoinColumn(name = "id_son") }, 
				inverseJoinColumns = { @JoinColumn(name = "id_father") })
	public Set<User> getSubscriptions()
	{
		return subscriptions;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public void setRegistrationId(String registrationId)
	{
		this.registrationId = registrationId;
	}

	public void setSubscriptions(Set<User> subscriptions)
	{
		this.subscriptions = subscriptions;
	}

}
