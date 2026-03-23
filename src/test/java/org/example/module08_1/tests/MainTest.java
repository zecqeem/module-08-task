package org.example.module08_1.tests;
import org.example.module08_1.drivers.DriverManager;
import org.example.module08_1.model.Email;
import org.example.module08_1.model.User;
import org.example.module08_1.pages.DraftsPage;
import org.example.module08_1.pages.LoginPage;
import org.example.module08_1.pages.MainPage;
import org.example.module08_1.pages.SentPage;
import org.example.module08_1.util.AllureScreenshot;
import org.example.module08_1.util.ConfigEmailReader;
import org.example.module08_1.util.ConfigUserReader;
import org.example.module08_1.util.TestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import static org.testng.Assert.*;
@Listeners(TestListener.class)
public class MainTest {
    private User user;
    private Email email;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        DriverManager.initializeDriver(browser);
        user = ConfigUserReader.getUserData();
        email = ConfigEmailReader.getEmailData();
    }

    @AfterMethod
    public void shutDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureScreenshot.saveScreenshotToAllure();
        }
        DriverManager.quitDriver();
    }

    @Test
    public void testSuccessfulLogin(){
        MainPage mainPage = new LoginPage()
                .openPage()
                .logIn(user);
        assertEquals(mainPage.getAccountData(), user.getExpectedEmail(), "Login failed or email doesn't match");
    }

    @Test
    public void testCreateAndVerifyDraft() {
        DraftsPage draftsPage = new LoginPage()
                .openPage()
                .logIn(user)
                .createANewMessage(email)
                .openDraftsFolder();
        assertTrue(draftsPage.isDraftWithSubjectPresent(email.getSubject()), "Draft theme doesn't match");
        draftsPage.openDraft(email.getSubject());

        List<String> expectedLetterData = List.of(
                email.getDestinationEmail(),
                email.getSubject(),
                email.getBody()
        );
        assertEquals(draftsPage.getLetterData(), expectedLetterData, "Draft content is incorrect");
    }

    @Test
    public void testSendEmailFromDrafts() {
        DraftsPage draftsPage = new LoginPage()
                .openPage()
                .logIn(user)
                .createANewMessage(email)
                .openDraftsFolder()
                .openDraft(email.getSubject())
                .sendEmail();
        assertTrue(draftsPage.isEmailSentNotificationDisplayed(), "Draft was not send");
        SentPage sentPage = draftsPage.openSentFolder();
        assertEquals(sentPage.checkLastEmail(), email.getSubject(), "Email is not found in Sent folder");
    }
}

