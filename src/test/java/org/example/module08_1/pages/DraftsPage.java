package org.example.module08_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DraftsPage extends AbstractPage {
    private static final String SENT_NOTIFICATION = "Message sent.";
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
    @FindBy(id = "rooster-editor")
    private WebElement bodyField;
    @FindBy(xpath = "//a[@data-testid='navigation-link:all-sent']")
    private WebElement sentFolder;

    private By subjectCheckLocator(String subject) {
        return By.xpath("//span[@title='" + subject + "']");
    }

    public boolean isDraftWithSubjectPresent(String subject) {
        log.info("Verifying drafts presence");
        try {
            waitForElementLocated(subjectCheckLocator(subject));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DraftsPage openDraft(String subject) {
        log.info("Opening latest draft");
        waitForElementLocated(subjectCheckLocator(subject)).click();
        return this;
    }

    public List<String> getLetterData() {
        log.info("Gathering draft letter data");
        List<String> listOfData = new ArrayList<>();
        waitForTitleAttribute(destinationFieldInDrafts);
        listOfData.add(destinationFieldInDrafts.getAttribute("title"));
        listOfData.add(subjectField.getAttribute("value"));
        switchToFrame(bodyFrame);
        listOfData.add(bodyField.getText());
        driver.switchTo().defaultContent();
        return listOfData;
    }

    public DraftsPage sendEmail() {
        log.info("Sending email");
        wait.until(elementToBeClickable(sendButton));
        new Actions(driver)
                .moveToElement(sendButton)
                .click()
                .perform();
        return this;
    }

    public boolean isEmailSentNotificationDisplayed() {
        log.info("Waiting for email to be send");
        try {
            wait.until(textToBePresentInElement(notificationSending, SENT_NOTIFICATION));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public SentPage openSentFolder() {
        log.info("Opening drafts page");
        click(sentFolder);
        return new SentPage();
    }
}
