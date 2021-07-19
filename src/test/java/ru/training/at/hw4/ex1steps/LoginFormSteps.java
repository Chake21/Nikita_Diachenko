package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import ru.training.at.hw4.pageobjects.LoginForm;

import static ru.training.at.hw4.Ex1Steps.prop;

public class LoginFormSteps {

    private final LoginForm loginFormPage;

    public LoginFormSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        loginFormPage = new LoginForm(driver);
    }

    @Step("Login")
    public void login(SoftAssert softly) {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        softly.assertEquals(loginFormPage.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }

    @Step("Login Assertion")
    public void loginAssertion(SoftAssert softly) {
        softly.assertEquals(loginFormPage.getUserName().getText(),
                prop.getProperty("NameForLoginCheck"));
    }
}