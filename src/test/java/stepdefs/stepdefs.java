package stepdefs;

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

    private static final Logger logger = LogManager.getLogger(stepdefs.class);

    @Before
    public void setUp(Scenario scn) throws Exception {
        this.scn = scn;

        String browserName = webDriverFactory.getBrowserName(); //get browser name by default chrome
        driver = webDriverFactory.getWebDriverForBrowser(browserName);

    }

    @Given("user opened the browser")
    public void user_opened_the_browser() {
        driver = new ChromeDriver();
        logger.info("chrome initialized");

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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


    @Given("user navigated to application url")
    public void user_navigated_to_application_url() {
        driver.get(url);
        System.out.println("application url is: "+url);
        logger.info("navigated to the url " +url);
    }

    @Then("user should be redirected to {string}")
    public void user_should_be_redirected_to(String expected_url) {
        Assert.assertEquals("Redirected url is: "+expected_url,expected_url, driver.getCurrentUrl());
        System.out.println("Redirected url is: "+expected_url);
        logger.info("redirected url is : "+expected_url);
    }

    @Then("application logo is displayed with width as {string} and height as {string}")
    public void application_logo_is_displayed_with_width_as_and_height_as(String expected_width, String expected_height) {
        boolean b = driver.findElement(By.xpath("//img[@width='350' and @height='99']")).isDisplayed();
        Assert.assertEquals("application logo is displayed", true, b);
        logger.info("application logo is displayed with width as "+expected_width + "and height as "+expected_height);

    }

    @Then("product category list is displayed")
    public void product_category_list_is_displayed() {

        ArrayList <String> expectedCategoryList = new ArrayList<>();
        expectedCategoryList.add("WOMEN");
        expectedCategoryList.add("DRESSES");
        expectedCategoryList.add("T-SHIRTS");

        List<WebElement> productCategoryList = driver.findElements(By.xpath("//div[@id='block_top_menu']/ul/li/a"));
        for (int count = 0; count < expectedCategoryList.size(); count++) {

            System.out.println((count+1)+" " + expectedCategoryList.get(count));
                Assert.assertEquals("Product index no " + (count+1) + " is not matching with expected",expectedCategoryList.get(count),productCategoryList.get(count).getText());
            }
       logger.info("category list is displayed");
        }

    @When("user enters text as T-shirts in search box")
    public void user_enters_text_as_T_shirts_in_search_box(){
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        searchBox.sendKeys("T-shirts");
        logger.info(searchBox+" is entered as text in search box");
    }

    @Then("search result should contains T-shirts as text")
    public void search_result_should_contains_t_shirts_as_text() {
        WebElement text= driver.findElement(By.xpath("//div[@class='ac_results']//ul//li"));
        Assert.assertEquals(true,text.isDisplayed());
        logger.info("search result shows :"+text);
    }
    @When("user clicks on twitter link")
    public void user_clicks_on_twitter_link() {
        WebElement twitterLink = driver.findElement(By.xpath("//section/ul/li[@class='twitter']"));
        twitterLink.click();
    logger.info("twitter link is clicked");
    }

    @Then("new tab is opened And shows account name as Selenium Framework")
    public void new_tab_is_opened_and_shows_account_name_as_selenium_framework() throws InterruptedException {

        System.out.println("current page title is: "+driver.getTitle());

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        String parentId = it.next();
        String childId = it.next();

        driver.switchTo().window(childId);
        logger.info("switched to new tab");

        Thread.sleep(5000);

        WebElement accName = driver.findElement(By.xpath("//span[contains(text(),'Selenium Framework')]"));
      String an=  accName.getText();
        Assert.assertEquals("Selenium Framework",an);
        logger.info("account name is displayed as "+an);

        String actualtitle = driver.getTitle();
        Assert.assertEquals("Selenium Framework (@seleniumfrmwrk) / Twitter", actualtitle);
        logger.info("current page title is "+actualtitle);

    }




}
