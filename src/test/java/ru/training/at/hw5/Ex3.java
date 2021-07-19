package ru.training.at.hw5;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.training.at.hw5.driverutils.DriverManager;
import ru.training.at.hw5.pageobjects.ElementsPage;
import ru.training.at.hw5.pageobjects.HomePage;
import ru.training.at.hw5.pageobjects.LoginForm;
import ru.training.at.hw5.pageobjects.UserTablePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Ex3 extends DriverManager {
    DriverManager driverManager = new DriverManager();
    WebDriver webDriver = driverManager.setupDriver();
    private FileInputStream fileInputStream;
    private final HomePage homePage;
    private final LoginForm loginForm;
    private final ElementsPage elementsPage;
    private final UserTablePage userTablePage;

    public Ex3() throws IOException {
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

    @Before
    public void start() throws IOException {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }


    @Given("I open JDI test site")
    public void go() throws IOException {
        webDriver.get(prop.getProperty("Homepage"));
    }

    @And("I login as user {string} Iovlev")
    public void login(String arg0) {
        loginForm.getLoginForm().click();
        loginForm.getNameField().sendKeys(arg0);
        loginForm.getPassField().sendKeys(prop.getProperty("Password"));
        loginForm.getLoginButton().click();
        Assert.assertEquals(loginForm.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    @And("I click on button {string} in Header part of page")
    public void clickOnServiceButton(String arg0) {
        homePage.getOpenMenuService().click();
        Assert.assertTrue(homePage.getOpenMenuService().isDisplayed());
    }

    @And("I click on {string} button in Service dropdown menu")
    public void openUserTablePage(String arg0) {
        homePage.getUserTablePage().click();
    }

    @When("I select 'vip' checkbox for {string}")
    public void selectCheckbox(String arg0) {
        userTablePage.getVipBox().click();
    }

    @Then("1 log row has text {string} in log section")
    public void assertionLog(String arg) {
        System.out.println(arg);
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            System.out.println(actualCollection);
            System.out.println(actualCollection.get(0));
            System.out.println(prop.getProperty("Vip"));
            if (actualCollection.get(0).contains(prop.getProperty("Vip"))) {
                Assert.assertTrue(actualCollection.get(0)
                        .contains(prop.getProperty("Vip")));
            }
        }
    }

    @After
    public void close() {
        webDriver.close();
    }

    @Test
    public void run() throws IOException {
        go();
        login("Roman");
        clickOnServiceButton("Service");
        openUserTablePage("USER TABLE");
        selectCheckbox("Sergey Ivan");
        assertionLog("Vip");
    }
}