package com.codex.service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;
	
	public void sendVerificationOtpEmail(String email,String otp) throws MessagingException {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,"UTF-8");
		
		String subject="verify OTP";
		String text="Your Verification code is "+otp; 
		
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(text);
		mimeMessageHelper.setTo(email);
		
		
		try {
			javaMailSender.send(mimeMessage);
		}catch(Exception e) {
			throw new MailSendException(e.getMessage());
		}
	}
}
