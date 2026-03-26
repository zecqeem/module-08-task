package org.example.module08_1.pages;

import org.example.module08_1.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    private static final String URL = "https://account.proton.me/mail";
    @FindBy(id = "username")
    private WebElement usernameInput;
    @FindBy(css = "#password")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage openPage() {
        log.info("Opening login page");
        driver.get(URL);
        return this;
    }

    public MainPage logIn(User user){
        log.info("Logging in with login: {}", user.getUsername());
        type(usernameInput,user.getUsername());
        type(passwordInput,user.getPassword());
        click(submitButton);
        return new MainPage();
    }
}
