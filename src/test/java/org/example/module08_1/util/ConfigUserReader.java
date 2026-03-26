package org.example.module08_1.util;
import org.example.module08_1.model.User;

public class ConfigUserReader {

    public static User getUserData() {
        Configuration configuration = Configuration.getInstance();
        return new User(
                configuration.get("user.username"),
                configuration.get("user.password"),
                configuration.get("user.expectedEmail")
        );
    }


}
