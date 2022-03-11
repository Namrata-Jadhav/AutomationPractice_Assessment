package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LandingPageObjects {

    private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);

    WebDriver driver;

    private By logoWidthHeight = By.xpath("//img[@width='350' and @height='99']");
    private By productList = By.xpath("//div[@id='block_top_menu']/ul/li/a");
    private By SearchBox = By.id("search_query_top");
    private By searchText = By.xpath("//div[@class='ac_results']//ul//li");
    private By twitterLink = By.xpath("//section/ul/li[@class='twitter']");
    private By accName = By.xpath("//span[contains(text(),'Selenium Framework')]");

    public LandingPageObjects(WebDriver driver)
    {
        this.driver= driver;
    }

    public void applicationLogoWidthHeightIsDisplayed(String w , String h){
        boolean b = driver.findElement(logoWidthHeight).isDisplayed();
        Assert.assertEquals("application logo is displayed", true, b);
        logger.info("application logo is displayed with width as "+w + "and height as "+h);
    }

    public void productCategoryListIsDisplayed(){
        ArrayList<String> expectedCategoryList = new ArrayList<>();
        expectedCategoryList.add("WOMEN");
        expectedCategoryList.add("DRESSES");
        expectedCategoryList.add("T-SHIRTS");

        List<WebElement> productCategoryList = driver.findElements(productList);
        for (int count = 0; count < expectedCategoryList.size(); count++) {

            System.out.println((count+1)+" " + expectedCategoryList.get(count));
            Assert.assertEquals("Product index no " + (count+1) + " is not matching with expected",expectedCategoryList.get(count),productCategoryList.get(count).getText());
        }
        logger.info("category list is displayed");
    }

    public void EnterTextInSearchBox(){
        WebElement searchBar = driver.findElement(SearchBox);
        searchBar.sendKeys("T-shirts");
        logger.info(searchBar+" is entered as text in search box");
    }

    public void SearchResultContainsText(){
        WebElement text= driver.findElement(searchText);
        Assert.assertEquals(true,text.isDisplayed());
        logger.info("search result shows :"+text);
    }

    public void clickOnTwitterLink(){
        WebElement tLink = driver.findElement(twitterLink);
        tLink.click();
        logger.info("twitter link is clicked");
    }

    public void newTabWithAccountNameIsDisplayed() throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        String parentId = it.next();
        String childId = it.next();

        driver.switchTo().window(childId);
        logger.info("switched to new tab");

        Thread.sleep(5000);

        WebElement accountName = driver.findElement(accName);
        String an=  accountName.getText();
        Assert.assertEquals("Selenium Framework",an);
        logger.info("account name is displayed as "+an);

        String actualtitle = driver.getTitle();
        Assert.assertEquals("Selenium Framework (@seleniumfrmwrk) / Twitter", actualtitle);
        logger.info("current page title is "+actualtitle);

    }

}
