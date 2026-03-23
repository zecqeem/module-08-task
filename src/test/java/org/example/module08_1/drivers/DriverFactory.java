package org.example.module08_1.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    private static final String FACTORY_PACKAGE = "org.example.module08_1.drivers.";
    public static InterfaceDriverFactory getFactory(String browser) {
        browser = Objects.requireNonNullElse(browser, "chrome").trim().toLowerCase();
        String className = createClassName(browser);
        try {
            return (InterfaceDriverFactory) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // if factory is not exist, just make a chrome
            log.warn("Factory for browser '{}' not found ({}). Using chrome.", browser, e.getMessage());
            return new ChromeDriverFactory();
        }
    }
    private static String createClassName(String browser){
        String capitalizedBrowser = browser.substring(0, 1).toUpperCase() + browser.substring(1);

        return FACTORY_PACKAGE + capitalizedBrowser + "DriverFactory";
    }
}
