package com.uch.sisp.server.service.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uch.sisp.server.database.entity.User;
import com.uch.sisp.server.database.exception.NullDestinationException;

public class GCMHelper
{
	
	public static List<String> getVerifiedRegistrationIds(User user, List<String> destinationEmails) throws NullDestinationException
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
		
		if(registrationIdsList.isEmpty()) throw new NullDestinationException();
		
		return registrationIdsList;
	}

	/**
	 * Retorna un mapa Map<String, User> de subscriptos desde una lista de
	 * usuarios
	 * 
	 * @param user
	 * @return Map<String, User>
	 */
	private static Map<String, User> buildEmailUserMapFromUserList(User user)
	{
		Map<String, User> map = new HashMap<String, User>();
		Set<User> subscriptionList = user.getSubscriptions();

		for (User u : subscriptionList)
		{
			map.put(u.getUserEmail(), u);
		}

		return map;
	}
}
