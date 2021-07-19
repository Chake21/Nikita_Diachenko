package ru.training.at.hw5.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserTablePage {

    public UserTablePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/div/table/tbody/tr[2]/td[4]/div/input")
    private WebElement vipBox;
    @FindBy(css = "#user-table > tbody > tr:nth-child(n) > td:nth-child(n) > select")
    private List<WebElement> numberType;
    @FindBy(css = "#user-table > tbody > tr:nth-child(n) > td:nth-child(n) > div > label")
    private List<WebElement> checkboxes;
    @FindBy(css = "#user-table > tbody > tr:nth-child(n) > td:nth-child(n) > div > span")
    private List<WebElement> descriptions;
    @FindBy(css = "#user-table > tbody > tr:nth-child(n) > td:nth-child(n) > a")
    private List<WebElement> usernames;

    public List<WebElement> getCheckboxes() {
        return checkboxes;
    }

    public List<WebElement> getDescriptions() {
        return descriptions;
    }

    public List<WebElement> getUsernames() {
        return usernames;
    }


    public WebElement getVipBox() {
        return vipBox;
    }

    public List<WebElement> getNumberType() {
        return numberType;
    }
}

