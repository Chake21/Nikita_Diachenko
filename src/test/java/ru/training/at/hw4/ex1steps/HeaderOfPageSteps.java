package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.pageobjects.HeaderOfPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class HeaderOfPageSteps {
    private final HeaderOfPage headerOfPage;
    private final FileInputStream fileInputStream;
    Properties prop = new Properties();

    public HeaderOfPageSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        headerOfPage = new HeaderOfPage(driver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }


    @Step("Assertion of count of texts of menu")
    public void assertionOfCountOfTextsOfMenu(SoftAssert softly, WebDriver webDriver) {
        for (WebElement image : headerOfPage.getImagesList()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(image.getText(), Arrays
                    .asList(prop.getProperty("ExpectedTextsOfMenu").split(","))
                    .get(headerOfPage.getImagesList().indexOf(image)));
        }
    }

}
