package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.training.at.hw4.pageobjects.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class HomePageSteps {
    private final HomePage homePage;
    private final FileInputStream fileInputStream;
    Properties prop = new Properties();

    public HomePageSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        homePage = new HomePage(driver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }


    @Step("Assertion of count of images")
    public void openDifferentElementsPage(WebDriver driver) {
        homePage.getOpenMenuService().click();
        homePage.getOpenDifferentElements().click();
    }


}