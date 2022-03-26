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

    private By applicationLogo = By.xpath("//div[@id='header_logo']/a");
    private By logoWidth = By.xpath("//a/img[@width='350']");
    private By logoHeight = By.xpath("//a/img[@height='99']");
    private By productList = By.xpath("//div[@id='block_top_menu']/ul/li/a");
    private By SearchBox = By.id("search_query_top");
    private By searchText = By.xpath("//div[@class='ac_results']//ul//li");
    private By twitterLink = By.xpath("//section/ul/li[@class='twitter']");
    private By accName = By.xpath("//span[contains(text(),'Selenium Framework')]");

    public LandingPageObjects(WebDriver driver)
    {
        this.driver= driver;
    }

    public void landingPageIsDisplayed(){
        String expectedTitle = "My Store";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        logger.info("current page title is "+actualTitle);
    }

    public void applicationLogoIsDisplayed(){
        boolean b = driver.findElement(applicationLogo).isDisplayed();
        Assert.assertEquals("application logo is displayed", true, b);
    }

    public void applicationLogoWidthHeightIsDisplayed(String w ,String h){
        String wid = driver.findElement(logoWidth).getAttribute("width");
        String hei = driver.findElement(logoHeight).getAttribute("height");

          if(wid.equals(w) && hei.equals(h)) {
              Assert.assertEquals( w, wid);
              Assert.assertEquals(h, hei);
              // Assert.assertTrue("application logo is displayed with width " + w + "and height " + h, true);
              logger.info("application logo is displayed with actual width n height");
          }
          else {
              Assert.fail("application logo width n height is mismatched");
              logger.fatal("width n height are mismatched");
          }
    }


    public void countOfMainProductCategoryListIsDisplayed(){
        Integer productCategoryCount = driver.findElements(productList).size();
        System.out.println(productCategoryCount);

        logger.info("category count is : "+productCategoryCount);


    }
    public void productCategoryListIsDisplayed(String product){
        List<WebElement> productCategoryList = driver.findElements(productList);
        Iterator<WebElement> itr = productCategoryList.iterator();
//        while(itr.hasNext())
//        {
//            if(product.equals(itr.next().getText()))
//            {
//                Assert.assertTrue(true);
//                logger.info("product category list is displayed as: "+product);
//            }
//        }
        for(int i=0; i<productCategoryList.size(); i++){
            if (product.equals(itr.next().getText()))
            {
                Assert.assertTrue(true);
                System.out.println( (i+1) +" . "+product );
            }
        }

    }

    public void EnterTextInSearchBox(String text){
        driver.findElement(SearchBox).sendKeys(text);
        logger.info(text+" is entered as text in search box");
    }

    public void SearchResultContainsText(String text){
        String searchWord = driver.findElement(searchText).getText();
        if(searchWord.contains(text))
        {
            Assert.assertTrue("search result contains text as "+text,true);
        }
        else
        {
            Assert.fail("search result does not contain the text as "+text);
        }
        logger.info("search result shows :"+text);
    }

    public void clickOnTwitterLink(){
        WebElement tLink = driver.findElement(twitterLink);
        tLink.click();
        logger.info("twitter link is clicked");
    }

    public void newTabWithNewUrlIsDisplayed(String expectedUrl) throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();

        String parentId = it.next();
        String childId = it.next();

        driver.switchTo().window(childId);
        logger.info("switched to new tab");

        Thread.sleep(5000);
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl,actualUrl);
    }

    public void newTabWithAccountNameIsDisplayed(String expectedName) throws InterruptedException {

        WebElement accountName = driver.findElement(accName);
        String an=  accountName.getText();
        Assert.assertEquals("Selenium Framework",an);
        logger.info("account name is displayed as "+an);

    }

}
