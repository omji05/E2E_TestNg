package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
//    public static ExtentTest test;

//    public static void setExtent() {
//        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport.html");
//        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
//
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//
//        extent.setSystemInfo("HostName", "MyHost");
//        extent.setSystemInfo("ProjectName", "MyStoreProject");
//        extent.setSystemInfo("Tester", "Hitendra");
//        extent.setSystemInfo("OS", "Win10");
//        extent.setSystemInfo("Browser", "Chrome");
//    }
    public static void createInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            sparkReporter = new ExtentSparkReporter("target/test-output/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Report");
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name", "Tester");
        }
//        return extent;
    }

    public static ExtentReports getExtentReport(){
        return extent;
    }

//    public static ExtentTest createTest(String testName, String testDescription) {
//        test = extent.createTest(testName, testDescription);
//        return test;
//    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
