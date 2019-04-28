package com.hrovina.onlinestore.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender; // gets email metadata from resources.application.properties

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Email Service Simulation...");
        mailSender.send(msg);
        LOG.info("Email sent.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Html Email Service Simulation...");
        javaMailSender.send(msg);
        LOG.info("Email sent.");
    }
}
