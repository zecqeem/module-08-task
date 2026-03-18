package org.example.module08_1.pages;

import org.example.module08_1.model.Email;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class MainPage extends AbstractPage {
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
    @FindBy(xpath = "//a[@data-testid='navigation-link:all-drafts']")
    private WebElement draftsFolder;

    public String getAccountData() {
        return waitForVisibility(data)
                .getAttribute("title");
    }

    public MainPage createANewMessage(Email email) {
        click(newMessageButton);
        click(destinationField);
        type(destinationField,email.getDestinationEmail() + Keys.ENTER);
        click(subjectField);
        type(subjectField,email.getSubject());
        switchToFrame(bodyFrame);
        bodyField.click();
        bodyField.clear();
        bodyField.sendKeys(email.getBody());
        driver.switchTo().defaultContent();
        click(closeButton);
        return this;
    }

    public DraftsPage openDraftsFolder() {
        click(draftsFolder);
        return new DraftsPage();
    }
}
