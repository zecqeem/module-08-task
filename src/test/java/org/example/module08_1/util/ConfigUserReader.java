package org.example.module08_1.util;

import org.example.module08_1.model.Email;
import org.example.module08_1.model.User;

import java.io.BufferedReader;
import java.io.FileReader;

public class ConfigUserReader {

    public static User getUserData() {
        return new User(
                Configuration.get("user.username"),
                Configuration.get("user.password"),
                Configuration.get("user.expectedEmail")
        );
    }


}
