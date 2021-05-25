package ru.training.at.hw4.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.AttachmentUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("EEE: onTestFailure.enter " + result.getName());
        Object webDriver = result.getTestContext().getAttribute("webDriver");
        System.out.println(webDriver);
        if (result.getStatus() == ITestResult.FAILURE) {
            File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                File screenshot = new File("build/screenshots/" + "screenshotName" + ".png");
                FileUtils.copyFile(tempFile, screenshot);
                System.err.println("Screenshot saved to: " + screenshot.getCanonicalPath());
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        if (webDriver != null) {
            AttachmentUtils.makeStringAttachment(Arrays.asList(
                    "test context contains driver:",
                    webDriver.toString()
            ));

        }

        System.out.println("EEE: onTestFailure.exit");
    }

}