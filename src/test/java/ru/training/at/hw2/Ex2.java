package ru.training.at.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Ex2 {
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

    public void ex2Point1() {  //1.Open Test Site by Url
        webDriver.get("https://jdi-testing.github.io/jdi-light/index.html");
        Assert.assertEquals("https://jdi-testing.github.io/jdi-light/index.html",
                webDriver.getCurrentUrl());
    }

    public void ex2Point2() {  //2.Assert Browser title
        webElement = webDriver.findElement(By.xpath("//header"));
        Assert.assertEquals("Home Page",
                webDriver.getTitle());
    }

    public void ex2Point3() {  //3. Perform login
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

    public void ex2Point4() {  //4. Assert Username is loggined
        webElement = webDriver.findElement(By.id("user-name"));
        Assert.assertEquals(webElement.getText(), "ROMAN IOVLEV");
    }

    public void ex2Point5() {  //5.Open through the header menu Service -> Different Elements Page
        webElement = webDriver.findElement(By.cssSelector("a.dropdown-toggle"));
        webElement.click();
        webElement = webDriver.findElement(By.linkText("DIFFERENT ELEMENTS"));
        webElement.click();
    }

    public void ex2Point6() {  //6.Select checkboxes
        List<WebElement> webElements = webDriver.findElements(By.className("label-checkbox"));
        for (WebElement e : webElements) {
            if (e.getText().equals("Water") | e.getText().equals("Wind")) {
                e.click();
            }
        }
    }

    public void ex2Point7() {  //7.Select radio
        List<WebElement> webElements = webDriver.findElements(By.className("label-radio"));
        for (WebElement e : webElements) {
            if (e.getText().equals("Selen")) {
                e.click();
            }
        }
    }

    public void ex2Point8() {  //8. Select in dropdown
        webElement = webDriver.findElement(By.className("colors"));
        webElement.click();
        List<WebElement> options = webDriver.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if ("Yellow".equals(option.getText())) {
                option.click();
            }
        }

    }

    public void ex2Point9() {  //9.Logs Asserts
        List<WebElement> webElements = webDriver.findElements(By.className(
                "info-panel-section"));
        for (WebElement e : webElements) {
            Assert.assertTrue(e.isDisplayed());
        }

        List<String> actualCollection = webElements.stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> expectedCollection = Arrays
                .asList("Yellow", "Selen", "Water", "Wind");
        Assert.assertTrue(actualCollection.get(0).contains(expectedCollection.get(0)));
        Assert.assertTrue(actualCollection.get(0).contains(expectedCollection.get(1)));
        Assert.assertTrue(actualCollection.get(0).contains(expectedCollection.get(2)));
        Assert.assertTrue(actualCollection.get(0).contains(expectedCollection.get(3)));

    }

    @Test
    public void runTests() {
        ex2Point1();
        ex2Point2();
        ex2Point3();
        ex2Point4();
        ex2Point5();
        ex2Point6();
        ex2Point7();
        ex2Point8();
        ex2Point9();
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
