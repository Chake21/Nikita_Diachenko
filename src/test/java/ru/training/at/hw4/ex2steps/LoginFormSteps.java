package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.training.at.hw4.driverutils.DriverManager;
import ru.training.at.hw4.pageobjects.LoginForm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class LoginFormSteps extends DriverManager {

    DriverManager driverManager = new DriverManager();
    private final LoginForm loginFormPage;
    private final FileInputStream fileInputStream;
    Properties prop = new Properties();

    public LoginFormSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        loginFormPage = new LoginForm(driver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }


    @Step("Login")
    public void login(WebDriver driver) {
        loginFormPage.getLoginForm().click();
        loginFormPage.getNameField().sendKeys(prop.getProperty("Name"));
        loginFormPage.getPassField().sendKeys(prop.getProperty("Password"));
        loginFormPage.getLoginButton().click();
        Assert.assertEquals(loginFormPage.getLoggedButton().getText(),
                prop.getProperty("MessageForLoginCheck"));
    }


    @Step("Login Assertion")
    public void loginAssertion(WebDriver driver) {
        Assert.assertEquals(loginFormPage.getUserName().getText(),
                prop.getProperty("NameForLoginCheck"));
    }
}