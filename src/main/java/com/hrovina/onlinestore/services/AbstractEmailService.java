package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.PurchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    private final String EMAIL_SUBJECT = "Order Confirmed! Code: ";

    @Override
    public void sendOrderConfirmationEmail(PurchaseOrder obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(PurchaseOrder obj){

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject(EMAIL_SUBJECT + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }

    protected String htmlFromTemplateOrder(PurchaseOrder obj){

        Context context = new Context();
        context.setVariable("purchaseOrder", obj);
        return templateEngine.process("email/orderConfirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(PurchaseOrder obj){
        try{
            MimeMessage mm = prepareMimeMessageFromOrder(obj);
            sendHtmlEmail(mm);
        }catch (MessagingException e){
            sendOrderConfirmationEmail(obj);
        }
    }

    protected MimeMessage prepareMimeMessageFromOrder(PurchaseOrder obj) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

        mmh.setTo(obj.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject(EMAIL_SUBJECT + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrder(obj), true);
        return mimeMessage;
    }
}

