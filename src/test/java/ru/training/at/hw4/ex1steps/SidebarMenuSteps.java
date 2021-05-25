package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.pageobjects.SidebarMenu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class SidebarMenuSteps {
    private final FileInputStream fileInputStream;
    private final SidebarMenu sidebarMenu;
    Properties prop = new Properties();


    public SidebarMenuSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        sidebarMenu = new SidebarMenu(driver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }

    @Step("Assertion of sidebars text")
    public void assertionOfSidebarsText(SoftAssert softly, WebDriver driver) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(),
                prop.getProperty("ExpectedNavigationSidebarText"));
    }

}
