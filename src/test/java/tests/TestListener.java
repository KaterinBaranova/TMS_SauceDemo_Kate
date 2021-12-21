package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    protected WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("Test '%s' succeed%n", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File distFile = new File("target/screenshots/screenshot_" + result.getName() + ".png");
        try {
            FileUtils.copyFile(file, distFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.printf("Test '%s' skipped%n", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
