package com.mail.SpringMailApp.Entity;

import java.util.List;

public class EmailDetails {

    private List<String> recipients;
    private String msgBody;
    private String subject;
    private String attachment;

    public EmailDetails() {
    }

    public EmailDetails(List<String> recipients, String msgBody, String subject, String attachment) {
        this.recipients = recipients;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
