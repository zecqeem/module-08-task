package org.example.module08_1.model;

public class User {
    private final String username;
    private final String password;
    private final String expectedEmail;

    public User(String username, String password,String expectedEmail){
        this.username = username;
        this.password = password;
        this.expectedEmail = expectedEmail;
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
