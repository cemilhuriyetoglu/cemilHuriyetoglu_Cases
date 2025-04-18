package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Listeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        //specify location of the report
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/PetStoreReport.html");

        htmlReporter.config().setDocumentTitle("PetStore API Automation Report"); // Tile of report
        htmlReporter.config().setReportName("PetStore API Automation Report"); // name of the report
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "PetStore Api");
        extent.setSystemInfo("Host name", "Test");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("user", "Tester");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getName()); // create new entry in the report

        test.log(Status.PASS, result.getName() + "  >>  PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in the report

        test.log(Status.FAIL, result.getName() + "  >>  FAILED"); // to add name in extent report
        test.log(Status.FAIL, result.getThrowable()); // to add error/exception in extent report

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // create new entry in the report
        test.log(Status.SKIP, result.getName() + "  >>  SKIPPED");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}