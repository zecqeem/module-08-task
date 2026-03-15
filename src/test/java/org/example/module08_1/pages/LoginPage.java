package org.example.module08_1.pages;

import org.example.module08_1.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginPage extends AbstractPage {
    private static final String URL = "https://account.proton.me/mail";
    User user;
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(css = "#password")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage(User user) {
        this.user = user;
    }

    public void openPage() {
        driver.get(URL);
    }

    public void writeUsername() {
        wait.until(visibilityOf(usernameInput)).sendKeys(user.getUsername());
    }

    public void writePassword() {
        wait.until(visibilityOf(passwordInput)).sendKeys(user.getPassword());
    }

    public void pressLoginButton() {
        submitButton.click();
    }

}
