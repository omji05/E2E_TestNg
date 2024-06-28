package pageObjects;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Action;

public class LeaveListPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//a[text()='Apply']")
    private WebElement applyLeaveLink;

    public LeaveListPage(){
        PageFactory.initElements(getDriver(),this);
    }

    public ApplyLeavePage navigateToApplyLeavePage(){
        action.click(applyLeaveLink);
        Assert.assertTrue(action.getCurrentURL().contains("applyLeave"));
        return new ApplyLeavePage();
    }




}
