package org.example.module08_1.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.module08_1.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBeNotEmpty;

public class AbstractPage {
    protected static final Logger log = LogManager.getLogger(AbstractPage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    public AbstractPage() {
        this.driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
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
    protected void clearAndTypeContentEditableJS(WebElement element,String text) {
        ((JavascriptExecutor) driver).executeScript("document.execCommand('selectAll', false, null);");
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(text)
                .perform();
    }


}
