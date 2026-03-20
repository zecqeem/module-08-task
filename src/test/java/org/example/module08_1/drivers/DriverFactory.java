package org.example.module08_1.drivers;

import java.util.Objects;

public class DriverFactory {
    public static InterfaceDriverFactory getFactory(String browser) {
        browser = Objects.requireNonNullElse(browser, "chrome").trim().toLowerCase();

        switch (browser) {
            case "edge":
                return new EdgeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            case "chrome":
            default:
                return new ChromeDriverFactory();
        }
    }
}
