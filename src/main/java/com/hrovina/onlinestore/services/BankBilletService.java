package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.BankBilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankBilletService {

    public void fillBankBilletPayment(BankBilletPayment bankBilletPayment, Date orderDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderDate);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        bankBilletPayment.setExpireDate(cal.getTime());
    }
}
