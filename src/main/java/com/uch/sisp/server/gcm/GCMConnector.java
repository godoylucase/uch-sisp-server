package com.uch.sisp.server.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.service.UserService;

@Component
public class GCMConnector
{
	private static final int MULTICAST_SIZE = 1000;

	@Autowired
	private Sender sender;

	@Autowired
	UserService userService;

	public void sendAllMessages(List<String> devices, Message message) throws EntityNotFoundException
	{ // send a multicast message using JSON
		// must split in chunks of 1000 devices (GCM limit)
		int total = devices.size();
		List<String> partialDevices = new ArrayList<String>(total);
		int counter = 0;
		for (String device : devices)
		{
			counter++;
			partialDevices.add(device);
			int partialSize = partialDevices.size();
			if (partialSize == MULTICAST_SIZE || counter == total)
			{
				syncSend(partialDevices, message);
				partialDevices.clear();
			}
		}
	}

	private void syncSend(List<String> partialDevices, Message message) throws EntityNotFoundException
	{
		MulticastResult multicastResult;
		try
		{
			multicastResult = sender.send(message, partialDevices, 5);
		} catch (IOException e)
		{
			// logger.log(Level.SEVERE, "Error posting messages", e);
			return;
		}
		List<Result> results = multicastResult.getResults();
		// analyze the results
		for (int i = 0; i < partialDevices.size(); i++)
		{
			String regId = partialDevices.get(i);
			Result result = results.get(i);
			String messageId = result.getMessageId();
			String canonicalRegId = null;
			if (messageId != null)
			{
				// Mensajes enviados con exito
				canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null)
				{
					// cambio de regId en caso de venir uno nuevo
					userService.replaceGCMRegistrationIdByCanonicalId(regId, canonicalRegId);
				}
			} else
			{
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED))
				{
					// aplicación removida, desuscribir
					String registrationIdToRemove = canonicalRegId == null ? regId : canonicalRegId;
					userService.removeUserFromGCMService(registrationIdToRemove);
				} else
				{
					// logger.severe("Error sending message to " + regId + ": "
					// + error);
				}
			}
		}
	}

}
