package org.example.module08_1.util;

import com.epam.reportportal.service.ReportPortal;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Calendar;

public class ScreenshotReporter {
    private static final Logger log = LogManager.getLogger(ScreenshotReporter.class);

    public static void attachScreenshots(String testName) {
        WebDriver driver = DriverManager.getDriver();
            attachToAllure(driver);
            attachToReportPortal(driver, testName);
    }

    private static void attachToAllure(WebDriver driver) {
        Allure.addAttachment(
                "screen AfterMethod",
                "image/png",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                ".png"
        );
        log.info("Screenshot attached to Allure.");
    }

    private static void attachToReportPortal(WebDriver driver, String testName) {
        File rpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        if (rpFile.exists()) {
            ReportPortal.emitLog("Failure screenshot: " + testName,
                    "ERROR",
                    Calendar.getInstance().getTime(),
                    rpFile);
            log.info("Screenshot attached to Report Portal.");
        } else {
            log.error("Failed to create screenshot file for Report Portal.");
        }
    }
}