package com.sezayir.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sezayir.demo.config.MailProperties;
import com.sezayir.demo.request.RequestBulkMail;
import com.sezayir.demo.request.RequestMail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private MailProperties mailProperties;

	@Override
	public void sendBulkMail(RequestBulkMail inputRequest) {

		String subject = inputRequest.getSubject();

		try {
			log.info("Sending bulk mail for subject : {}", subject);

			Personalization personalization = new Personalization();
			List<String> receivers = inputRequest.getTo();

			String messageText = inputRequest.getContent();

			receivers.forEach(receiverMail -> {
				Email to = new Email(receiverMail);
				personalization.addTo(to);
			});

			personalization.setSubject(subject);

			Mail mail = new Mail();

			Email from = new Email(mailProperties.getFrom());

			Content content = new Content("text/html", messageText);
			mail.setFrom(from);
			mail.addContent(content);
			mail.addPersonalization(personalization);

			SendGrid sg = new SendGrid(mailProperties.getApiKey());
			Request request = new Request();

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);

			if (response.getStatusCode() == 202) {
				log.info("Sent mail for subject : {}", subject);
			}

		} catch (IOException e) {

			log.info("Failed mail for subject : {}", subject);
		}

	}

	@Override
	public void sendMail(RequestMail inputRequest) {
		String subject = inputRequest.getSubject();

		try {
			log.info("Sending bulk mail for subject : {}", subject);
			String receiver = inputRequest.getTo();
			String messageText = inputRequest.getContent();

			Email from = new Email(mailProperties.getFrom());
			Email to = new Email(receiver);

			Content content = new Content("text/html", messageText);

			Mail mail = new Mail(from, subject, to, content);
			mail.setTemplateId("d-54ea5df208ab4ab3a705fea7c420d76b");

			SendGrid sg = new SendGrid(mailProperties.getApiKey());
			Request request = new Request();

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);

			if (response.getStatusCode() == 202) {
				log.info("Sent mail for subject : {}", subject);
			}

		} catch (IOException e) {

			log.info("Failed mail for subject : {}", subject);
		}

	}

}