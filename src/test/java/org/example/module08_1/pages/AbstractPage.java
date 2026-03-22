package org.example.module08_1.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.drivers.DriverManager;
import org.example.module08_1.util.Configuration;
import org.example.module08_1.util.JavaScriptUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        waitForVisibility(element).click();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element).clear();
        element.sendKeys(text);
    }
    protected void switchToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
    protected WebElement waitForElementLocated(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitForTitleAttribute(WebElement data) {
        wait.until(attributeToBeNotEmpty(data, "title"));
    }


}
