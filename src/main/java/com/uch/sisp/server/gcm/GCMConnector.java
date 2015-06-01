package com.uch.sisp.server.gcm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.uch.sisp.server.gcm.thread.GCMSenderThread;

@Component
public class GCMConnector
{
	private static final int MULTICAST_SIZE = 1000;
	private static final Executor threadPool = Executors.newFixedThreadPool(5);

	@Autowired
	private Sender sender;

	public void sendAllMessages(List<String> devices, Message message)
	{ // send a multicast message using JSON
		// must split in chunks of 1000 devices (GCM limit)
		int total = devices.size();
		List<String> partialDevices = new ArrayList<String>(total);
		int counter = 0;
		int tasks = 0;
		for (String device : devices)
		{
			counter++;
			partialDevices.add(device);
			int partialSize = partialDevices.size();
			if (partialSize == MULTICAST_SIZE || counter == total)
			{
				asyncSend(partialDevices, message);
				partialDevices.clear();
				tasks++;
			}
		}
	}

	private void asyncSend(List<String> partialDevices, Message message)
	{
		final List<String> devices = new ArrayList<String>(partialDevices);
		GCMSenderThread runnableSender = new GCMSenderThread(sender, message, partialDevices);
		threadPool.execute(runnableSender);
	}

}
