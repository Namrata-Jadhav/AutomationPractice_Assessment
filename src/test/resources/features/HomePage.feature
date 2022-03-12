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
    Then application logo is displayed with width as "350" and height as "99"

  @TC3
  Scenario: application product main category list validation
    When user is on application landing page
    Then product category list is displayed with count 3

  @TC4
  Scenario: Application Search functionality
    When user enters text as T-shirts in search box
    Then search result should contains T-shirts as text

  @TC5
  Scenario: Application social media handles validation
    When user clicks on twitter link
    Then new tab is opened And shows account name as Selenium Framework

