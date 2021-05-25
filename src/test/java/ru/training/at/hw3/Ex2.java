package ru.training.at.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.training.at.hw3.driverutils.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import ru.training.at.hw3.pageobjects.*;

public class Ex2 extends DriverManager {

    DriverManager driverManager = new DriverManager();
    WebDriver webDriver = driverManager.setupDriver();
    private final LoginForm loginFormPage;
    private final HeaderOfPage headerOfPage;
    private final SidebarMenu sidebarMenu;
    private final ElementsPage elementsPage;
    private final HomePage homePage;
    private final FileInputStream fileInputStream;

    public Ex2() throws IOException {
        webDriver = driverManager.setupDriver();
        PageFactory.initElements(webDriver, this);
        loginFormPage = new LoginForm(webDriver);
        headerOfPage = new HeaderOfPage(webDriver);
        sidebarMenu = new SidebarMenu(webDriver);
        elementsPage = new ElementsPage(webDriver);
        homePage = new HomePage(webDriver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);

    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    Properties prop = new Properties();

    public void openTestSite() {
        webDriver.get(prop.getProperty("Homepage"));
        Assert.assertEquals(webDriver.getCurrentUrl(),
                prop.getProperty("Homepage"));
    }

    public void browserTitleAssertion() {
        Assert.assertEquals(webDriver.getTitle(), prop.getProperty("Title"));
    }

    public void login() {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        Assert.assertEquals(loginFormPage.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    public void loginAssertion() {
        Assert.assertEquals(loginFormPage.getUserName().getText(),
                prop.getProperty("NameForLoginCheck"));
    }

    public void openDifferentElementsPage() {
        homePage.getOpenMenuService().click();
        homePage.getOpenDifferentElements().click();
    }

    public void selectCheckboxes() {
        for (WebElement checkbox : elementsPage.getCheckboxes()) {
            if (checkbox.getText().equals(prop.getProperty("Water"))
                    || checkbox.getText().equals(prop.getProperty("Water"))) {
                checkbox.click();
            }
        }
    }

    public void selectRadio() {
        for (WebElement radio : elementsPage.getRadios()) {
            if (radio.getText().equals(prop.getProperty("Selen"))) {
                radio.click();
            }
        }
    }

    public void selectInDropdown() {
        elementsPage.getColorsDropdown().click();
        for (WebElement option : elementsPage.getOptions()) {
            if (prop.getProperty("Yellow").equals(option.getText())) {
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
        for (int i = 0; i < actualCollection.size(); i++) {
            Assert.assertTrue(actualCollection.get(0).contains(Arrays
                    .asList(prop.getProperty("ExpectedLogsParts").split(",")).get(i)));
        }
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
        webDriver.close();
    }
}