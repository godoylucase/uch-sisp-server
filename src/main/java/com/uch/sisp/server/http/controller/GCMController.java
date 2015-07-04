package com.uch.sisp.server.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendNotificationResponse;
import com.uch.sisp.server.service.GoogleNotificationService;

@RestController
@RequestMapping(value = "/gcm")
public class GCMController
{
	@Autowired
	GoogleNotificationService googleNotificationService;

	@RequestMapping(value = "/registerGCMDevice", method = RequestMethod.POST)
	public ResponseEntity<RegisterDeviceResponse> registerDevice(@RequestBody RegisterDeviceRequest request)
	{
		ResponseEntity<RegisterDeviceResponse> response = null;
		RegisterDeviceResponse responseBody = null;

		try
		{
			responseBody = googleNotificationService.registerGCMDevice(request);
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.CREATED);
		} catch (EntityNotFoundException e)
		{
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/unregisterGCMDevice/{deviceId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> unregisterDevice(@PathVariable int deviceId)
	{
		ResponseEntity<Void> response = null;

		try
		{
			googleNotificationService.unregisterGCMDevice(deviceId);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		} catch (EntityNotFoundException e)
		{
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/sendGCMNotification", method = RequestMethod.POST)
	public ResponseEntity<SendNotificationResponse> sendNotification(
			@RequestBody SendNotificationRequest request)
	{
		SendNotificationResponse responseBody = null;
		ResponseEntity<SendNotificationResponse> response = null;
		try
		{
			responseBody = googleNotificationService.sendGCMNotification(request);
			response = new ResponseEntity<SendNotificationResponse>(responseBody, HttpStatus.CREATED);
		} catch (GCMServiceException e)
		{
			response = new ResponseEntity<SendNotificationResponse>(responseBody, e.getHttpStatus());
		}

		return response;
	}
}
