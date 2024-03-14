package com.scheduler.service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    
    public void sendEmail(String recipient, String emailBody, String subject) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject(subject);
        message.setFrom(fromEmail);
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient));

        MimeMessageHelper helper = new MimeMessageHelper(message, true); // Set multipart mode for attachments (if needed)
        helper.setText(emailBody, true); // Set HTML content type

        mailSender.send(message);
    }
    
    public void successfulRegistrationEmail(String recipient) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject("Webhook Registration was Successfull");
        message.setFrom(fromEmail);
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient));
        
        String emailBody = "Dear "+recipient+", \n\nYour registration for Webhook API was successful!!!"
				   + "\nYou will now receive email notifications for any change in product information for the events you have opted for.\n";
		   
        emailBody = emailBody + "\nThanks & Regards \nParker Team";

        MimeMessageHelper helper = new MimeMessageHelper(message, true); // Set multipart mode for attachments (if needed)
        helper.setText(emailBody, true); // Set HTML content type

        mailSender.send(message);
    }
}
	