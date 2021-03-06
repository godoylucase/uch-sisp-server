package com.uch.sisp.server.http.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.sisp.server.database.exception.EntityNotFoundException;
import com.uch.sisp.server.gcm.exception.GCMServiceException;
import com.uch.sisp.server.http.request.RegisterDeviceRequest;
import com.uch.sisp.server.http.request.SendPanicNotificationRequest;
import com.uch.sisp.server.http.response.RegisterDeviceResponse;
import com.uch.sisp.server.http.response.SendPanicNotificationResponse;
import com.uch.sisp.server.service.GoogleNotificationService;

@Slf4j
@RestController
@RequestMapping(value = "/gcm")
public class GCMController {
	@Autowired
	GoogleNotificationService googleNotificationService;

	@RequestMapping(value = "/registerGCMDevice", method = RequestMethod.POST)
	public ResponseEntity<RegisterDeviceResponse> registerDevice(@RequestBody RegisterDeviceRequest request) {
		log.debug("Registrando GCM services usuario con id {}.", request.getId());
		ResponseEntity<RegisterDeviceResponse> response = null;
		RegisterDeviceResponse responseBody = null;

		try {
			responseBody = googleNotificationService.registerGCMDevice(request);
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.CREATED);
		} catch (EntityNotFoundException e) {
			response = new ResponseEntity<RegisterDeviceResponse>(responseBody, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(value = "/unregisterGCMDevice/{deviceId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> unregisterDevice(@PathVariable int deviceId) {
		ResponseEntity<Void> response = null;
		try {
			googleNotificationService.unregisterGCMDevice(deviceId);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/sendPanicNotification", method = RequestMethod.POST)
	public ResponseEntity<SendPanicNotificationResponse> sendNotification(@RequestBody SendPanicNotificationRequest request) {
		ResponseEntity<SendPanicNotificationResponse> response = null;
		try {
			googleNotificationService.sendPanicNotification(request);
			response = new ResponseEntity<SendPanicNotificationResponse>(HttpStatus.CREATED);
		} catch (GCMServiceException e) {
			response = new ResponseEntity<SendPanicNotificationResponse>(e.getHttpStatus());
		}
		return response;
	}
}
