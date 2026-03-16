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
    private WebElement subjectCheck;
    @FindBy(xpath = "//button[@data-testid='composer:send-button']")
    private WebElement sendButton;
    @FindBy(xpath = "//span[@class = 'notification__content']")
    private WebElement notificationSending;
    @FindBy(css = "input[data-testid='composer:subject']")
    private WebElement subjectField;
    @FindBy(xpath = "//iframe[@data-testid='rooster-iframe']")
    private WebElement bodyFrame;
    @FindBy(xpath = "//div[@class = 'protonmail_signature_block-proton']")
    private WebElement bodyField;

    public void openDraftsFolder() {
        wait.until(visibilityOf(draftsFolder)).click();
    }

    public String getLastDraftSubject() {
        return wait.until(visibilityOf(subjectCheck)).getAttribute("title");
    }

    public void pressLastDraft() {
        wait.until(visibilityOf(subjectCheck)).click();
    }

    public List<String> getLetterData() {
        List<String> listOfData = new ArrayList<>();
        wait.until(attributeToBeNotEmpty(destinationFieldInDrafts, "title"));
        listOfData.add(destinationFieldInDrafts.getAttribute("title"));
        listOfData.add(subjectField.getAttribute("value"));
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
