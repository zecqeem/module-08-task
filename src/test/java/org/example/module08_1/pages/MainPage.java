package org.example.module08_1.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AbstractPage {
    private static final String dest = "zecqeem@gmail.com";
    @FindBy(xpath = "//button[@data-testid='heading:userdropdown']")
    private WebElement data;
    @FindBy(xpath = "//button[text() = 'Новое сообщение']")
    private WebElement newMessageButton;
    @FindBy(css = "input[data-testid='composer:to']")
    private WebElement destinationField;
    @FindBy(css = "input[data-testid='composer:subject']")
    private WebElement themeField;
    @FindBy(xpath = "//iframe[@data-testid='rooster-iframe']")
    private WebElement bodyFrame;
    @FindBy(xpath = "//div[@class = 'protonmail_signature_block-proton']")
    private WebElement bodyField;
    @FindBy(xpath = "//button[@data-testid='composer:close-button']")
    private WebElement closeButton;


    public String getAccountData() {
        return wait.until(visibilityOf(data))
                .getAttribute("title");
    }

    public void createANewMessage() {
        wait.until(visibilityOf(newMessageButton)).click();
    }

    public void writeDestination() {
        wait.until(visibilityOf(destinationField))
                .click();
        destinationField.sendKeys(dest + Keys.ENTER);
    }

    public void writeTheme() {
        themeField.click();
        themeField.sendKeys("test");
    }

    public void writeBody() {
        wait.until(frameToBeAvailableAndSwitchToIt(bodyFrame));
        bodyField.click();
        bodyField.clear();
        bodyField.sendKeys("test");
        driver.switchTo().defaultContent();
    }

    public void closeAndSave() {
        closeButton.click();
    }

}
