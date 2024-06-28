package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static Properties prop;

    //private for encapsulation and non-final to facilitate ThreadLocal concept by updating value of driver specific to thread
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    //to read the configuration properties
    @BeforeSuite
    public void loadConfig() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/config/configuration.properties");
            prop.load(ip);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RemoteWebDriver getDriver(){
        return driver.get();
    }

    public void launchApp(String browserName) {
        // String browserName = prop.getProperty("browser");
        try {
            //from Selenium Grid 4 we can only give URL till port i.e. http://localhost:4444
            URL gridUrl = new URL("http://localhost:4444/wd/hub");
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
//                WebDriverManager.chromedriver().setup();
                // Set Browser to ThreadLocalMap
//                driver.set(new ChromeDriver());
                driver.set(new RemoteWebDriver(gridUrl,options));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
//                WebDriverManager.firefoxdriver().setup();
//                driver.set(new FirefoxDriver());
                driver.set(new RemoteWebDriver(gridUrl,options));
            } else if (browserName.equalsIgnoreCase("IE")) {
                InternetExplorerOptions options = new InternetExplorerOptions();
//                WebDriverManager.iedriver().setup();
//                driver.set(new InternetExplorerDriver());
                driver.set(new RemoteWebDriver(gridUrl,options));
            }
        }catch (MalformedURLException e){
            Log.info("Exception Occured in url creation");
        }
        //Maximize the screen
        getDriver().manage().window().maximize();
        //Delete all the cookies
        getDriver().manage().deleteAllCookies();
        //Implicit TimeOuts
        getDriver().manage().timeouts().implicitlyWait
                (Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
        //PageLoad TimeOuts
        getDriver().manage().timeouts().pageLoadTimeout
                (Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
        //Launching the URL
        getDriver().get(prop.getProperty("url"));
    }

//    @AfterSuite
//    public void afterSuite() {
//        ExtentManager.endReport();
//    }






}
