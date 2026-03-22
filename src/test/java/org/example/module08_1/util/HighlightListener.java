package org.example.module08_1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {
    private WebDriver driver;
    public HighlightListener(WebDriver driver) {
        this.driver = driver;
    }
    private void highlightElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].setAttribute('style', 'border: 3px solid red; background: blue;');",
                element
        );
    }
    @Override
    public void beforeClick(WebElement element){
        highlightElement(element);
    }
    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend){
        highlightElement(element);
    }
}
