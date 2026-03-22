package org.example.module08_1.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private final Properties properties;
    private static Configuration instance;
    private Configuration(){
        properties = new Properties();
        String env = System.getProperty("env", "dev");
        String path = "src/test/resources/" + env + ".properties";

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config: " + env, e);
        }
    }
    public static synchronized Configuration getInstance(){
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key: " + key + " not found");
        }
        return value;
    }
}
