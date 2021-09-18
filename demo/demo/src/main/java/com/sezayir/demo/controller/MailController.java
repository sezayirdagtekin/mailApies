package com.sezayir.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sezayir.demo.request.RequestBulkMail;
import com.sezayir.demo.request.RequestMail;
import com.sezayir.demo.service.MailService;

@RestController
@RequestMapping("mail")
public class MailController {

	@Autowired
	MailService mailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendMail(@RequestBody RequestMail request) {

		mailService.sendMail(request);
		return null;

	}
	@PostMapping("/bulk/send")
	public ResponseEntity<String> sendMail(@RequestBody RequestBulkMail request) {

		mailService.sendBulkMail(request);
		return null;

	}
	

}
