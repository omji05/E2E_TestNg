package testcases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.Log;

public class LoginTest extends BaseClass {

    DashboardPage dashboardPage;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }


    @Test
    public void loginTest1(){
        Log.startTestCase("loginTest1");
        LoginPage loginPage=new LoginPage();
        dashboardPage = loginPage.login("Admin","admin123");
        Log.info("Entered Username and Password loginTest1");
        dashboardPage.validateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Log.endTestCase("loginTest1");
    }

    @Test
    public void loginTest2(){
        Log.startTestCase("loginTest2");
        LoginPage loginPage=new LoginPage();
        dashboardPage = loginPage.login("Admin","admin123");
        Log.info("Entered Username and Password loginTest2");
        dashboardPage.validateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Log.endTestCase("loginTest2");
        Assert.assertTrue(false);
    }

//    @Test
//    public void loginTest3(){
//        Log.startTestCase("loginTest3");
//        LoginPage loginPage=new LoginPage();
//        dashboardPage = loginPage.login("Admin","admin123");
//        dashboardPage.validateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
//        Log.endTestCase("loginTest3");
//    }
}
