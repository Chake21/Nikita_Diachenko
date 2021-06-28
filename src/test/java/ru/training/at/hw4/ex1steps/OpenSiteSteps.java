package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import static ru.training.at.hw4.Ex1Steps.prop;

public class OpenSiteSteps {

    public OpenSiteSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Open Test Site")
    public void openTestSite(SoftAssert softly, WebDriver driver) {
        driver.get(prop.getProperty("Homepage"));
        softly.assertEquals(driver.getCurrentUrl(),
                prop.getProperty("Homepage"));
    }

    @Step("Title Assertion")
    public void titleAssertion(SoftAssert softly, WebDriver driver) {
        softly.assertEquals(driver.getTitle(), prop.getProperty("Title"));
    }

}