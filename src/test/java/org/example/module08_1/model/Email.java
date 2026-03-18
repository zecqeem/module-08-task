package org.example.module08_1.model;


public class Email {

    private final String destinationEmail;
    private final String subject;
    private final String body;

    public Email(String destinationEmail,String subject, String body){
        this.destinationEmail = destinationEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
