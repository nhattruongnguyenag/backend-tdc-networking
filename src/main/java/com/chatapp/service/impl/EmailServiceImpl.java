package com.chatapp.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.chatapp.constant.SystemConstant;
import com.chatapp.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String urlReset , String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setFrom(SystemConstant.EMAIL_TDC_SOCIAL_NETWORK,"TDCNetwork Support");
        helper.setTo(email);
        helper.setSubject("Password Reset Request");
        helper.setText(SystemConstant.EMAIL_RESET_TEXT(urlReset , email),true);
		mailSender.send(message);
	}

    @Override
    public void sendEmail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setFrom(SystemConstant.EMAIL_TDC_SOCIAL_NETWORK,"TDCNetwork Support");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);
        mailSender.send(message);
    }
}
