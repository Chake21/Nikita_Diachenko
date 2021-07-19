package ru.training.at.hw5.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HeaderOfPage {

    public HeaderOfPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul.uui-navigation.nav.navbar-nav.m-l8 > li:nth-child(-n+4)")
    private List<WebElement> imagesList;

    public List<WebElement> getImagesList() {
        return imagesList;
    }
}
