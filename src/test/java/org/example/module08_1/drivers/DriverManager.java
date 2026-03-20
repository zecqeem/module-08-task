package org.example.module08_1.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverManager.class);
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            log.error("Driver has not been initialized Call initializeDriver() first.");
            throw new RuntimeException();
        }
        return driver;
    }

    public static void initializeDriver(String browser) {
        log.info("Initializing browser: {}", browser);
        InterfaceDriverFactory factory = DriverFactory.getFactory(browser);
        WebDriver driver = factory.createDriver();
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            log.info("Quitting driver");
            driver.quit();
        }
        driverThreadLocal.remove();
    }
}
