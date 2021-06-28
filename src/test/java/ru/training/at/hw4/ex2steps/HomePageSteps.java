package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.training.at.hw4.pageobjects.HomePage;



public class HomePageSteps {
    private final HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        homePage = new HomePage(driver);
    }

    @Step("Assertion of count of images")
    public void openDifferentElementsPage() {
        homePage.getOpenMenuService().click();
        homePage.getOpenDifferentElements().click();
    }
}