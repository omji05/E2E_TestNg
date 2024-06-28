package utils;

import base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Action extends BaseClass {


    //@Override
    public void scrollByVisibilityOfElement(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", ele);

    }

    //@Override
    public void click(WebElement ele) {
//        isDisplayedWithWait(ele,15);
        Actions act = new Actions(getDriver());
        act.moveToElement(ele).click().build().perform();

    }


    //check if useful-pending
    //@Override
    public boolean findElement(WebElement ele) {
        boolean flag = false;
        try {
            ele.isDisplayed();
            flag = true;
        } catch (Exception e) {
            // System.out.println("Location not found: "+locatorName);
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Successfully Found element at");

            } else {
                System.out.println("Unable to locate element at");
            }
        }
        return flag;
    }

    //@Override
    public boolean isDisplayed(WebElement ele) {
        boolean flag = false;
        flag = findElement(ele);
        if (flag) {
            flag = ele.isDisplayed();
            if (flag) {
                System.out.println("The element is Displayed");
            } else {
                System.out.println("The element is not Displayed");
            }
        } else {
            System.out.println("Not displayed ");
        }
        return flag;
    }

    public boolean isDisplayedWithWait(WebElement element,int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Element found!!");
            return true;
        }catch (TimeoutException e) {
            // Handle the element not being found
            System.out.println("Element not found in given amount of time!");
            return false;
        }

    }

    //@Override
    public boolean isSelected(WebElement ele) {
        boolean flag = false;
        flag = findElement(ele);
        if (flag) {
            flag = ele.isSelected();
            if (flag) {
                System.out.println("The element is Selected");
            } else {
                System.out.println("The element is not Selected");
            }
        } else {
            System.out.println("Not selected ");
        }
        return flag;
    }

    //@Override
    public boolean isEnabled(WebElement ele) {
        boolean flag = false;
        flag = findElement(ele);
        if (flag) {
            flag = ele.isEnabled();
            if (flag) {
                System.out.println("The element is Enabled");
            } else {
                System.out.println("The element is not Enabled");
            }
        } else {
            System.out.println("Not Enabled ");
        }
        return flag;
    }

    /*
     * Type text at location
     *
     * @param locatorName
     * @param text
     * @return - true/false
     */
    //@Override
    public boolean type(WebElement ele, String text) {
        boolean flag = false;
        try {
            flag = ele.isDisplayed();
            ele.clear();
            ele.sendKeys(text);
            // logger.info("Entered text :"+text);
            flag = true;
        } catch (Exception e) {
            System.out.println("Location Not found");
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Successfully entered value");
            } else {
                System.out.println("Unable to enter value");
            }

        }
        return flag;
    }

    //@Override
    public boolean selectBySendkeys(String value,WebElement ele) {
        boolean flag = false;
        try {
            ele.sendKeys(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Select value from the DropDown");
            } else {
                System.out.println("Not Selected value from the DropDown");
                // throw new ElementNotFoundException("", "", "")
            }
        }
    }

    /*
      select value from DropDown by using selectByIndex

      @param locator     : Action to be performed on element (Get it from Object
                         repository)

      @param index       : Index of value wish to select from dropdown list.

      @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
                         Listbox etc..)

     */
    //@Override
    public boolean selectByIndex(WebElement element, int index) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByIndex(index);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by Index");
            } else {
                System.out.println("Option not selected by Index");
            }
        }
    }

    /*
     * select value from DD by using value
     *
     * @param locator     : Action to be performed on element (Get it from Object
     *                    repository)
     *
     * @param value       : Value wish to select from dropdown list.
     *
     * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
     *                    Listbox etc..)
     */

    //@Override
    public boolean selectByValue(WebElement element,String value) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByValue(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by Value");
            } else {
                System.out.println("Option not selected by Value");
            }
        }
    }

    /*
     * select value from DropDown by using selectByVisibleText
     *
     * @param locator     : Action to be performed on element (Get it from Object
     *                    repository)
     *
     * @param visibletext : VisibleText wish to select from dropdown list.
     *
     * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
     *                    Listbox etc..)
     */

    //@Override
    public boolean selectByVisibleText(String visibletext, WebElement ele) {
        boolean flag = false;
        try {
            Select s = new Select(ele);
            s.selectByVisibleText(visibletext);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by VisibleText");
            } else {
                System.out.println("Option not selected by VisibleText");
            }
        }
    }

    //select from list
    public void selectValueFromListDropdown(List<WebElement> element,String optionText) throws InterruptedException {
        boolean flag =false;
        for(WebElement ele:element)
        {
            System.out.println(ele.getText());
//            Thread.sleep(5000);
            if(ele.getText().equalsIgnoreCase(optionText)){
                ele.click();
                flag = true;
                break;
            }
            else
                continue;
        }
        if(flag){
            System.out.println("Value is selected from the list");
        }
        else if(!flag){
            System.out.println("Value to select is not found in the list");
        }

    }


    //@Override
    public boolean mouseHoverByJavaScript(WebElement ele) {
        boolean flag = false;
        try {
            WebElement mo = ele;
            String javaScript = "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                    + "arguments[0].dispatchEvent(evObj);";
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(javaScript, mo);
            flag = true;
            return true;
        }

        catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("MouseOver Action is performed");
            } else {
                System.out.println("MouseOver Action is not performed");
            }
        }
    }

    //@Override
    public boolean JSClick(WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", ele);
            // driver.executeAsyncScript("arguments[0].click();", element);

            flag = true;

        }

        catch (Exception e) {
            throw e;

        } finally {
            if (flag) {
                System.out.println("Click Action is performed");
            } else if (!flag) {
                System.out.println("Click Action is not performed");
            }
        }
        return flag;
    }

    //check here if getDriver works correctly with explicit wait ans switchTo method
    //@Override
    public boolean switchToFrameByIndex(int index) {
        boolean flag = false;
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
            getDriver().switchTo().frame(index);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with index \"" + index + "\" is selected");
            } else {
                System.out.println("Frame with index \"" + index + "\" is not selected");
            }
        }
    }

    /**
     * This method switch the to frame using frame ID.
     *
     * @param idValue : Frame ID wish to switch
     *
     */
    //@Override
    public boolean switchToFrameById(String idValue) {
        boolean flag = false;
        try {
            getDriver().switchTo().frame(idValue);
            flag = true;
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with Id \"" + idValue + "\" is selected");
            } else {
                System.out.println("Frame with Id \"" + idValue + "\" is not selected");
            }
        }
    }

    /**
     * This method switch the to frame using frame Name.
     *
     * @param nameValue : Frame Name wish to switch
     *
     */
    //@Override
    public boolean switchToFrameByName(String nameValue) {
        boolean flag = false;
        try {
            getDriver().switchTo().frame(nameValue);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with Name \"" + nameValue + "\" is selected");
            } else if (!flag) {
                System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
            }
        }
    }

    //@Override
    public boolean switchToDefaultFrame() {
        boolean flag = false;
        try {
            getDriver().switchTo().defaultContent();
            flag = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                // SuccessReport("SelectFrame ","Frame with Name is selected");
            } else if (!flag) {
                // failureReport("SelectFrame ","The Frame is not selected");
            }
        }
    }

    //@Override
    public void mouseOverElement(WebElement element) {
        boolean flag = false;
        try {
            new Actions(getDriver()).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println(" MouserOver Action is performed on ");
            } else {
                System.out.println("MouseOver action is not performed on");
            }
        }
    }

    //@Override
    public boolean moveToElement( WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            Actions actions = new Actions(getDriver());
            // actions.moveToElement(driver.findElement(locator)).build().perform();
            actions.moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //@Override
    public boolean mouseover( WebElement ele) {
        boolean flag = false;
        try {
            new Actions(getDriver()).moveToElement(ele).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            /*
             * if (flag) {
             * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
             * +"\""); } else {
             * failureReport("MouseOver","MouseOver action is not performed on \""
             * +locatorName+"\""); }
             */
        }
    }
    //@Override
    public boolean draggable(WebElement source, int x, int y) {
        boolean flag = false;
        try {
            new Actions(getDriver()).dragAndDropBy(source, x, y).build().perform();
            Thread.sleep(5000);
            flag = true;
            return true;

        } catch (Exception e) {

            return false;

        } finally {
            if (flag) {
                System.out.println("Draggable Action is performed on \""+source+"\"");
            } else if(!flag) {
                System.out.println("Draggable action is not performed on \""+source+"\"");
            }
        }
    }
    //@Override
    public boolean draganddrop(WebElement source, WebElement target) {
        boolean flag = false;
        try {
            new Actions(getDriver()).dragAndDrop(source, target).perform();
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("DragAndDrop Action is performed");
            } else if(!flag) {
                System.out.println("DragAndDrop Action is not performed");
            }
        }
    }

    //@Override
    public boolean slider(WebElement ele, int x, int y) {
        boolean flag = false;
        try {
            // new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
            // .perform();
            new Actions(getDriver()).dragAndDropBy(ele, x, y).build().perform();// 150,0
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Slider Action is performed");
            } else {
                System.out.println("Slider Action is not performed");
            }
        }
    }

    //@Override
    public boolean rightclick(WebElement ele) {
        boolean flag = false;
        try {
            Actions clicker = new Actions(getDriver());
            clicker.contextClick(ele).perform();
            flag = true;
            return true;
            // driver.findElement(by1).sendKeys(Keys.DOWN);
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("RightClick Action is performed");
            } else {
                System.out.println("RightClick Action is not performed");
            }
        }
    }

    //@Override
    public boolean switchWindowByTitle(String windowTitle, int count) {
        boolean flag = false;
        try {
            Set<String> windowList = getDriver().getWindowHandles();

            String[] array = windowList.toArray(new String[0]);

            getDriver().switchTo().window(array[count-1]);

            if (getDriver().getTitle().contains(windowTitle)){
                flag = true;
            }else{
                flag = false;
            }
            return flag;
        } catch (Exception e) {
            //flag = true;
            return false;
        } finally {
            if (flag) {
                System.out.println("Navigated to the window with title");
            } else {
                System.out.println("The Window with title is not Selected");
            }
        }
    }
    //@Override
    public boolean switchToNewWindow() {
        boolean flag = false;
        try {

            Set<String> s=getDriver().getWindowHandles();
            Object popup[]=s.toArray();
            getDriver().switchTo().window(popup[1].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                System.out.println("Window is Navigated with title");
            } else {
                System.out.println("The Window with title: is not Selected");
            }
        }
    }
    //@Override
    public boolean switchToOldWindow() {
        boolean flag = false;
        try {

            Set<String> s=getDriver().getWindowHandles();
            Object popup[]=s.toArray();
            getDriver().switchTo().window(popup[0].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                System.out.println("Focus navigated to the window with title");
            } else {
                System.out.println("The Window with title: is not Selected");
            }
        }
    }
    //@Override
    public int getColumncount(WebElement row) {
        List<WebElement> columns = row.findElements(By.tagName("td"));
        int a = columns.size();
        System.out.println(columns.size());
        for (WebElement column : columns) {
            System.out.print(column.getText());
            System.out.print("|");
        }
        return a;
    }

    //@Override
    public int getRowCount(WebElement table) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int a = rows.size() - 1;
        return a;
    }


    /**
     * Verify alert present or not
     *
     * @return: Boolean (True: If alert preset, False: If no alert)
     *
     */
    //@Override
    public boolean Alert() {
        boolean presentFlag = false;
        Alert alert = null;

        try {
            // Check the presence of alert
            alert = getDriver().switchTo().alert();
            // if present consume the alert
            alert.accept();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            // Alert present; set the flag

            // Alert not present
            ex.printStackTrace();
        } finally {
            if (!presentFlag) {
                System.out.println("The Alert is handled successfully");
            } else{
                System.out.println("There was no alert to handle");
            }
        }

        return presentFlag;
    }
    //@Override
    public boolean launchUrl(String url) {
        boolean flag = false;
        try {
            getDriver().navigate().to(url);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Successfully launched \""+url+"\"");
            } else {
                System.out.println("Failed to launch \""+url+"\"");
            }
        }
    }

    //@Override
    public boolean isAlertPresent()
    {
        try
        {
            getDriver().switchTo().alert();
            return true;
        }   // try 
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch 
    }

    //@Override
    public String getTitle() {
        boolean flag = false;

        String text = getDriver().getTitle();
        if (flag) {
            System.out.println("Title of the page is: \""+text+"\"");
        }
        return text;
    }

    //@Override
    public String getCurrentURL()  {
        boolean flag = false;

        String text = getDriver().getCurrentUrl();
        if (flag) {
            System.out.println("Current URL is: \""+text+"\"");
        }
        return text;
    }

    //@Override
    public boolean click1(WebElement locator, String locatorName) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Able to click on \""+locatorName+"\"");
            } else {
                System.out.println("Click Unable to click on \""+locatorName+"\"");
            }
        }

    }

    public boolean click2(WebElement locator) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Able to click");
            } else {
                System.out.println("Click Unable to click");
            }
        }

    }

    //@Override
    public void fluentWait(WebElement element, int timeOut) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<>((WebDriver) getDriver())
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        }catch(Exception e) {
            System.out.println("Exception in fluent wait");
        }
    }
    //@Override
    public void implicitWait( int timeOut) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }
    //@Override
    public void explicitWait( WebElement element, int timeOut ) {
        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    //@Override
    public void pageLoadTimeOut( int timeOut) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
    }
    //@Override
    public String screenShot( String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/ScreenShots/" + filename + "_" + dateName + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
//        String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
//                + dateName + ".png";
//        return newImageString;
        return "../../ScreenShots/"+filename+"_" + dateName + ".png";
    }
    //@Override
    public String getCurrentTime() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
        return currentDate;
    }
}
