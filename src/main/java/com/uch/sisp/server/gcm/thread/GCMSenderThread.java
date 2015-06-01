package com.uch.sisp.server.gcm.thread;

import java.io.IOException;
import java.util.List;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMSenderThread implements Runnable
{
	private Message message;
	private Sender sender;
	private List<String> partialDevices;

	public GCMSenderThread(Sender sender, Message message, List<String> partialDevices)
	{
		this.message = message;
		this.sender = sender;
		this.partialDevices = partialDevices;
	}

	@Override
	public void run()	
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
			if (messageId != null)
			{
				// logger.fine("Succesfully sent message to device: " + regId +
				// "; messageId = "
				// + messageId);
				String canonicalRegId = result.getCanonicalRegistrationId();
				if (canonicalRegId != null)
				{
					// same device has more than on registration id:
					// update it
					// logger.info("canonicalRegId " + canonicalRegId);
					// Datastore.updateRegistration(regId, canonicalRegId);
				}
			} else
			{
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED))
				{
					// application has been removed from device -
					// unregister it
					// logger.info("Unregistered device: " + regId);
					// Datastore.unregister(regId);
				} else
				{
					// logger.severe("Error sending message to " + regId + ": "
					// + error);
				}
			}
		}
	}

}
