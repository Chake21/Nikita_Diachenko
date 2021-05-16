package ru.training.at.hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import ru.training.at.hw3.driverutils.DriverManager;


public class LoginForm extends DriverManager {
    DriverManager driverManager;

    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
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

    public WebElement getLoginForm() {
        return loginForm;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getPassField() {
        return passField;
    }

    public WebElement getUserName() {
        return userName;
    }

    public WebElement getLoggedButton() {
        return loggedButton;
    }
}
