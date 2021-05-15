package ru.training.at.hw2;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ex1 {

    private WebDriver webDriver;

    @BeforeClass
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    WebElement webElement;

    @BeforeMethod
    public void driverProperties() {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void ex1Point1(SoftAssert softly) {  //1.Open Test Site by Url
        webDriver.get("https://jdi-testing.github.io/jdi-light/index.html");
        softly.assertEquals("https://jdi-testing.github.io/jdi-light/index.html",
                webDriver.getCurrentUrl());
    }

    public void ex1Point2(SoftAssert softly) {  //2.Assert Browser title
        webElement = webDriver.findElement(By.cssSelector("header"));
        softly.assertEquals("Home Page",
                webDriver.getTitle());
    }

    public void ex1Point3(SoftAssert softly) {  //3. Perform login
        webElement = webDriver.findElement(
                By.cssSelector("ul.uui-navigation.navbar-nav.navbar-right"));
        webElement.click();
        webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys("Roman");
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys("Jdi1234");
        WebElement login = webDriver.findElement(By.cssSelector("#login-button"));
        login.click();
    }

    public void ex1Point4(SoftAssert softly) {  //4. Assert Username is loggined
        webElement = webDriver.findElement(By.id("user-name"));
        softly.assertEquals(webElement.getText(), "ROMAN IOVLEV");
    }

    public void ex1Point5(SoftAssert softly) {  //5.Assert that there are 4...
        List<WebElement> webElements = webDriver.findElements(By
                .cssSelector("ul.uui-navigation.nav.navbar-nav.m-l8 > li:nth-child(-n+4)"));
        List<String> actualCollection = webElements.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedCollection = Arrays
                .asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        softly.assertEquals(actualCollection, expectedCollection);


    }

    public void ex1Point6(SoftAssert softly) {  //6 Assert that there are 4 images on the
        // Index Page and they are displayed 4 images
        List<WebElement> webElements = webDriver.findElements(By.className("benefit-icon"));
        for (WebElement e : webElements) {
            softly.assertTrue(e.isDisplayed(), "image is not displayed!");
        }
    }

    public void ex1Point7(SoftAssert softly) {
        //7 Assert that there are 4 images and they have proper text
        List<WebElement> webElements = webDriver.findElements(By.className("benefit-txt"));
        for (WebElement e : webElements) {
            softly.assertTrue(e.isDisplayed());
        }
        List<String> actualCollection = webElements.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedCollection = Arrays
                .asList("To include good practices\n" + "and ideas from successful\n"
                                + "EPAM project", "To be flexible and\n" + "customizable",
                        "To be multiplatform", "Already have good base\n"
                                + "(about 20 internal and\n"
                                + "some external projects),\n" + "wish to get more…");
        softly.assertEquals(actualCollection, expectedCollection);


    }

    public void ex1Point8(SoftAssert softly) {
        //8. Assert that there is the iframe with “Frame Button” exist
        webElement = webDriver.findElement(By.id("frame"));
        //If the frame doesnt exist, test will crash
        softly.assertTrue(webElement.isEnabled(), "The frame doesnt exist!");
    }

    public void ex1Point9(SoftAssert softly) {  //9.Switch to the iframe and check
        // that there is “Frame Button” in the iframe
        webDriver.switchTo().frame(webElement);
        webElement = webDriver.findElement(By.id("frame-button"));
        softly.assertTrue(webElement.isDisplayed());
        webDriver.switchTo().defaultContent();  //10.Switch to original window back
    }

    public void ex1Point11(SoftAssert softly) {  //11.Assert that there are 5 items in the
        // Left Section are displayed
        // and they have proper text
        List<WebElement> webElements = webDriver.findElements(By.name("navigation-sidebar"));
        for (WebElement e : webElements) {
            e.isDisplayed();
        }
        softly.assertEquals(webElements.get(0).getText(), "Home\n" + "Contact form\n"
                + "Service\n" + "Metals & Colors\n"
                + "Elements packs");
    }

    @Test
    public void runTests() {
        SoftAssert softly = new SoftAssert();
        ex1Point1(softly);
        ex1Point2(softly);
        ex1Point3(softly);
        ex1Point4(softly);
        ex1Point5(softly);
        ex1Point6(softly);
        ex1Point7(softly);
        ex1Point8(softly);
        ex1Point9(softly);
        ex1Point11(softly);
        softly.assertAll();
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}


