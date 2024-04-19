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
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {

        // Check subject valid
        if(!isValidSubject(details.getSubject())){
            return "Subject is empty or not present";
        }

        // Get list of recipients
        List<String> recipients = details.getRecipients();

        // Invalid recipients
        List<String> invalidRecipients = new ArrayList<>();

        // Try block to check for exceptions
        try {

            for(String recipient : recipients) {
                // Check mail Id
                if (!isValidEmailAddress(recipient)) {
                    invalidRecipients.add(recipient);
                    continue; // Skip sending email to this invalid recipient
                }

                // Creating a simple mail message
                SimpleMailMessage mailMessage = new SimpleMailMessage();

                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(recipient);
                mailMessage.setText(details.getMsgBody());
                mailMessage.setSubject(details.getSubject());

                // Sending the mail
                javaMailSender.send(mailMessage);
            }
            if (!invalidRecipients.isEmpty()) {
                return "Mail Sent Successfully to valid recipients. Invalid recipients: " + invalidRecipients;
            } else {
                return "Mail Sent Successfully to all recipients.";
            }

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

        // Check Attachment Path is Valid
        if(!isValidAttachmentPath(details.getAttachment())){
            return "Attachment path is not valid.";
        }

        // Check subject valid
        if(!isValidSubject(details.getSubject())){
            return "Subject cannot be empty.";
        }

        // Get list of recipients
        List<String> recipients = details.getRecipients();

        // Variable to keep track of invalid recipients
        List<String> invalidRecipients = new ArrayList<>();

        try {

            for(String recipient: recipients) {
                // Check Mail Id
                if(!isValidEmailAddress(recipient)){
                    invalidRecipients.add(recipient);
                    continue;
                }

                // Creating a mime message
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper;
                // Setting multipart as true for attachments to be send
                mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setTo(recipient);
                mimeMessageHelper.setText(details.getMsgBody());
                mimeMessageHelper.setSubject(details.getSubject());


                // Adding the attachment
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

                mimeMessageHelper.addAttachment(file.getFilename(), file);

                // Sending the mail
                javaMailSender.send(mimeMessage);
            }
            if (!invalidRecipients.isEmpty()) {
                return "Mail sent Successfully to valid recipients. Invalid recipients: " + invalidRecipients;
            } else {
                return "Mail sent Successfully to all recipients.";
            }
    }
    // Catch block to handle MessagingException
    catch(MessagingException e) {
        // Display message when exception occurred
        return "Error while sending mail!!!";
        }
    }

    // Method to validate email address
    private boolean isValidEmailAddress(String email) {
            try {
                InternetAddress internetAddress = new InternetAddress(email);
                internetAddress.validate();
                return true;
            } catch (AddressException e) {
                return false;
            }
    }

    // Method to validate attachment path
    private boolean isValidAttachmentPath(String attachmentPath){
        File file = new File(attachmentPath);
        return file.exists() && file.isFile();
    }

    // Method to validate subject
    private boolean isValidSubject(String subject){
        return subject != null && !subject.trim().isEmpty();
    }

}
