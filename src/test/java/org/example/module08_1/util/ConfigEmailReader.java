package org.example.module08_1.util;

import org.example.module08_1.model.Email;

public class ConfigEmailReader {

    public static Email getEmailData(){
        return new Email(
                Configuration.get("email.destination"),
                Configuration.get("email.subject"),
                Configuration.get("email.body")
        );
    }
}
