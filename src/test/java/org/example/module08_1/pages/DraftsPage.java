package org.example.module08_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DraftsPage extends AbstractPage {
    private static final String SENT_NOTIFICATION = "Сообщение отправлено.";
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
    @FindBy(xpath = "//a[@data-testid='navigation-link:all-sent']")
    private WebElement sentFolder;

    private By subjectCheckLocator(String subject) {
        return By.xpath("//span[@title='" + subject + "']");
    }

    public boolean isDraftWithSubjectPresent(String subject) {
        try {
            waitForElementLocated(subjectCheckLocator(subject));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public DraftsPage openDraft(String subject) {
        waitForElementLocated(subjectCheckLocator(subject)).click();
        return this;
    }

    public List<String> getLetterData() {
        List<String> listOfData = new ArrayList<>();
        wait.until(attributeToBeNotEmpty(destinationFieldInDrafts, "title"));
        listOfData.add(destinationFieldInDrafts.getAttribute("title"));
        listOfData.add(subjectField.getAttribute("value"));
        switchToFrame(bodyFrame);
        listOfData.add(bodyField.getText());
        driver.switchTo().defaultContent();
        return listOfData;
    }

    public DraftsPage sendEmail() {
        click(sendButton);
        return this;
    }

    public boolean isEmailSentNotificationDisplayed() {
        try {
            wait.until(textToBePresentInElement(notificationSending, SENT_NOTIFICATION));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public SentPage openSentFolder() {
        click(sentFolder);
        return new SentPage();
    }
}
