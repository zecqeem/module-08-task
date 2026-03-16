package org.example.module08_1.pages;

import org.example.module08_1.model.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DraftsPage extends AbstractPage {
    private static final String SENT_NOTIFICATION = "Сообщение отправлено.";
    private final String subject;

    @FindBy(xpath = "//a[@data-testid='navigation-link:all-drafts']")
    private WebElement draftsFolder;
    @FindBy(xpath = "//span[contains(@class, 'composer-addresses-fakefield-inner')]")
    private WebElement destinationFieldInDrafts;
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

    public DraftsPage(Email email) {
        this.subject = email.getSubject();
    }

    private By subjectCheckLocator() {
        return By.xpath("//span[@title='" + subject + "']");
    }

    public void openDraftsFolder() {
        wait.until(visibilityOf(draftsFolder)).click();
    }

    public String getLastDraftSubject() {
        return wait.until(visibilityOfElementLocated(subjectCheckLocator())).getAttribute("title");
    }

    public void pressLastDraft() {
        wait.until(visibilityOfElementLocated(subjectCheckLocator())).click();
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
        wait.until(textToBePresentInElement(notificationSending, SENT_NOTIFICATION));
    }
}
