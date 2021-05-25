package ru.training.at.hw4;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.driverutils.DriverManager;
import ru.training.at.hw4.ex1steps.*;

import java.io.IOException;


public class Ex1Steps extends DriverManager {
    DriverManager driverManager = new DriverManager();
    private final HeaderOfPageSteps headerOfPageSteps;
    private final HomePageSteps homePageSteps;
    private final LoginFormSteps loginFormSteps;
    private final OpenSiteSteps openSiteSteps;
    private final SidebarMenuSteps sidebarMenuSteps;
    WebDriver webDriver = driverManager.setupDriver();


    public Ex1Steps() throws IOException {

        PageFactory.initElements(webDriver, this);
        headerOfPageSteps = new HeaderOfPageSteps(webDriver);
        homePageSteps = new HomePageSteps(webDriver);
        loginFormSteps = new LoginFormSteps(webDriver);
        openSiteSteps = new OpenSiteSteps(webDriver);
        sidebarMenuSteps = new SidebarMenuSteps(webDriver);
    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    SoftAssert softly = new SoftAssert();

    @BeforeMethod
    public void setUp(ITestContext context) {
        context.setAttribute("webDriver", webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Open Site and Title Assertion")
    @Story("Open Site")
    @Test(priority = 1)
    public void openTestSite() {
        openSiteSteps.openTestSite(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Open Site and Title Assertion")
    @Story("Title Assertion")
    @Test(priority = 2)
    public void titleAssertion() {
        openSiteSteps.titleAssertion(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Login Things")
    @Story("Login in")
    @Test(priority = 3)
    public void login() {
        loginFormSteps.login(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Login Things")
    @Story("Login Assertion")
    @Test(priority = 4)
    public void loginAssertion() {
        loginFormSteps.loginAssertion(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Story("Assertion of count of texts")
    @Test(priority = 5)
    public void headerAssertion() {
        headerOfPageSteps.assertionOfCountOfTextsOfMenu(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Home Page Steps")
    @Story("Assertion of count of images")
    @Test(priority = 6)
    public void countOfImagesAssertion() {
        homePageSteps.assertionOfCountOfImages(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Home Page Steps")
    @Story("Assertion of texts of images")
    @Test(priority = 7)
    public void textOfImagesAssertion() {
        homePageSteps.assertionOfTextsOfImages(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Home Page Steps")
    @Story("Assertion of frames existence")
    @Test(priority = 8)
    public void framesExistenceAssertion() {
        homePageSteps.assertionOfIframesExistence(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Feature("Home Page Steps")
    @Story("Assertion of buttons existence")
    @Test(priority = 9)
    public void buttonsExistenceAssertion() {
        homePageSteps.assertionOfButtonsExistence(softly, webDriver);
    }

    @Epic("Exercise 1")
    @Story("Assertion of sidebars text")
    @Test(priority = 10)
    public void textOfSidebarsAssertion() {
        sidebarMenuSteps.assertionOfSidebarsText(softly, webDriver);
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
