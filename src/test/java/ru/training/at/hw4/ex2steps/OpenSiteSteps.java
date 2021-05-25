package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class OpenSiteSteps {

    private final FileInputStream fileInputStream;
    Properties prop = new Properties();

    public OpenSiteSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }

    @Step("Open Test Site")
    public void openTestSite(WebDriver driver) {
        driver.get(prop.getProperty("Homepage"));
        Assert.assertEquals(driver.getCurrentUrl(),
                prop.getProperty("Homepage"));
    }

    @Step("Title Assertion")
    public void browserTitleAssertion(WebDriver driver) {
        Assert.assertEquals(driver.getTitle(), prop.getProperty("Title"));
    }

}