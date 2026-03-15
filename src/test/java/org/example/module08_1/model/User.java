package org.example.module08_1.model;

import org.example.module08_1.Util.ConfigReader;

public class User {
    private final String username;
    private final String password;
    private final String expectedEmail;
    ConfigReader reader = new ConfigReader();
    public User(){
        username = reader.getUsername();
        password = reader.getPassword();
        expectedEmail = reader.getExpectedEmail();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedEmail() {
        return expectedEmail;
    }
}
