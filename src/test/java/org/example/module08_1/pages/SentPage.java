package org.example.module08_1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends AbstractPage{
    @FindBy(xpath = "//span[@data-testid='message-row:subject']")
    private WebElement lastEmailSubject;

    public String checkLastEmail() {
        log.info("Checking if email was sent and has correct data");
        return waitForVisibility(lastEmailSubject).getAttribute("title");
    }
}
