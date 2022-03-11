Feature: Home Page Feature

  @TC1
  Scenario: Application URl redirection
    Given user opened the browser
    When user navigated to application url
    Then user should be redirected to "http://automationpractice.com/index.php"

  @TC2
  Scenario: application logo validation
    Given user opened the browser
    When user navigated to application url
    Then application logo is displayed with width as "350" and height as "99"

  @TC3
  Scenario: application product main category list validation
    Given user opened the browser
    When user navigated to application url
    Then product category list is displayed

    @TC4
    Scenario: Application Search functionality
      Given user opened the browser
      When user navigated to application url
      And user enters text as T-shirts in search box
      Then search result should contains T-shirts as text

      @TC5
      Scenario: Application social media handles validation
        Given user opened the browser
        When user navigated to application url
        And user clicks on twitter link
        Then new tab is opened And shows account name as Selenium Framework

