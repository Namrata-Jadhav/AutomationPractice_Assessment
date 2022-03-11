package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class webDriverFactory {

        private static final Logger logger = LogManager.getLogger(webDriverFactory.class);

        private static WebDriver driver=null;

        public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
            switch(browser.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    logger.info("Chrome Browser invoked");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    logger.info("Firefox Browser invoked");
                    break;
                default:
                    logger.fatal("enter browser name either chrome or firefox: " + browser);
                    throw new Exception("No such browser is implemented.Browser name sent: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            logger.info("browser is maximized and will implicitly wait till 20 sec");
            return driver;
        }

        public static String getBrowserName(){
            String browserDefault = "chrome";
            String browserSentFromCmd = System.getProperty("browser");

            if (browserSentFromCmd==null){
                return browserDefault;
            }else{
                return browserSentFromCmd;
            }
        }


    }


