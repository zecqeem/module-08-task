package org.example.module08_1.util;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureScreenshot {
    private static final Logger log = LogManager.getLogger(AllureScreenshot.class);
    public static void saveScreenshotToAllure(){
        WebDriver driver = DriverManager.getDriver();
        Allure.addAttachment(
                "screen AfterMethod",
                "image/png",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                ".png"
        );
        log.info("Screenshot attached to Allure Report");
    }
}
