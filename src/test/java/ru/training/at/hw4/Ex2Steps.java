package ru.training.at.hw4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.training.at.hw4.driverutils.DriverManager;
import ru.training.at.hw4.ex2steps.*;

import java.io.IOException;

public class Ex2Steps extends DriverManager {
    DriverManager driverManager = new DriverManager();
    WebDriver webDriver = driverManager.setupDriver();
    private final HomePageSteps homePageSteps;
    private final LoginFormSteps loginFormSteps;
    private final OpenSiteSteps openSiteSteps;
    private final ElementsPageSteps elementsPageSteps;

    public Ex2Steps() throws IOException {
        webDriver = driverManager.setupDriver();
        PageFactory.initElements(webDriver, this);
        homePageSteps = new HomePageSteps(webDriver);
        loginFormSteps = new LoginFormSteps(webDriver);
        openSiteSteps = new OpenSiteSteps(webDriver);
        elementsPageSteps = new ElementsPageSteps(webDriver);
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("webDriver", webDriver);
    }

    @Epic("Exercise 2")
    @Test
    @Feature("Open Site and Title Assertion")
    @Story("Open Site")
    public void openSite() {
        openSiteSteps.openTestSite(webDriver);
    }

    @Epic("Exercise 2")
    @Test(priority = 1)
    @Feature("Open Site and Title Assertion")
    @Story("Title Assertion")
    public void titleAssertion() {
        openSiteSteps.browserTitleAssertion(webDriver);
    }

    @Epic("Exercise 2")
    @Test(priority = 2)
    @Feature("Login Things")
    @Story("Login in")
    public void login() {
        loginFormSteps.login(webDriver);
    }

    @Epic("Exercise 2")
    @Feature("Login Things")
    @Story("Login Assertion")
    @Test(priority = 3)
    public void loginAssertion() {
        loginFormSteps.loginAssertion(webDriver);
    }

    @Epic("Exercise 2")
    @Story("Open page of elements")
    @Test(priority = 4)
    public void openDiffElementsPage() {
        homePageSteps.openDifferentElementsPage(webDriver);
    }

    @Epic("Exercise 2")
    @Feature("Different Elements")
    @Story("Select Checkboxes")
    @Test(priority = 5)
    public void selectCheckboxes() {
        elementsPageSteps.selectCheckboxes(webDriver);
    }

    @Epic("Exercise 2")
    @Feature("Different Elements")
    @Story("Select Radio")
    @Test(priority = 6)
    public void selectRadio() {
        elementsPageSteps.selectRadio(webDriver);
    }

    @Epic("Exercise 2")
    @Feature("Different Elements")
    @Story("Select Dropdown Menu")
    @Test(priority = 7)
    public void selectInDropdownMenu() {
        elementsPageSteps.selectInDropdown(webDriver);
    }

    @Epic("Exercise 2")
    @Feature("Different Elements")
    @Story("Logs Asserts")
    @Test(priority = 8)
    public void logsAsserts() {
        elementsPageSteps.logsAsserts(webDriver);
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}