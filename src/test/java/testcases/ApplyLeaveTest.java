package testcases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.ApplyLeavePage;
import pageObjects.DashboardPage;
import pageObjects.LeaveListPage;
import pageObjects.LoginPage;
import utils.Log;

public class ApplyLeaveTest extends BaseClass {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    LeaveListPage leaveListPage;
    ApplyLeavePage applyLeavePage;

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
    public void applyLeave() throws InterruptedException {
        Log.startTestCase("applyLeave");
        loginPage=new LoginPage();
        dashboardPage = loginPage.login("Admin","admin123");
        Log.info("Entered Username and Password ap");
//        dashboardPage.validateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        leaveListPage = dashboardPage.navigateToLeaveMenu();
        applyLeavePage = leaveListPage.navigateToApplyLeavePage();
        Log.info("Navigated to leave page ap");
        applyLeavePage.applyLeave("CAN - FMLA","This is test Comment");
        Log.endTestCase("applyLeave");
    }


    @Test
    public void applyLeave2() throws InterruptedException {
        Log.startTestCase("applyLeave2");
        loginPage=new LoginPage();
        dashboardPage = loginPage.login("Admin","admin123");
        Log.info("Entered Username and Password ap2");
        dashboardPage.validateUrl("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        leaveListPage = dashboardPage.navigateToLeaveMenu();
        applyLeavePage = leaveListPage.navigateToApplyLeavePage();
        Log.info("Navigated to leave page ap2");
        applyLeavePage.applyLeave("CAN - FMLA","This is test Comment");
        Log.endTestCase("applyLeave2");
//        Assert.assertTrue(false);
    }
}
