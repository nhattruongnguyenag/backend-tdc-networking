package com.chatapp.service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;;

public interface EmailService {
	void sendEmail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException;
}
