package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.services.interfaces.EmailSender;

@Service
public class EmailServiceImpl implements EmailSender{
	
	@Autowired
	private JavaMailSender mailSender; // this one is in built interface extends from MailSender

	@Override
	@Async
	public String sendEmail(String toEmail, String title, String body) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("anilbemech2020@gmail.com");
		mail.setTo(toEmail);
		mail.setText(body);
		mail.setSubject(title);
		
		mailSender.send(mail);
		return "mail sent successfulyy!!!";
	}
	

}
