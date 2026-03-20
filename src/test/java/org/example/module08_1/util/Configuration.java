package org.example.module08_1.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "dev");
        String path = "src/test/resources/" + env + ".properties";

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config: " + env, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key: " + key + " not found");
        }
        return value;
    }
}
