package com.janitha.trafficoffencemanagement.emailservice.service;

public interface EmailService {
    public void sendReminderEmails();
    public void sendUserCredentialsEmail(String userName, String email, String password);
}
