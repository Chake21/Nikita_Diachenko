package ru.training.at.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.training.at.hw3.dataproviders.DataProviderForHw3;
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
    WebDriver webDriver;
    private final LoginForm loginFormPage;
    private final ElementsPage elementsPage;
    private final HomePage homePage;
    private final FileInputStream fileInputStream;

    public Ex2() throws IOException {
        webDriver = driverManager.setupDriver();
        PageFactory.initElements(webDriver, this);
        loginFormPage = new LoginForm(webDriver);
        elementsPage = new ElementsPage(webDriver);
        homePage = new HomePage(webDriver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);

    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    Properties prop = new Properties();

    public void openTestSite(String homepage) {
        webDriver.get(homepage);
        Assert.assertEquals(webDriver.getCurrentUrl(),
                homepage);
    }

    public void browserTitleAssertion(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    public void login(String name, String password) {
        loginFormPage.login(name, password);
    }

    public void loginAssertion(String fullName) {
        Assert.assertEquals(loginFormPage.getUserName().getText(),
                fullName);
    }

    public void openDifferentElementsPage() {
        homePage.openDifferentElementsPage();
    }

    public void selectCheckboxes(String water) {
        for (WebElement checkbox : elementsPage.getCheckboxes()) {
            if (checkbox.getText().equals(water)
                    || checkbox.getText().equals(water)) {
                checkbox.click();
            }
        }
    }

    public void selectRadio(String selen) {
        for (WebElement radio : elementsPage.getRadios()) {
            if (radio.getText().equals(selen)) {
                radio.click();
            }
        }
    }

    public void selectInDropdown(String yellow) {
        elementsPage.getColorsDropdown().click();
        for (WebElement option : elementsPage.getOptions()) {
            if (yellow.equals(option.getText())) {
                option.click();
            }
        }

    }

    public void logsAsserts(String logparts) {

        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            Assert.assertTrue(actualCollection.get(0).contains(Arrays
                    .asList(logparts.split(",")).get(i)));
        }
    }

    @Test(dataProvider = "ex2DataProvider", dataProviderClass = DataProviderForHw3.class)
    public void runTests(String homepage, String title, String name, String password,
                         String fullName, String water, String selen,
                         String yellow, String logParts) {
        openTestSite(homepage);
        browserTitleAssertion(title);
        login(name, password);
        loginAssertion(fullName);
        openDifferentElementsPage();
        selectCheckboxes(water);
        selectRadio(selen);
        selectInDropdown(yellow);
        logsAsserts(logParts);
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}