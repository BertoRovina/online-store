package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(PurchaseOrder obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(PurchaseOrder obj);

    void sendHtmlEmail(MimeMessage msg);
}
