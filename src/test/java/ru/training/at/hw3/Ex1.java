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

public class Ex1 extends DriverManager {

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


    public void openTestSite(SoftAssert softly) {
        webDriver.get(prop.getProperty("Homepage"));
        softly.assertEquals(webDriver.getCurrentUrl(),
                prop.getProperty("Homepage"));
    }

    public void browserTitleAssertion(SoftAssert softly) {
        softly.assertEquals(webDriver.getTitle(), prop.getProperty("Title"));
    }

    public void login(SoftAssert softly) {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        softly.assertEquals(loginFormPage.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    public void loginAssertion(SoftAssert softly) {
        softly.assertEquals(loginFormPage.getUserName().getText(),
                prop.getProperty("NameForLoginCheck"));
    }

    public void assertionOfCountOfTextsOfMenu(SoftAssert softly) {
        for (WebElement image : headerOfPage.getImagesList()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(image.getText(), Arrays
                    .asList(prop.getProperty("ExpectedTextsOfMenu").split(","))
                    .get(headerOfPage.getImagesList().indexOf(image)));
        }
    }

    public void assertionOfCountOfImages(SoftAssert softly) {
        for (WebElement icon : homePage.getIconsList()) {
            softly.assertTrue(icon.isDisplayed(), prop
                    .getProperty("AllertForMessageIsntDisplayed"));
        }
    }

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

    public void assertionOfIframesExistence(SoftAssert softly) {
        softly.assertTrue(homePage.getFrame().isEnabled(), prop
                .getProperty("AllertForFrameIsntDisplayed"));
    }

    public void assertionOfButtonsExistence(SoftAssert softly) {
        webDriver.switchTo().frame(homePage.getFrame());
        softly.assertTrue(homePage.getFrameButton().isDisplayed());
        webDriver.switchTo().defaultContent();  //10.Switch to original window back
    }

    public void assertionOfSidebarsText(SoftAssert softly) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(),
                prop.getProperty("ExpectedNavigationSidebarText"));
    }

    @Test
    public void runTests() {
        SoftAssert softly = new SoftAssert();
        openTestSite(softly);
        browserTitleAssertion(softly);
        login(softly);
        loginAssertion(softly);
        assertionOfCountOfTextsOfMenu(softly);
        assertionOfCountOfImages(softly);
        assertionOfTextsOfImages(softly);
        assertionOfIframesExistence(softly);
        assertionOfButtonsExistence(softly);
        assertionOfSidebarsText(softly);
        softly.assertAll();
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
