package ru.training.at.hw3;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

import ru.training.at.hw3.driverutils.DriverManager;

public class Ex1 extends DriverManager {

    DriverManager driverManager = new DriverManager();
    private final LoginForm loginFormPage;
    private final HeaderOfPage headerOfPage;
    private final SidebarMenu sidebarMenu;

    public Ex1() {
        driverManager.setupDriver();
        PageFactory.initElements(driver, this);
        loginFormPage = new LoginForm(driver);
        headerOfPage = new HeaderOfPage(driver);
        sidebarMenu = new SidebarMenu(driver);

    }

    public LoginForm getLoginFormPage() {
        return loginFormPage;
    }

    public HeaderOfPage getHeaderOfPage() {
        return headerOfPage;
    }

    public SidebarMenu getSidebarMenu() {
        return sidebarMenu;
    }


    @FindBy(className = "benefit-icon")
    private List<WebElement> iconsList;
    @FindBy(className = "benefit-txt")
    private List<WebElement> textsOfImages;
    @FindBy(id = "frame")
    private WebElement frame;
    @FindBy(id = "frame-button")
    private WebElement frameButton;


    public void openTestSite(SoftAssert softly) {
        driver.get(Locators.getHomepage());
        softly.assertEquals(driver.getCurrentUrl(),
                Locators.getHomepage());
    }

    public void browserTitleAssertion(SoftAssert softly) {
        softly.assertEquals(driver.getTitle(), Locators.getTitle());
    }

    public void login(SoftAssert softly) {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(Locators.getName());
        loginFormPage.getPassField().sendKeys(Locators.getPassword());
        loginFormPage.getLoginButton().click();
        softly.assertEquals(loginFormPage.getLoggedButton().getText(),
                Locators.getMessageForLoginCheck());
    }

    public void loginAssertion(SoftAssert softly) {
        softly.assertEquals(loginFormPage.getUserName().getText(),
                Locators.getNameForLoginCheck());
    }

    public void assertionOfCountOfTextsOfMenu(SoftAssert softly) {
        for (WebElement image : headerOfPage.getImagesList()) {
            softly.assertTrue(image.isDisplayed());
            softly.assertEquals(image.getText(), Locators.getExpectedTextsOfMenu()
                    .get(headerOfPage.getImagesList().indexOf(image)));
        }
    }

    public void assertionOfCountOfImages(SoftAssert softly) {
        for (WebElement icon : iconsList) {
            softly.assertTrue(icon.isDisplayed(), Locators.getAllertForMessageIsntDisplayed());
        }
    }

    public void assertionOfTextsOfImages(SoftAssert softly) {
        for (WebElement textOfImage : textsOfImages) {
            softly.assertTrue(textOfImage.isDisplayed());
            softly.assertEquals(textsOfImages.indexOf(textOfImage.getText()),
                    Locators.getExpectedTextsOfImages().indexOf(textOfImage));
        }
    }

    public void assertionOfIframesExistence(SoftAssert softly) {
        softly.assertTrue(frame.isEnabled(), Locators.getAllertForFrameIsntDisplayed());
    }

    public void assertionOfButtonsExistence(SoftAssert softly) {
        driver.switchTo().frame(frame);
        softly.assertTrue(frameButton.isDisplayed());
        driver.switchTo().defaultContent();  //10.Switch to original window back
    }

    public void assertionOfSidebarsText(SoftAssert softly) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(), Locators
                .getExpectedNavigationSidebarsText());
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
        driver.close();
    }
}
