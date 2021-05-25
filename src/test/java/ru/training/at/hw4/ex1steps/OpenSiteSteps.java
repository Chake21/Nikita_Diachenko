package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

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

    SoftAssert softly = new SoftAssert();

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