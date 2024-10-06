package com.app.services.interfaces;

public interface EmailSender {
	public String sendEmail(String toEmail,String title,String body);
}
