package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(PurchaseOrder obj);

    void sendEmail(SimpleMailMessage msg);
}
