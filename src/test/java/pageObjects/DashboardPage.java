package pageObjects;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Action;

public class DashboardPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    private WebElement userDropdown;

    @FindBy(xpath = "//li[@class='oxd-main-menu-item-wrapper']//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath="//span[@class='oxd-topbar-header-breadcrumb']//h6[text()='Leave']")
    private WebElement leavePageHeader;

    public DashboardPage(){
        PageFactory.initElements(getDriver(),this);
    }

    public void validateUrl(String expectedUrl){
        String actualUrl = getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
        try {
            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println("Exception at thread sleep occured");
        }

    }

    public LeaveListPage navigateToLeaveMenu(){
        action.isDisplayedWithWait(leaveMenu,10);
        action.click(leaveMenu);
        Assert.assertTrue(action.isDisplayedWithWait(leavePageHeader,10));
        return new LeaveListPage();
    }
}
