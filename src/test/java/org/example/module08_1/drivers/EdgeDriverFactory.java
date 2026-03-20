package org.example.module08_1.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverFactory implements InterfaceDriverFactory{
    @Override
    public WebDriver createDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        return new EdgeDriver(options);
    }
}
