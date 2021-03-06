package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.pageobjects.HomePage;

import java.util.Arrays;

import static ru.training.at.hw4.Ex1Steps.prop;

public class HomePageSteps {
    private final HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        homePage = new HomePage(driver);
    }

    @Step("Assertion of count of images")
    public void assertionOfCountOfImages(SoftAssert softly) {
        for (WebElement icon : homePage.getIconsList()) {
            softly.assertTrue(icon.isDisplayed(), prop
                    .getProperty("AllertForMessageIsntDisplayed"));
        }
    }

    @Step("Assertion of texts of images")
    public void assertionOfTextsOfImages(SoftAssert softly) {
        for (WebElement image : homePage.getTextsOfImages()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(homePage.getTextsOfImages()
                            .get(homePage.getTextsOfImages().indexOf(image)).getText(),
                    Arrays.asList(prop
                            .getProperty("ExpectedTextsOfImages").split("!"))
                            .get(homePage.getTextsOfImages().indexOf(image)));
        }
    }

    @Step("Assertion of iFrames existence")
    public void assertionOfIframesExistence(SoftAssert softly) {
        softly.assertTrue(homePage.getFrame().isEnabled(), prop
                .getProperty("AllertForFrameIsntDisplayed"));
    }

    @Step("Assertion of buttons existence")
    public void assertionOfButtonsExistence(SoftAssert softly, WebDriver driver) {
        driver.switchTo().frame(homePage.getFrame());
        softly.assertTrue(homePage.getFrameButton().isDisplayed());
        driver.switchTo().defaultContent();  //10.Switch to original window back
    }
}