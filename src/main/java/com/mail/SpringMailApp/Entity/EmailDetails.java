package com.mail.SpringMailApp.Entity;

import java.util.List;

public class EmailDetails {

    private List<String> recipients;
    private String msgBody;
    private String subject;
    private List<String> attachments;

    public EmailDetails() {
    }

    public EmailDetails(List<String> recipients, String msgBody, String subject, List<String> attachments) {
        this.recipients = recipients;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachments = attachments;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
}