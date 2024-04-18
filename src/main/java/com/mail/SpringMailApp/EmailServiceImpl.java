package com.mail.SpringMailApp;

import com.mail.SpringMailApp.Entity.EmailDetails;
import com.mail.SpringMailApp.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method to validate email address
    private boolean isValidEmailAddress(String email){
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            return true;
        }
        catch(AddressException e) {
            return false;
        }
    }

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {

        // Check mail Id
        if(!isValidEmailAddress(details.getRecipient())){
            return "Recipient Address is invalid.";
        }

        // Check subject valid
        if(!isValidSubject(details.getSubject())){
            return "Subject is empty or not present";
        }

        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully..";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    @Override
    public String sendMailWithAttachment(EmailDetails details) {

        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        // Check mail Id
        if(!isValidEmailAddress(details.getRecipient())){
            return "Recipient Address is invalid.";
        }

        // Check Attachment Path is Valid
        if(!isValidAttachmentPath(details.getAttachment())){
            return "Attachment path is not valid.";
        }

        // Check subject valid
        if(!isValidSubject(details.getSubject())){
            return "Subject cannot be empty.";
        }

        try {
            // Setting multipart as true for attachments to be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());


        // Adding the attachment
        FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

        mimeMessageHelper.addAttachment(file.getFilename(), file);

        // Sending the mail
        javaMailSender.send(mimeMessage);

        return "Mail sent Successfully...";
    }
    // Catch block to handle MessagingException
    catch(MessagingException e) {
        // Display message when exception occurred
        return "Error while sending mail!!!";
        }
    }

    // Method to validate attachment path
    private boolean isValidAttachmentPath(String attachmentPath){
        File file = new File(attachmentPath);
        return file.exists() && file.isFile();
    }

    private boolean isValidSubject(String subject){
        return subject != null && !subject.trim().isEmpty();
    }

}
