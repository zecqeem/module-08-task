package org.example.module08_1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final Logger log = LogManager.getLogger(Configuration.class);
    private final Properties properties;
    private static Configuration instance;
    private Configuration(){
        properties = new Properties();
        String env = System.getProperty("env", "dev");
        String path = "src/test/resources/" + env + ".properties";
        log.trace("Attempting to load configuration file from path: {}", path);
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
            log.debug("Configuration file '{}' successfully loaded", env);
        } catch (IOException e) {
            log.error("Critical error: Cannot load config '{}'", env, e);
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
        log.debug("Fetching property by key: {}", key);
        String value = properties.getProperty(key);
        if (value == null) {
            log.error("Key '{}' not found in configuration file", key);
            throw new RuntimeException("Key: " + key + " not found");
        }
        return value;
    }
}
