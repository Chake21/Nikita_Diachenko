package ru.training.at.hw3;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw3.pageobjects.*;
import ru.training.at.hw3.driverutils.DriverManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.io.FileInputStream;

public class Ex1 {

    DriverManager driverManager = new DriverManager();
    private final LoginForm loginFormPage;
    private final HeaderOfPage headerOfPage;
    private final SidebarMenu sidebarMenu;
    private final HomePage homePage;
    private final FileInputStream fileInputStream;
    WebDriver webDriver = driverManager.setupDriver();

    public Ex1() throws IOException {

        PageFactory.initElements(webDriver, this);
        loginFormPage = new LoginForm(webDriver);
        headerOfPage = new HeaderOfPage(webDriver);
        sidebarMenu = new SidebarMenu(webDriver);
        homePage = new HomePage(webDriver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    Properties prop = new Properties();


    public void openTestSite(SoftAssert softly, Object expectedValue) {
        webDriver.get(prop.getProperty("Homepage"));
        softly.assertEquals(webDriver.getCurrentUrl(),
                expectedValue);
    }

    public void browserTitleAssertion(SoftAssert softly, Object expectedValue) {
        softly.assertEquals(webDriver.getTitle(), expectedValue);
    }

    public void login(SoftAssert softly, Object expectedValue) {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        softly.assertEquals(loginFormPage.getLoggedButton().getText(),
                expectedValue);
    }

    public void loginAssertion(SoftAssert softly, Object expectedValue) {
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
        webDriver.switchTo().defaultContent();  //10.Switch to original window back
    }

    public void assertionOfSidebarsText(SoftAssert softly, Object expectedValue) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(),
                expectedValue);
    }

    @Test
    public void runTests() {
        SoftAssert softly = new SoftAssert();
        openTestSite(softly, prop.getProperty("Homepage"));
        browserTitleAssertion(softly, prop.getProperty("Title"));
        login(softly, prop.getProperty("MessageForLoginCheck"));
        loginAssertion(softly, prop.getProperty("NameForLoginCheck"));
        assertionOfCountOfTextsOfMenu(softly, prop.getProperty("ExpectedTextsOfMenu"));
        assertionOfCountOfImages(softly, prop
                .getProperty("AllertForMessageIsntDisplayed"));
        assertionOfTextsOfImages(softly, prop
                .getProperty("ExpectedTextsOfImages"));
        assertionOfIframesExistence(softly, prop
                .getProperty("AllertForFrameIsntDisplayed"));
        assertionOfButtonsExistence(softly, homePage.getFrameButton());
        assertionOfSidebarsText(softly, prop.getProperty("ExpectedNavigationSidebarText"));
        softly.assertAll();
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
