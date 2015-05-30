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
import com.uch.sisp.server.http.request.UnregisterDeviceRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.UnregisterDeviceResponse;
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
		RegisterDeviceResponse body = null;

		try
		{
			gcmService.registerDevice(request);
			body = new RegisterDeviceResponse();
			body.setRegisterId(request.getRegisterId());
			response = new ResponseEntity<RegisterDeviceResponse>(body, HttpStatus.OK);
		} catch (EntityNotFoundException e)
		{
			response = new ResponseEntity<RegisterDeviceResponse>(body, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/unregisterDevice", method = RequestMethod.POST)
	public ResponseEntity<UnregisterDeviceResponse> unregisterDevice(
			@RequestBody UnregisterDeviceRequest unregDevice)
	{
		UnregisterDeviceResponse body = new UnregisterDeviceResponse();
		ResponseEntity<UnregisterDeviceResponse> response = new ResponseEntity<UnregisterDeviceResponse>(
				body, HttpStatus.OK);
		return response;
	}
}
