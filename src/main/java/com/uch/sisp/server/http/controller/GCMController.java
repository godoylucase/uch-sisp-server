package com.uch.sisp.server.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;

@RestController
@RequestMapping(value = "/gcm")
public class GCMController
{
	@RequestMapping(value = "/registerDevice", method = RequestMethod.POST)
	public ResponseEntity<RegisterDeviceResponse> registerDevice(
			@RequestBody RegisterDeviceRequest regDevice)
	{
		RegisterDeviceResponse body = new RegisterDeviceResponse();
		body.setRegisterId(regDevice.getRegisterId());
		ResponseEntity<RegisterDeviceResponse> response = new ResponseEntity<RegisterDeviceResponse>(body, HttpStatus.OK);
		return response;
	}
}
