package org.example.module08_1.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.util.HighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverManager.class);

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            log.error("Driver has not been initialized Call initializeDriver() first.");
            throw new RuntimeException("Driver is null. Ensure initializeDriver() is called before getDriver().");
        }
        return driver;
    }

    public static void initializeDriver(String browser) {
        log.info("Initializing browser: {}", browser);

        InterfaceDriverFactory factory = DriverFactory.getFactory(browser);
        WebDriver driver = factory.createDriver();

        WebDriver decoratedDriver = decorateDriver(driver);

        decoratedDriver.manage().window().maximize();
        driverThreadLocal.set(decoratedDriver);
    }

    private static WebDriver decorateDriver(WebDriver driver){
        HighlightListener highlightListener = new HighlightListener(driver);
        return new EventFiringDecorator<>(highlightListener).decorate(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            log.info("Quitting driver");
            driver.quit();
        }
        driverThreadLocal.remove();
        log.debug("Browser process successfully killed.");
    }
}
