package ru.training.at.hw2;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Ex1 {

    private WebDriverWait webDriverWait;
    WebDriver webDriver;
    WebElement webElement;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void ex1Point1() {  //1.Open Test Site by Url
        System.setProperty("webdriver.chrome.driver",
                "/Users/Nikita/Downloads/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://jdi-testing.github.io/jdi-light/index.html");
        softAssert.assertEquals("https://jdi-testing.github.io/jdi-light/index.html",
                webDriver.getCurrentUrl());
    }

    @Test
    public void ex1Point2() {  //2.Assert Browser title
        webElement = webDriver.findElement(By.xpath("//header"));
        softAssert.assertEquals("Home Page",
                webDriver.getTitle());
    }

    @Test
    public void ex1Point3() {  //3. Perform login
        webElement = webDriver.findElement(
                By.cssSelector("ul.uui-navigation.navbar-nav.navbar-right"));
        webElement.click();
        webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys("Roman");
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys("Jdi1234");
        webElement = webDriver.findElement(By.cssSelector(
                "button.uui-button.dark-blue.btn-login"));
        softAssert.assertEquals("LOGOUT", webElement.getText());
    }

    @Test
    public void ex1Point4() {  //4. Assert Username is loggined
        webElement = webDriver.findElement(By.id("login-button"));
        webElement.click();
        webElement = webDriver.findElement(By.id("user-name"));
        softAssert.assertEquals("ROMAN IOVLEV", webElement.getText());
    }

    @Test
    public void ex1Point5() {  //5.Assert that there are 4...
        List<WebElement> webElements = webDriver
                .findElements(By.cssSelector("ul.uui-navigation.nav.navbar-nav.m-l8"));
        softAssert.assertEquals("HOME\n"
                + "CONTACT FORM\n"
                + "SERVICE\n"
                + "METALS & COLORS", webElements.get(0).getText());

    }

    @Test
    public void ex1Point6() {  //6 Assert that there are 4 images on the
        // Index Page and they are displayed 4 images
        List<WebElement> webElements = webDriver.findElements(By.className("benefit-icon"));
        for (WebElement e : webElements) {
            softAssert.assertTrue(e.isDisplayed());
        }
    }

    @Test
    public void ex1Point7() {  //7 Assert that there are 4 images and they have proper text
        List<WebElement> webElements = webDriver.findElements(By.className("benefit-txt"));
        for (WebElement e : webElements) {
            softAssert.assertTrue(e.isDisplayed());
        }
        softAssert.assertEquals("To include good practices\n"
                + "and ideas from successful\n"
                + "EPAM project", webElements.get(0).getText());
        softAssert.assertEquals("To be flexible and\n"
                + "customizable", webElements.get(1).getText());
        softAssert.assertEquals("To be multiplatform", webElements.get(2).getText());
        softAssert.assertEquals("Already have good base\n"
                + "(about 20 internal and\n"
                + "some external projects),\n"
                + "wish to get more…", webElements.get(3).getText());


    }

    @Test
    public void ex1Point8() {  //8. Assert that there is the iframe with “Frame Button” exist
        webElement = webDriver.findElement(By.id("frame"));
        softAssert.assertTrue(webElement.isEnabled());
    }

    @Test
    public void ex1Point9() {  //9.Switch to the iframe and check
        // that there is “Frame Button” in the iframe
        webDriver.switchTo().frame(webElement);
        webElement = webDriver.findElement(By.id("frame-button"));
        softAssert.assertTrue(webElement.isDisplayed());
        webDriver.switchTo().defaultContent();  //10.Switch to original window back
    }

    @Test
    public void ex1Point91() {  //11.Assert that there are 5 items in the
        // Left Section are displayed
        // and they have proper text
        List<WebElement> webElements = webDriver.findElements(By.name("navigation-sidebar"));
        for (WebElement e : webElements) {
            e.isDisplayed();
        }
        softAssert.assertEquals("Home\n" + "Contact form\n"
                + "Service\n" + "Metals & Colors\n"
                + "Elements packs", webElements.get(0).getText());

    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}


