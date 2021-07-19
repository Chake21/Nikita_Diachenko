package ru.training.at.hw5;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.training.at.hw5.driverutils.DriverManager;
import ru.training.at.hw5.pageobjects.ElementsPage;
import ru.training.at.hw5.pageobjects.HomePage;
import ru.training.at.hw5.pageobjects.LoginForm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Ex1 extends DriverManager {
    DriverManager driverManager = new DriverManager();
    WebDriver webDriver = driverManager.setupDriver();
    private final FileInputStream fileInputStream;
    WebElement webElement;
    private final HomePage homePage;
    private final LoginForm loginForm;
    private final ElementsPage elementsPage;

    public Ex1() throws IOException {
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
        PageFactory.initElements(webDriver, this);
        homePage = new HomePage(webDriver);
        loginForm = new LoginForm(webDriver);
        elementsPage = new ElementsPage(webDriver);
    }

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    Properties prop = new Properties();

    @Before
    public void start() throws IOException {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("I open Jdi site")
    public void go() throws IOException {
        webDriver.get(prop.getProperty("Homepage"));
    }

    @And("I login as {string} Iovlev")
    public void login(String arg0) {
        loginForm.getLoginForm().click();
        loginForm.getNameField().sendKeys(arg0);
        loginForm.getPassField().sendKeys(prop.getProperty("Password"));
        loginForm.getLoginButton().click();
        Assert.assertEquals(loginForm.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    @When("I click on {string} button in Header")
    public void clickOnServiceButton(String arg0) {
        homePage.getOpenMenuService().click();
        Assert.assertTrue(homePage.getOpenMenuService().isDisplayed());
    }

    @And("I click on {string} in Service dropdown")
    public void clickOnDifferentElements(String arg0) {
        homePage.getOpenDifferentElements().click();
    }

    @Then("{string} page should be opened")
    public void assertionOfOpenedElementsPage(String arg0) {
        Assert.assertEquals(webDriver.getCurrentUrl(),
                "https://jdi-testing.github.io/jdi-light/different-elements.html");
    }

    @When("I click on {string} and {string} checkboxes")
    public void selectCheckboxes(String arg0, String arg1) {
        for (WebElement checkbox : elementsPage.getCheckboxes()) {
            if (checkbox.getText().equals(prop.getProperty(arg0))
                    || checkbox.getText().equals(prop.getProperty(arg1))) {
                checkbox.click();
            }
        }
    }

    @And("I click on {string} radiobutton")
    public void selectRadio(String arg0) {
        for (WebElement radio : elementsPage.getRadios()) {
            if (radio.getText().equals(prop.getProperty(arg0))) {
                radio.click();
            }
        }
    }

    @And("I click on dropdown menu")
    public void clickOnColorMenu() {
        elementsPage.getColorsDropdown().click();
    }

    @And("I click on {string} string in dropdown menu")
    public void selectColor(String arg0) {
        for (WebElement option : elementsPage.getOptions()) {
            if (prop.getProperty("Yellow").equals(option.getText())) {
                option.click();
            }
        }
    }

    @Then("1 log row has {string} text in log section")
    public void firstLogAssertion(String arg0) {
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            if (actualCollection.get(0).contains(prop.getProperty("Water"))) {
                Assert.assertTrue(actualCollection.get(0)
                        .contains(prop.getProperty("Water")));
            }
        }
    }

    @And("1 log row has {string} text in log section 1")
    public void secondLogAssertion(String arg0) {
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            if (actualCollection.get(0).contains(prop.getProperty("Wind"))) {
                Assert.assertTrue(actualCollection.get(0)
                        .contains(prop.getProperty("Wind")));
            }
        }
    }


    @And("1 log row has {string} text in log section 2")
    public void thirdLogAssertion(String arg0) {
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            if (actualCollection.get(0).contains(prop.getProperty("Selen"))) {
                Assert.assertTrue(actualCollection.get(0)
                        .contains(prop.getProperty("Selen")));
            }
        }
    }

    @And("1 log row has {string} text in log section 3")
    public void fourthLogAssertion(String arg0) {
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            if (actualCollection.get(0).contains(prop.getProperty("Yellow"))) {
                Assert.assertTrue(actualCollection.get(0)
                        .contains(prop.getProperty("Yellow")));
            }
        }
    }

    @Test
    public void run() throws IOException {
        go();
        login("Roman");
        clickOnServiceButton("Service");
        clickOnDifferentElements("Different Elements");
        assertionOfOpenedElementsPage("Different Elements");
        selectCheckboxes("Water", "Wind");
        selectRadio("Selen");
        clickOnColorMenu();
        selectColor("Yellow");
        firstLogAssertion("FirstLog");
        secondLogAssertion("SecondLog");
        thirdLogAssertion("ThirdLog");
        fourthLogAssertion("FourthLog");
    }
}
