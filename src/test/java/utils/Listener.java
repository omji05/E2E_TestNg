package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

//    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    Action action= new Action();

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.createInstance();
        // Do nothing
    }

    @Override
    public void onFinish(ITestContext context) {
        if (ExtentReportManager.getExtentReport() != null) {
            ExtentReportManager.flush();
        }
//        ExtentReportManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =  ExtentReportManager.getExtentReport().createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, result.getThrowable());
        test.get().log(Status.FAIL,
                MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        test.get().log(Status.FAIL,
                MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        String imgPath = action.screenShot(result.getName());
        System.out.println(imgPath);
        test.get().fail("Screenshot is attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do nothing
    }
}
