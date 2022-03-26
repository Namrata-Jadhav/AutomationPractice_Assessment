Feature: Application landing Page Feature

  Background:
    Given user navigate to the application url

  @TC1
  Scenario: Application URl redirection
    When user is on application landing page
    Then user should be redirected to "http://automationpractice.com/index.php"

  @TC2
  Scenario: application logo validation
    When user checks for visibility of application logo
    Then application logo is displayed with width as "35" and height as "99"

  @TC3
  Scenario Outline: application product main category list validation
    When user is on application landing page
    Then product main category list count is 3
    And text for main product categories is shown as "<product name>"
    Examples:
      | product name |
      | WOMEN        |
      | DRESSES      |
      | T-SHIRTS     |


  @TC4
  Scenario: Application Search functionality
    When user enters text as "T-shirts" in search box
    Then search result should contains "T-shirts" as text

  @TC5
  Scenario: Application social media handles validation
    When user clicks on twitter link
    Then new tab is opened with new url as "https://twitter.com/seleniumfrmwrk"
    And shows account name as "Selenium Framework"

