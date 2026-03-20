package org.example.module08_1.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory implements InterfaceDriverFactory{
    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver();
    }
}
