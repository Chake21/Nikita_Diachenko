package ru.training.at.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw3.driverutils.DriverManager;

import java.util.List;
import java.util.stream.Collectors;

public class Ex2 extends DriverManager {

    DriverManager driverManager = new DriverManager();
    private final LoginForm loginFormPage;
    private final HeaderOfPage headerOfPage;
    private final SidebarMenu sidebarMenu;
    private final ElementsPage elementsPage;

    public Ex2() {
        driverManager.setupDriver();
        PageFactory.initElements(driver, this);
        loginFormPage = new LoginForm(driver);
        headerOfPage = new HeaderOfPage(driver);
        sidebarMenu = new SidebarMenu(driver);
        elementsPage = new ElementsPage(driver);

    }

    public ElementsPage getElementsPage() {
        return elementsPage;
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

    @FindBy(css = "ul.uui-navigation.navbar-nav.navbar-right")
    private WebElement loginForm;
    @FindBy(css = "#login-button")
    private WebElement loginButton;
    @FindBy(id = "name")
    private WebElement nameField;
    @FindBy(id = "password")
    private WebElement passField;
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(css = "body > header > div > nav > ul.uui-navigation.navbar-nav.navbar-right"
            + " > li > div > div > button")
    private WebElement loggedButton;
    @FindBy(css = "a.dropdown-toggle")
    private WebElement openMenuService;
    @FindBy(linkText = "DIFFERENT ELEMENTS")
    private WebElement openDifferentElements;


    public void openTestSite() {
        driver.get(Locators.getHomepage());
        Assert.assertEquals(driver.getCurrentUrl(),
                Locators.getHomepage());
    }

    public void browserTitleAssertion() {
        Assert.assertEquals(driver.getTitle(), Locators.getTitle());
    }

    public void login() {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(Locators.getName());
        loginFormPage.getPassField().sendKeys(Locators.getPassword());
        loginFormPage.getLoginButton().click();
        Assert.assertEquals(loginFormPage.getLoggedButton().getText(),
                Locators.getMessageForLoginCheck());
    }

    public void loginAssertion() {
        Assert.assertEquals(loginFormPage.getUserName().getText(),
                Locators.getNameForLoginCheck());
    }

    public void openDifferentElementsPage() {
        openMenuService.click();
        openDifferentElements.click();
    }

    public void selectCheckboxes() {
        for (WebElement checkbox : elementsPage.getCheckboxes()) {
            if (checkbox.getText().equals(Locators.getWater()) | checkbox
                    .getText().equals(Locators.getWind())) {
                checkbox.click();
            }
        }
    }

    public void selectRadio() {
        for (WebElement radio : elementsPage.getRadios()) {
            if (radio.getText().equals(Locators.getSelen())) {
                radio.click();
            }
        }
    }

    public void selectInDropdown() {
        elementsPage.getColorsDropdown().click();
        for (WebElement option : elementsPage.getOptions()) {
            if (Locators.getYellow().equals(option.getText())) {
                option.click();
            }
        }

    }

    public void logsAsserts() {

        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }

        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(actualCollection.get(0)
                .contains(Locators.getExpectedLogsParts().get(0)));
        Assert.assertTrue(actualCollection.get(0)
                .contains(Locators.getExpectedLogsParts().get(1)));
        Assert.assertTrue(actualCollection.get(0)
                .contains(Locators.getExpectedLogsParts().get(2)));
        Assert.assertTrue(actualCollection.get(0)
                .contains(Locators.getExpectedLogsParts().get(3)));

    }

    @Test
    public void runTests() {
        openTestSite();
        browserTitleAssertion();
        login();
        loginAssertion();
        openDifferentElementsPage();
        selectCheckboxes();
        selectRadio();
        selectInDropdown();
        logsAsserts();
    }

    @AfterClass
    public void clear() {
        driver.close();
    }
}