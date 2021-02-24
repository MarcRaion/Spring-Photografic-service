package com.marcinha.stylist.service;

import com.marcinha.stylist.entity.CrmUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(CrmUser crmUser) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(crmUser.getEmail());

        mail.setSubject("Confirmation");
//        mail.setText("Registration successfull");
        mail.setText("\nYour username is: "+ crmUser.getUserName()+"\nYour password is: "+ crmUser.getPassword());

        javaMailSender.send(mail);


    }
}
