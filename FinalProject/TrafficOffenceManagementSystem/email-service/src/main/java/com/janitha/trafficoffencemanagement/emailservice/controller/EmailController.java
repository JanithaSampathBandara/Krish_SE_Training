package com.janitha.trafficoffencemanagement.emailservice.controller;

import com.janitha.trafficoffencemanagement.emailservice.model.User;
import com.janitha.trafficoffencemanagement.emailservice.service.EmailService;
import com.janitha.trafficoffencemanagement.emailservice.service.impl.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services/emails")
public class EmailController {

    Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    EmailService emailService;

    @GetMapping
    public String msg(){
        return "janzz";
    }

    @PostMapping
    public void sendUserCredentialsEmail(@RequestBody User user){

        try{

            emailService.sendUserCredentialsEmail(user.username, user.email, user.password);

        }catch(MailException mailException){

            logger.error(mailException.getMessage());

        }

    }
}


