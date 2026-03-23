package org.example.module08_1.util;

import org.example.module08_1.model.Email;

public class ConfigEmailReader {

    public static Email getEmailData(){
        Configuration configuration = Configuration.getInstance();
        return new Email(
                configuration.get("email.destination"),
                configuration.get("email.subject"),
                configuration.get("email.body")
        );
    }
}
