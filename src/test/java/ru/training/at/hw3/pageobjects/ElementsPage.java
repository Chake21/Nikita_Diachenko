package ru.training.at.hw3.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ElementsPage {

    public ElementsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<WebElement> getRadios() {
        return radios;
    }

    public WebElement getColorsDropdown() {
        return colorsDropdown;
    }

    public List<WebElement> getOptions() {
        return options;
    }

    public List<WebElement> getPanelSection() {
        return panelSection;
    }

    @FindBy(className = "label-checkbox")
    private List<WebElement> checkboxes;
    @FindBy(className = "label-radio")
    private List<WebElement> radios;
    @FindBy(className = "colors")
    private WebElement colorsDropdown;
    @FindBy(tagName = "option")
    private List<WebElement> options;
    @FindBy(className = "info-panel-section")
    private List<WebElement> panelSection;

}
