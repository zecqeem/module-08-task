package org.example.module08_1.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JavaScriptUtils {
    private final WebDriver driver;

    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndTypeContentEditableJS(WebElement element, String text) {
        ((JavascriptExecutor) driver).executeScript("document.execCommand('selectAll', false, null);");
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(text)
                .perform();
    }
}
