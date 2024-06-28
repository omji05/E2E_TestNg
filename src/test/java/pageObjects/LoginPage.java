package pageObjects;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Action;

public class LoginPage extends BaseClass {

    Action action = new Action();

    @FindBy(name = "username")
    private WebElement usernameTxtBox;

    @FindBy(name="password")
    private WebElement passwordTxtBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage(){
        PageFactory.initElements(getDriver(),this);
    }

    public DashboardPage login(String username, String password){
        action.type(usernameTxtBox,username);
        action.type(passwordTxtBox,password);
        action.click(loginButton);
        return new DashboardPage();
    }
}
