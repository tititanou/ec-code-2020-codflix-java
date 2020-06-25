package com.codflix.backend.models;

public class Email {
    private String sender;
    private String recipient;
    private String title;
    private String message;

    public Email(String sender, String recipient, String title, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
    }
    @Override
    public String toString() {
        return "Email{" +
                "sender=" + sender +
                ", recipient='" + recipient + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
