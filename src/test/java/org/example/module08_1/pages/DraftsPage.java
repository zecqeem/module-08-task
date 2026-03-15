package org.example.module08_1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class DraftsPage extends AbstractPage{
    @FindBy(xpath = "//a[@data-testid='navigation-link:all-drafts']")
    private WebElement draftsFolder;
    @FindBy(xpath = "//span[contains(@class, 'composer-addresses-fakefield-inner')]")
    private WebElement destinationFieldInDrafts;
    @FindBy(xpath = "//span[@title = 'test']")
    private WebElement themeCheck;
    @FindBy(xpath = "//button[@data-testid='composer:send-button']")
    private WebElement sendButton;
    @FindBy(xpath = "//span[@class = 'notification__content']")
    private WebElement notificationSending;
    @FindBy(css = "input[data-testid='composer:subject']")
    private WebElement themeField;
    @FindBy(xpath = "//iframe[@data-testid='rooster-iframe']")
    private WebElement bodyFrame;
    @FindBy(xpath = "//div[@class = 'protonmail_signature_block-proton']")
    private WebElement bodyField;

    public void openDraftsFolder() {
        wait.until(visibilityOf(draftsFolder)).click();
    }

    public String getLastDraftTheme() {
        return wait.until(visibilityOf(themeCheck)).getAttribute("title");
    }

    public void pressLastDraft() {
        wait.until(visibilityOf(themeCheck)).click();
    }

    public List<String> getLetterData() {
        List<String> listOfData = new ArrayList<>();
        wait.until(attributeToBeNotEmpty(destinationFieldInDrafts, "title"));
        listOfData.add(destinationFieldInDrafts.getAttribute("title"));
        listOfData.add(themeField.getAttribute("value"));
        wait.until(frameToBeAvailableAndSwitchToIt(bodyFrame));
        listOfData.add(bodyField.getText());
        driver.switchTo().defaultContent();
        return listOfData;
    }

    public void sendEmail() {
        sendButton.click();
    }

    public void checkIfEmailWasSent() {
        wait.until(textToBePresentInElement(notificationSending, "Сообщение отправлено."));
    }
}
