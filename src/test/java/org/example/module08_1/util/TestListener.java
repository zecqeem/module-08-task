package org.example.module08_1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failure: {}", result.getName());
        String screenshotPath =ScreenshotUtil.takeScreenshot(result.getName());
        log.info("Screenshot saved: {}", screenshotPath);
    }
    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}", result.getName());
    }
}
