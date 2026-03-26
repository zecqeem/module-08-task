package org.example.module08_1.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.drivers.DriverManager;
import org.example.module08_1.util.Configuration;
import org.example.module08_1.util.JavaScriptUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBeNotEmpty;

public class AbstractPage {
    protected static final Logger log = LogManager.getLogger(AbstractPage.class);
    private static final String DURATION = Configuration.getInstance().get("configuration.duration");
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavaScriptUtils jsUtils;

    public AbstractPage() {
        this.driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(DURATION)));
        PageFactory.initElements(driver, this);
        jsUtils = new JavaScriptUtils(driver);
    }

    protected WebElement waitForVisibility(WebElement element) {
        log.debug("Waiting for visibility of element: {}", element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        log.debug("Clicking on element: {}", element);
        waitForVisibility(element).click();
    }

    protected void type(WebElement element, String text) {
        log.debug("Clearing field and typing text into element: {}", element);
        waitForVisibility(element).clear();
        element.sendKeys(text);
    }

    protected void switchToFrame(WebElement frame) {
        log.debug("Switching to frame...");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    protected WebElement waitForElementLocated(By locator) {
        log.debug("Waiting for element presence by locator: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForTitleAttribute(WebElement data) {
        log.debug("Waiting for 'title' attribute to be populated for element: {}", data);
        wait.until(attributeToBeNotEmpty(data, "title"));
    }
}
