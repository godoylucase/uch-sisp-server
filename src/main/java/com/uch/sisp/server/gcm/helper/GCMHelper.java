package com.uch.sisp.server.gcm.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.gcm.exception.GCMNullDestinationException;
import com.uch.sisp.server.http.domain.GPSPosition;

@Component
public class GCMHelper
{

	public List<String> getVerifiedRegistrationIds(User user, List<String> destinationEmails)
			throws GCMNullDestinationException
	{
		List<String> registrationIdsList = new ArrayList<String>();
		Map<String, User> mapEmailUser = buildEmailUserMapFromUserList(user);

		for (String destination : destinationEmails)
		{
			if (mapEmailUser.containsKey(destination))
			{
				String registrationId = mapEmailUser.get(destination).getRegistrationId();
				registrationIdsList.add(registrationId);
			}
		}

		if (registrationIdsList.isEmpty())
			throw new GCMNullDestinationException();

		return registrationIdsList;
	}

	/**
	 * Retorna un mapa Map<String, User> de subscriptos desde una lista de
	 * usuarios
	 * 
	 * @param user
	 * @return Map<String, User>
	 */
	private Map<String, User> buildEmailUserMapFromUserList(User user)
	{
		Map<String, User> map = new HashMap<String, User>();
		Set<User> subscriptionList = user.getSubscriptions();

		for (User u : subscriptionList)
		{
			map.put(u.getUserEmail(), u);
		}

		return map;
	}

	/**
	 * Construye un mensaje para una solicitud de Panico
	 * 
	 * @param position
	 * @return
	 */
	public Message buildPanicNotification(GPSPosition position)
	{
		Message message = new Message.Builder().collapseKey("Panico").timeToLive(3).delayWhileIdle(true)
				.addData("latitude", position.getLatitude().toString())
				.addData("altitude", position.getAltitude().toString()).build();
		return message;
	}
}
