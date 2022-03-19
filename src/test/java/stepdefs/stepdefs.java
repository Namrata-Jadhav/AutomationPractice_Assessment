package stepdefs;

import PageObjects.LandingPageObjects;
import core.webDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class stepdefs {

    WebDriver driver;
    String url = "http://automationpractice.com";
    Scenario scn;

    LandingPageObjects landingPageObjects;

    private static final Logger logger = LogManager.getLogger(stepdefs.class);

    @Before
    public void setUp(Scenario scn) throws Exception {
        this.scn = scn;

        logger.info("chrome initialized");

        String browserName = webDriverFactory.getBrowserName(); //get browser name by default chrome
        driver = webDriverFactory.getWebDriverForBrowser(browserName);

       landingPageObjects = new  LandingPageObjects(driver);

    }

    @After(order = 1)
    public void cleanUp() {
        driver.quit();
       logger.info("browser closed");

    }

    @After(order = 2)
    public void takeScreenShot(Scenario scn) {
        if (scn.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot) driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png", "Failed step name: " + scn.getName());
            logger.info("screenshot attached");
        } else {
            scn.log("Test cases are passed...No screen shot is taken");
        }
    }


    @Given("user navigate to the application url")
    public void user_navigate_to_the_application_url() {
        driver.get(url);
       // System.out.println("application url is: "+url);
        logger.info("navigated to the url " +url);
    }


    @When("user is on application landing page")
    public void user_is_on_application_landing_page() {
       landingPageObjects.landingPageIsDisplayed();
       logger.info("landing page is displayed");
    }


    @Then("user should be redirected to {string}")
    public void user_should_be_redirected_to(String expected_url) {
        Assert.assertEquals("Redirected url is: "+expected_url,expected_url, driver.getCurrentUrl());
        System.out.println("Redirected url is: "+expected_url);
        logger.info("redirected url is : "+expected_url);
    }

    @When("user checks for visibility of application logo")
    public void user_checks_for_visibility_of_application_logo() {
        landingPageObjects.applicationLogoIsDisplayed();
        logger.info("application logo is displayed");
    }

    @Then("application logo is displayed with width as {string} and height as {string}")
    public void application_logo_is_displayed_with_width_as_and_height_as(String expected_width, String expected_height) {

       landingPageObjects.applicationLogoWidthHeightIsDisplayed(expected_width,expected_height);
       logger.info("application logo is displayed with width as "+expected_width +"and height as "+expected_height);
    }

    @Then("product main category list count is {int}")
    public void product_main_category_list_count_is(Integer int1) {
       landingPageObjects.countOfMainProductCategoryListIsDisplayed();
       logger.info("product category list is displayed with count "+int1);
    }

    @Then("text for main product categories is shown as {string}")
    public void text_for_main_product_categories_is_shown_as(String productName) {
        logger.info("text of product category list is shown as : "+productName);
        landingPageObjects.productCategoryListIsDisplayed(productName);
    }


    @When("user enters text as {string} in search box")
    public void user_enters_text_as_T_shirts_in_search_box(String text){
       landingPageObjects.EnterTextInSearchBox(text);
       logger.info("text entered in search box is "+text);
    }


    @Then("search result should contains {string} as text")
    public void search_result_should_contains_as_text(String string) {
        landingPageObjects.SearchResultContainsText();
        logger.info("search result contains " + string + " as search text");
    }

    @When("user clicks on twitter link")
    public void user_clicks_on_twitter_link() {
        WebElement twitterLink = driver.findElement(By.xpath("//section/ul/li[@class='twitter']"));
        twitterLink.click();
        logger.info("twitter link is clicked");
    }

    @Then("new tab is opened with new url as {string}")
    public void new_tab_is_opened_with_new_url_as(String expectedUrl) throws InterruptedException {
        landingPageObjects.newTabWithNewUrlIsDisplayed(expectedUrl);
        logger.info("new tab is opened with new url as "+expectedUrl);
    }
    @Then("shows account name as {string}")
    public void shows_account_name_as(String expectedName) throws InterruptedException {
        landingPageObjects.newTabWithAccountNameIsDisplayed(expectedName);
        logger.info("New tab contains account name as "+expectedName);
    }


}
