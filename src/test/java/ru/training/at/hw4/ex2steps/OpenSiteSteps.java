package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static ru.training.at.hw4.Ex1Steps.prop;

public class OpenSiteSteps {


    public OpenSiteSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
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