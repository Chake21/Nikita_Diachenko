package ru.training.at.hw4.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SidebarMenu {
    private LoginForm loginFormPage;
    private HeaderOfPage headerOfPage;
    private SidebarMenu sidebarMenu;


    public SidebarMenu(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "navigation-sidebar")
    private List<WebElement> navigationSidebar;

    public List<WebElement> getNavigationSidebar() {
        return navigationSidebar;
    }
}
