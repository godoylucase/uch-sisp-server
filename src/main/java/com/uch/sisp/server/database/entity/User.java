package com.uch.sisp.server.database.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.uch.sisp.server.database.entity.structure.BaseEntity;
import com.uch.sisp.server.database.entity.structure.GenericDomainObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "USER")
@Getter @Setter
public class User extends BaseEntity implements Serializable, GenericDomainObject
{
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "gcm_registration_id")
	private String registrationId;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "SISP_SUBSCRIPTIONS", 
		joinColumns = { @JoinColumn(name = "id_son") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_father") })
	private Set<User> subscriptions = new HashSet<User>();

	@ManyToMany(mappedBy = "subscriptions")
	private Set<User> fathers = new HashSet<User>();

}
