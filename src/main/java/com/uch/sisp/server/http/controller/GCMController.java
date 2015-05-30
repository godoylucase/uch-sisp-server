package com.uch.sisp.server.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.request.UnregisterDeviceRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendNotificationResponse;
import com.uch.sisp.server.service.GCMService;

@RestController
@RequestMapping(value = "/gcm")
public class GCMController
{
	@Autowired
	GCMService gcmService;

	@RequestMapping(value = "/registerDevice", method = RequestMethod.POST)
	public ResponseEntity<RegisterDeviceResponse> registerDevice(@RequestBody RegisterDeviceRequest request)
	{
		ResponseEntity<RegisterDeviceResponse> response = null;
		RegisterDeviceResponse responseBody = null;

		try
		{
			responseBody = gcmService.registerDevice(request);
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.OK);
		} catch (EntityNotFoundException e)
		{
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/unregisterDevice", method = RequestMethod.POST)
	public ResponseEntity<Void> unregisterDevice(
			@RequestBody UnregisterDeviceRequest request)
	{
		ResponseEntity<Void> response = null;

		try
		{
			gcmService.unregisterDevice(request);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		} catch (EntityNotFoundException e)
		{
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<SendNotificationResponse> sendNotification(@RequestBody SendNotificationRequest request)
	{
		ResponseEntity<SendNotificationResponse> response = null;
		
		return response;
	}
}
