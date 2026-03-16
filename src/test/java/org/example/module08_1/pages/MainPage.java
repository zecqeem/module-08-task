package org.example.module08_1.pages;

import org.example.module08_1.model.Email;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class MainPage extends AbstractPage {
    private Email email;
    @FindBy(xpath = "//button[@data-testid='heading:userdropdown']")
    private WebElement data;
    @FindBy(xpath = "//button[@data-testid = 'sidebar:compose']")
    private WebElement newMessageButton;
    @FindBy(css = "input[data-testid='composer:to']")
    private WebElement destinationField;
    @FindBy(css = "input[data-testid='composer:subject']")
    private WebElement subjectField;
    @FindBy(xpath = "//iframe[@data-testid='rooster-iframe']")
    private WebElement bodyFrame;
    @FindBy(xpath = "//div[@class = 'protonmail_signature_block-proton']")
    private WebElement bodyField;
    @FindBy(xpath = "//button[@data-testid='composer:close-button']")
    private WebElement closeButton;

    public MainPage(Email email) {
        this.email = email;
    }

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
        destinationField.sendKeys(email.getDestinationEmail() + Keys.ENTER);
    }

    public void writeSubject() {
        subjectField.click();
        subjectField.sendKeys(email.getSubject());
    }

    public void writeBody() {
        wait.until(frameToBeAvailableAndSwitchToIt(bodyFrame));
        bodyField.click();
        bodyField.clear();
        bodyField.sendKeys(email.getBody());
        driver.switchTo().defaultContent();
    }

    public void closeAndSave() {
        closeButton.click();
    }

}
