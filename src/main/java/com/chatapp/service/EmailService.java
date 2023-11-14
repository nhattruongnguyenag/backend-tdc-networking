package com.chatapp.service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface EmailService {
	public void sendEmail(String urlReset , String email) throws MessagingException, UnsupportedEncodingException;
}
