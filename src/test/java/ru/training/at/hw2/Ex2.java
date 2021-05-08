package ru.training.at.hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ex2 {
    String check;
    private WebDriverWait webDriverWait;
    WebDriver webDriver;
    WebElement webElement;


    @Test
    public void ex2Point1() {  //1.Open Test Site by Url
        System.setProperty("webdriver.chrome.driver",
                "/Users/Nikita/Downloads/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://jdi-testing.github.io/jdi-light/index.html");
        Assert.assertEquals("https://jdi-testing.github.io/jdi-light/index.html",
                webDriver.getCurrentUrl());
    }

    @Test
    public void ex2Point2() {  //2.Assert Browser title
        webElement = webDriver.findElement(By.xpath("//header"));
        Assert.assertEquals("Home Page",
                webDriver.getTitle());
    }

    @Test
    public void ex2Point3() {  //3. Perform login
        webElement = webDriver.findElement(
                By.cssSelector("ul.uui-navigation.navbar-nav.navbar-right"));
        webElement.click();
        webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys("Roman");
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys("Jdi1234");
        webElement = webDriver.findElement(By.cssSelector(
                "button.uui-button.dark-blue.btn-login"));
        Assert.assertEquals("ENTER", webElement.getText());
    }

    @Test
    public void ex2Point4() {  //4. Assert Username is loggined
        webElement = webDriver.findElement(By.id("login-button"));
        webElement.click();
        webElement = webDriver.findElement(By.id("user-name"));
        Assert.assertEquals("ROMAN IOVLEV", webElement.getText());
    }

    @Test
    public void ex2Point5() {  //5.Open through the header menu Service -> Different Elements Page
        webElement = webDriver.findElement(By.cssSelector("a.dropdown-toggle"));
        webElement.click();
        webElement = webDriver.findElement(By.linkText("DIFFERENT ELEMENTS"));
        webElement.click();
    }

    @Test
    public void ex2Point6() {  //6.Select checkboxes
        List<WebElement> webElements = webDriver.findElements(By.className("label-checkbox"));
        for (WebElement e : webElements) {
            if (e.getText().equals("Water")) {
                e.click();
            } else if (e.getText().equals("Wind")) {
                e.click();
            }
        }
    }

    @Test
    public void ex2Point7() {  //7.Select radio
        List<WebElement> webElements = webDriver.findElements(By.className("label-radio"));
        for (WebElement e : webElements) {
            if (e.getText().equals("Selen")) {
                e.click();
            }
        }
    }

    @Test
    public void ex2Point8() {  //8. Select in dropdown
        webElement = webDriver.findElement(By.className("colors"));
        webElement.click();
        webElement = webDriver.findElement(By.cssSelector("option:nth-child(4)"));
        webElement.click();

    }

    @Test
    public void ex2Point9() {  //9.Logs Asserts
        List<WebElement> webElements = webDriver.findElements(By.className(
                "info-panel-section"));
        for (WebElement e : webElements) {
            Assert.assertTrue(e.isDisplayed());
            if (e.getText().contains("Water")) {
                Assert.assertTrue(e.getText().contains("Water"));
            } else if (e.getText().contains("Wind")) {
                Assert.assertTrue(e.getText().contains("Wind"));
            } else if (e.getText().contains("Selen")) {
                Assert.assertTrue(e.getText().contains("Selen"));
            } else if (e.getText().contains("Yellow")) {
                Assert.assertTrue(e.getText().contains("Yellow"));
            }
        }
    }

    @AfterClass
    public void clear() {
        webDriver.close();
    }
}
