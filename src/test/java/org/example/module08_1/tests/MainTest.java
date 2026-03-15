package org.example.module08_1.tests;

import org.example.module08_1.drivers.DriverManager;
import org.example.module08_1.model.User;
import org.example.module08_1.pages.DraftsPage;
import org.example.module08_1.pages.LoginPage;

import static org.testng.Assert.*;

import org.example.module08_1.pages.MainPage;
import org.example.module08_1.pages.SentPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private DraftsPage draftsPage;
    private SentPage sentPage;
    private User user;
    List<String> listOfLetter;

    @BeforeMethod
    public void setUp() {
        DriverManager.initializeDriver();
        user = new User();
        loginPage = new LoginPage(user);
        mainPage = new MainPage();
        draftsPage = new DraftsPage();
        sentPage = new SentPage();
        listOfLetter = new ArrayList<>();
        listOfLetter.add("zecqeem@gmail.com");
        listOfLetter.add("test");
        listOfLetter.add("test");
    }

    @AfterMethod
    public void shutDown() {
        DriverManager.quitDriver();
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        logIn();
        assertEquals(mainPage.getAccountData(), user.getExpectedEmail(), "Login failed or email doesn't match");
    }

    @Test(priority = 2)
    public void testCreateAndVerifyDraft() {
        logIn();
        createDraft();

        draftsPage.openDraftsFolder();
        assertEquals(draftsPage.getLastDraftTheme(), "test", "Draft theme doesn't match");

        draftsPage.pressLastDraft();
        assertEquals(draftsPage.getLetterData(), listOfLetter, "Draft content is incorrect");
    }

    @Test(priority = 3)
    public void testSendEmailFromDrafts() {
        logIn();
        createDraft();

        draftsPage.openDraftsFolder();
        draftsPage.pressLastDraft();
        draftsPage.sendEmail();
        draftsPage.checkIfEmailWasSent();

        sentPage.openSentFolder();
        assertEquals(sentPage.checkLastEmail(), "test", "Email is not found in Sent folder");
    }

    private void logIn() {
        loginPage.openPage();
        loginPage.writeUsername();
        loginPage.writePassword();
        loginPage.pressLoginButton();
    }

    private void createDraft() {
        mainPage.createANewMessage();
        mainPage.writeDestination();
        mainPage.writeTheme();
        mainPage.writeBody();
        mainPage.closeAndSave();
    }
}

