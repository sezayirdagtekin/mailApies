package com.sezayir.demo.service;

import com.sezayir.demo.request.RequestBulkMail;
import com.sezayir.demo.request.RequestMail;

public interface MailService {
	
	void sendMail(RequestMail inputRequest);
	void sendBulkMail(RequestBulkMail inputRequest);

}
