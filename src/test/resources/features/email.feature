# Created by zecqeem at 30.03.2026
@regression
Feature: Email sending

  Background:
    Given I open the ProtonMail login page
    And I log in with valid credentials

  Scenario Outline: Create and Send Email From Drafts
    When I create a new message with recipient "<recipient>", subject "<subject>", and body "<body>"
    And I open the Drafts folder
    And I should see the draft with subject "<subject>"
    And I send email
    Then I should see that email is present with subject "<subject>"
    Examples:
      | recipient          | subject           | body                     |
      | zecqeem@gmail.com  | test              | test                     |
      | zecqeem@gmail.com  | messi             | messi                    |

  @smoke
  Scenario Outline: Create and verify a draft email
    When I create a new message with recipient "<recipient>", subject "<subject>", and body "<body>"
    And I open the Drafts folder
    Then I should see the draft with subject "<subject>"
    And the draft content should match "<recipient>", "<subject>", and "<body>"

    Examples:
      | recipient          | subject           | body                     |
      | zecqeem@gmail.com  | test              | test                     |
      | zecqeem@gmail.com  | messi             | messi                    |
