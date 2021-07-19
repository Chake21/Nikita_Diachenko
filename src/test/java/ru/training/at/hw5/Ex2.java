package ru.training.at.hw5;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import ru.training.at.hw5.driverutils.DriverManager;
import ru.training.at.hw5.pageobjects.ElementsPage;
import ru.training.at.hw5.pageobjects.HomePage;
import ru.training.at.hw5.pageobjects.LoginForm;
import ru.training.at.hw5.pageobjects.UserTablePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Ex2 {
    DriverManager driverManager = new DriverManager();
    WebDriver webDriver = driverManager.setupDriver();
    private final FileInputStream fileInputStream;
    private final HomePage homePage;
    private final LoginForm loginForm;
    private final ElementsPage elementsPage;
    private final UserTablePage userTablePage;

    public Ex2() throws IOException {
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
        PageFactory.initElements(webDriver, this);
        homePage = new HomePage(webDriver);
        loginForm = new LoginForm(webDriver);
        elementsPage = new ElementsPage(webDriver);
        userTablePage = new UserTablePage(webDriver);
    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    Properties prop = new Properties();
    DataTable dataTable;

    @Given("I open JDI GitHub site")
    public void go() throws IOException {
        webDriver.get(prop.getProperty("Homepage"));
    }

    @And("I login as {string} Iovlev user")
    public void login(String arg0) {
        loginForm.getLoginForm().click();
        loginForm.getNameField().sendKeys(arg0);
        loginForm.getPassField().sendKeys(prop.getProperty("Password"));
        loginForm.getLoginButton().click();
        Assert.assertEquals(loginForm.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    @And("I click on button {string} in Header")
    public void clickOnServiceButton(String arg0) {
        homePage.getOpenMenuService().click();
        Assert.assertTrue(homePage.getOpenMenuService().isDisplayed());
    }

    @And("I click on {string} button in Service dropdown")
    public void openUserTablePage(String arg0) {
        homePage.getUserTablePage().click();
    }

    @Then("Page {string} should be opened")
    public void assertionOfUserTablePageIsOpened(String arg0) {
        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://jdi-testing.github.io/jdi-light/user-table.html");
    }

    @And("6 Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void numberTypesCheck() {
        for (WebElement e : userTablePage.getNumberType()) {
            Assert.assertTrue(e.isDisplayed());
        }
        List<String> actualCollection = userTablePage.getNumberType().stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    @And("6 Usernames should be displayed on Users Table on User Table Page")
    public void usernamesCheck() {
        for (WebElement e : userTablePage.getUsernames()) {
            Assert.assertTrue(e.isDisplayed());
        }
        List<String> actualCollection = userTablePage.getUsernames().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            Assert.assertTrue(prop.getProperty("Usernames").contains(actualCollection.get(i)));
        }

    }

    @And("6 Description texts under images should be displayed on Users Table on User Table Page")
    public void descriptionCheck() {
        for (WebElement e : userTablePage.getDescriptions()) {
            Assert.assertTrue(e.isDisplayed());
        }
        List<String> actualCollection = userTablePage.getDescriptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue(prop.getProperty("Descriptions").contains("some description"));
        for (int i = 0; i < actualCollection.size(); i++) {
            Assert.assertTrue(prop.getProperty("Descriptions").contains(actualCollection.get(i)));
        }
    }

    @And("6 checkboxes should be displayed on Users Table on User Table Page")
    public void checkboxesCheck() {
        for (WebElement e : userTablePage.getCheckboxes()) {
            Assert.assertTrue(e.isDisplayed());
        }
        List<String> actualCollection = userTablePage.getCheckboxes().stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }


    @And("User table should contain following values:")
    public void usertableCheck(DataTable dataTable) {
        List<List<String>> table = dataTable.asLists();
        List<String> namesCollection = userTablePage.getUsernames().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> descriptionCollection = userTablePage.getDescriptions().stream()
                .map(WebElement::getText).collect(Collectors.toList());

        for (int i = 1; i < dataTable.height(); i++) {
            String b = String.valueOf(i);
            List<String> stringForCheck = table.get(i);
            String actual = (b + ", " + namesCollection.get(i - 1)
                    + ", " + descriptionCollection.get(i - 1));
            String newActual = "[" + StringUtils.normalizeSpace(actual) + "]";
            Assert.assertEquals(newActual, stringForCheck.toString());
        }
    }


    @And("droplist should contain values in column Type for user Roman")
    public void userRomanCheck(DataTable dataTable) {
        List<String> table = dataTable.asList();
        List<String> actualCollection = userTablePage.getNumberType().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        String newActual = "[" + StringUtils.normalizeSpace(actualCollection.get(0)) + "]";
        String newestActual = newActual.replaceAll(" ", ", ");
        System.out.println(table.size());
        for (int i = 1; i < table.size(); i++) {
            Assert.assertTrue(newestActual.contains(table.get(i)));
        }
    }

    @Test
    public void run() throws IOException {
        go();
        login("Roman");
        clickOnServiceButton("Service");
        openUserTablePage("USER TABLE");
        assertionOfUserTablePageIsOpened("USER TABLE");
        numberTypesCheck();
        usernamesCheck();
        descriptionCheck();
        checkboxesCheck();
        usertableCheck(dataTable);
        userRomanCheck(dataTable);

    }

    @AfterClass
    public void close() {
        webDriver.close();
    }
}
