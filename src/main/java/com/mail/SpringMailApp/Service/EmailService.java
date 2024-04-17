package com.mail.SpringMailApp.Service;

import com.mail.SpringMailApp.Entity.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send a email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
