package org.example.module08_1.model;

import org.example.module08_1.util.ConfigEmailReader;

public class Email {

    private final String destinationEmail;
    private final String subject;
    private final String body;
    ConfigEmailReader reader = new ConfigEmailReader();
    public Email(){
        destinationEmail = reader.getDestinationEmail();
        subject = reader.getSubject();
        body = reader.getBody();
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
