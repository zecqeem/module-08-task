package org.example.module08_1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.module08_1.drivers.DriverManager;
import org.example.module08_1.util.ScreenshotReporter;

public class Hooks {

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initializeDriver(browser);
    }

    @After
    public void shutDown(Scenario scenario) {
        if (scenario.isFailed()){
            ScreenshotReporter.attachScreenshots(scenario.getName());
        }
        DriverManager.quitDriver();
    }
}
