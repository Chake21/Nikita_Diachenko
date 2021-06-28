package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.pageobjects.HeaderOfPage;

import java.util.Arrays;

import static ru.training.at.hw4.Ex1Steps.prop;

public class HeaderOfPageSteps {
    private final HeaderOfPage headerOfPage;

    public HeaderOfPageSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        headerOfPage = new HeaderOfPage(driver);
    }

    @Step("Assertion of count of texts of menu")
    public void assertionOfCountOfTextsOfMenu(SoftAssert softly) {
        for (WebElement image : headerOfPage.getImagesList()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(image.getText(), Arrays
                    .asList(prop.getProperty("ExpectedTextsOfMenu").split(","))
                    .get(headerOfPage.getImagesList().indexOf(image)));
        }
    }
}
