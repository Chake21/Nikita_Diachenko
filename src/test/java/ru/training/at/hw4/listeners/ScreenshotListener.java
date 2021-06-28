package ru.training.at.hw4.listeners;

import com.google.api.client.util.DateTime;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.AttachmentUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalTime;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ScreenshotListener implements ITestListener {

    int count = 0;

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("EEE: onTestSuccess.enter " + result.getName());
        Object webDriver = result.getTestContext().getAttribute("webDriver");
        System.out.println(webDriver);
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("hi");
            File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                File screenshot = new File("build/screenshots/" + "goodTestScreenshot" + "_"
                        + count + ".png");
                FileUtils.copyFile(tempFile, screenshot);
                System.err.println("Screenshot saved to: " + screenshot.getCanonicalPath());
                count = count + 1;

            } catch (IOException e) {
                throw new Error(e);
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("EEE: onTestFailure.enter " + result.getName());
        Object webDriver = result.getTestContext().getAttribute("webDriver");
        System.out.println(webDriver);
        if (result.getStatus() == ITestResult.FAILURE) {
            File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                System.out.println(LocalTime.now().toString());
                File screenshot = new File("build/screenshots/" + "failedScreenshot" + "_"
                        + count + ".png");
                FileUtils.copyFile(tempFile, screenshot);
                System.err.println("Screenshot saved to: " + screenshot.getCanonicalPath());
                count = count + 1;
            } catch (IOException e) {
                throw new Error(e);
            }
        }

        if (webDriver != null) {
            AttachmentUtils.makeStringAttachment(Arrays.asList(
                    "test context contains driver:",
                    webDriver.toString()));
        }
        System.out.println("EEE: onTestFailure.exit");
    }

}