package ru.training.at.hw4.ex1steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import ru.training.at.hw4.pageobjects.SidebarMenu;


import static ru.training.at.hw4.Ex1Steps.prop;

public class SidebarMenuSteps {
    private final SidebarMenu sidebarMenu;

    public SidebarMenuSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
        sidebarMenu = new SidebarMenu(driver);
    }

    @Step("Assertion of sidebars text")
    public void assertionOfSidebarsText(SoftAssert softly) {
        for (WebElement navigationSidebarsElement : sidebarMenu.getNavigationSidebar()) {
            navigationSidebarsElement.isDisplayed();
        }
        softly.assertEquals(sidebarMenu.getNavigationSidebar().get(0).getText(),
                prop.getProperty("ExpectedNavigationSidebarText"));
    }
}
