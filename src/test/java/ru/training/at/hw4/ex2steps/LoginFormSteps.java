package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.training.at.hw4.driverutils.DriverManager;
import ru.training.at.hw4.pageobjects.LoginForm;
import static ru.training.at.hw4.Ex1Steps.prop;

public class LoginFormSteps extends DriverManager {

    private final LoginForm loginFormPage;

    public LoginFormSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        loginFormPage = new LoginForm(driver);

    }

    @Step("Login")
    public void login() {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        Assert.assertEquals(loginFormPage.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    @Step("Login Assertion")
    public void loginAssertion() {
        Assert.assertEquals(loginFormPage.getUserName().getText(),
                prop.getProperty("NameForLoginCheck"));
    }
}