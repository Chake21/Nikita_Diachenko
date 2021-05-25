package ru.training.at.hw4.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "benefit-icon")
    private List<WebElement> iconsList;
    @FindBy(className = "benefit-txt")
    private List<WebElement> textsOfImages;
    @FindBy(id = "frame")
    private WebElement frame;
    @FindBy(id = "frame-button")
    private WebElement frameButton;
    @FindBy(css = "a.dropdown-toggle")
    private WebElement openMenuService;
    @FindBy(linkText = "DIFFERENT ELEMENTS")
    private WebElement openDifferentElements;

    public List<WebElement> getIconsList() {
        return iconsList;
    }

    public List<WebElement> getTextsOfImages() {
        return textsOfImages;
    }

    public WebElement getFrame() {
        return frame;
    }

    public WebElement getFrameButton() {
        return frameButton;
    }

    public WebElement getOpenMenuService() {
        return openMenuService;
    }

    public WebElement getOpenDifferentElements() {
        return openDifferentElements;
    }


}
