package org.example.module08_1.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.module08_1.model.Email;
import org.example.module08_1.model.User;
import org.example.module08_1.pages.DraftsPage;
import org.example.module08_1.pages.LoginPage;
import org.example.module08_1.pages.MainPage;
import org.example.module08_1.pages.SentPage;
import org.example.module08_1.util.ConfigUserReader;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class EmailSteps {

    private User user;
    private LoginPage loginPage;
    private MainPage mainPage;
    private DraftsPage draftsPage;

    @Given("I open the ProtonMail login page")
    public void iOpenTheProtonMailLogiPage() {
        user = ConfigUserReader.getUserData();
        loginPage = new LoginPage().openPage();
    }

    @Given("I log in with valid credentials")
    public void iLogInWithValidCredentials() {
        mainPage = loginPage.logIn(user);
    }

    @When("I create a new message with recipient {string}, subject {string}, and body {string}")
    public void iCreateANewMessageWithRecipientSubjectAndBody(String destination, String subject, String body) {
        mainPage.createANewMessage(new Email(destination,subject,body));
    }

    @When("I open the Drafts folder")
    public void iOpenTheDraftsFolder() {
        draftsPage = mainPage.openDraftsFolder();
    }

    @When("I should see the draft with subject {string}")
    public void iShouldSeeTheDraftWithSubject(String subject) {
        draftsPage.isDraftWithSubjectPresent(subject);
        draftsPage.openDraft(subject);
    }

    @Then("the draft content should match {string}, {string}, and {string}")
    public void theDraftContentShouldMatchAnd(String destination, String subject, String body) {
        List<String> expectedLetterData = List.of(
                destination,
                subject,
                body
        );
        assertEquals(draftsPage.getLetterData(), expectedLetterData, "Draft content is incorrect");
    }

    @When("I send email")
    public void iSendEmail() {
        draftsPage.sendEmail();
        draftsPage.isEmailSentNotificationDisplayed();
    }

    @Then("I should see that email is present with subject {string}")
    public void iShouldSeeThatEmailWasSend(String subject) {
        SentPage sentPage = draftsPage.openSentFolder();
        assertEquals(sentPage.checkLastEmail(), subject, "Email is not found in Sent folder");
    }
}
