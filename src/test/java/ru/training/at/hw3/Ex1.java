package ru.training.at.hw3;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw3.dataproviders.DataProviderForHw3;
import ru.training.at.hw3.pageobjects.*;
import ru.training.at.hw3.driverutils.DriverManager;

import java.io.IOException;
import java.util.Arrays;

public class Ex1 {

    DriverManager driverManager = new DriverManager();
    private final LoginForm loginFormPage;
    private final HeaderOfPage headerOfPage;
    private final SidebarMenu sidebarMenu;
    private final HomePage homePage;
    WebDriver webDriver = driverManager.setupDriver();

    public Ex1() throws IOException {
        PageFactory.initElements(webDriver, this);
        loginFormPage = new LoginForm(webDriver);
        headerOfPage = new HeaderOfPage(webDriver);
        sidebarMenu = new SidebarMenu(webDriver);
        homePage = new HomePage(webDriver);
    }


    public void openTestSite(SoftAssert softly, String homepage, Object expectedValue) {
        webDriver.get(homepage);
        softly.assertEquals(webDriver.getCurrentUrl(),
                expectedValue);
    }

    public void browserTitleAssertion(SoftAssert softly, Object expectedValue) {
        softly.assertEquals(webDriver.getTitle(), expectedValue);
    }

    public void login(SoftAssert softly, String name, String password, Object expectedValue) {
        loginFormPage.login(name, password);
        softly.assertEquals(loginFormPage.getUserName().getText(),
                expectedValue);
    }

    public void assertionOfCountOfTextsOfMenu(SoftAssert softly, String expectedValue) {
        for (WebElement image : headerOfPage.getImagesList()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(image.getText(), Arrays
                    .asList(expectedValue.split(","))
                    .get(headerOfPage.getImagesList().indexOf(image)));
        }
    }

    public void assertionOfCountOfImages(SoftAssert softly, String expectedValue) {
        for (WebElement icon : homePage.getIconsList()) {
            softly.assertTrue(icon.isDisplayed(), expectedValue);
        }
    }

    public void assertionOfTextsOfImages(SoftAssert softly, String expectedValue) {
        for (WebElement image : homePage.getTextsOfImages()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(homePage.getTextsOfImages()
                            .get(homePage.getTextsOfImages().indexOf(image)).getText(),
                    Arrays.asList(expectedValue.split("!"))
                            .get(homePage.getTextsOfImages().indexOf(image)));
        }
    }

    public void assertionOfIframesExistence(SoftAssert softly, String expectedValue) {
        softly.assertTrue(homePage.getFrame().isEnabled(), expectedValue);
    }

    public void assertionOfButtonsExistence(SoftAssert softly, WebElement expectedValue) {
        webDriver.switchTo().frame(homePage.getFrame());
        softly.assertTrue(expectedValue.isDisplayed());
        webDriver.switchTo().defaultContent();
    }

    public void assertionOfSidebarsText(SoftAssert softly, Object expectedValue) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(),
                expectedValue);
    }

    @Test(dataProvider = "ex1DataProvider", dataProviderClass = DataProviderForHw3.class)
    public void runTests(String homepage, String title, String name, String password,
                         String fullName, String expectedTextsOfMenu, String allertForMessage,
                         String expectedTextsOfImages, String allertForFrame,
                         String expectedNavigationSidebarText) {
        SoftAssert softly = new SoftAssert();
        openTestSite(softly, homepage, homepage);
        browserTitleAssertion(softly, title);
        login(softly, name, password, fullName);
        assertionOfCountOfTextsOfMenu(softly, expectedTextsOfMenu);
        assertionOfCountOfImages(softly, allertForMessage);
        assertionOfTextsOfImages(softly, expectedTextsOfImages);
        assertionOfIframesExistence(softly, allertForFrame);
        assertionOfButtonsExistence(softly, homePage.getFrameButton());
        assertionOfSidebarsText(softly, expectedNavigationSidebarText);
        softly.assertAll();
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
