package org.example.module08_1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SentPage extends AbstractPage{
    @FindBy(xpath = "//a[@data-testid='navigation-link:all-sent']")
    private WebElement sentFolder;
    @FindBy(xpath = "//span[@data-testid='message-row:subject']")
    private WebElement lastEmailSubject;

    public void openSentFolder() {
        wait.until(visibilityOf(sentFolder)).click();
    }

    public String checkLastEmail() {
        wait.until(visibilityOf(lastEmailSubject));
        return lastEmailSubject.getAttribute("title");
    }
}
