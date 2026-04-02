package org.example.module08_1.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory implements InterfaceDriverFactory{
    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments(
                "--headless=new",
                "--window-size=1920,1080"
        );
        return new ChromeDriver(options);
    }
}
