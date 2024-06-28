package pageObjects;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import utils.Action;

import java.util.List;

public class ApplyLeavePage extends BaseClass {
    Action action = new Action();

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    private WebElement leaveTypeDropdown;

//    @FindBy(xpath = "//div[@class='oxd-select-wrapper']")
//    private WebElement expandedList;

    @FindBy(xpath = "//div[@class='oxd-select-wrapper']//div[@role='option']")
    private List<WebElement> listOptions;

    @FindBy(xpath = "//label[text()='From Date']/parent::div/following-sibling::div//div[@class='oxd-date-input']")
    private WebElement fromDatePicker;

    @FindBy(xpath = "//label[text()='To Date']/parent::div/following-sibling::div//div[@class='oxd-date-input']")
    private WebElement toDatePicker;

    @FindBy(xpath = "//div[@class='oxd-date-input-link --today']")
    private WebElement selectToday;

    @FindBy(xpath = "//textarea[contains(@class,'oxd-textarea')]")
    private WebElement commentBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement applyButton;

    @FindBy(id = "//div[@id='oxd-toaster_1']//p[text()='Success']")
    private WebElement toasterSuccess;

    public ApplyLeavePage(){
        PageFactory.initElements(getDriver(),this);
    }



    public void applyLeave(String leaveType,String comment) throws InterruptedException {
        action.click2(leaveTypeDropdown);
//        action.selectValueFromListDropdown(expandedList,leaveType);
        action.selectValueFromListDropdown(listOptions,leaveType);
        action.click(fromDatePicker);
        action.click(selectToday);
        action.click(toDatePicker);
        action.click(selectToday);
        action.type(commentBox,comment);
//        action.click(applyButton);
//        Assert.assertTrue(action.isDisplayedWithWait(toasterSuccess,15));

    }


}
