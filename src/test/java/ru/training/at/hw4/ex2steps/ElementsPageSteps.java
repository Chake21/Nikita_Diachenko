package ru.training.at.hw4.ex2steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.training.at.hw4.pageobjects.ElementsPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static ru.training.at.hw4.Ex1Steps.PATH_TO_PROPERTIES;

public class ElementsPageSteps {
    private final ElementsPage elementsPage;
    private final FileInputStream fileInputStream;
    Properties prop = new Properties();

    public ElementsPageSteps(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
        elementsPage = new ElementsPage(driver);
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
    }


    @Step("Selecting Checkboxes")
    public void selectCheckboxes(WebDriver driver) {
        for (WebElement checkbox : elementsPage.getCheckboxes()) {
            if (checkbox.getText().equals(prop.getProperty("Water"))
                    || checkbox.getText().equals(prop.getProperty("Wind"))) {
                checkbox.click();
            }
        }
    }


    @Step("Selecting Radio")
    public void selectRadio(WebDriver driver) {
        for (WebElement radio : elementsPage.getRadios()) {
            if (radio.getText().equals(prop.getProperty("Selen"))) {
                radio.click();
            }
        }
    }


    @Step("Selecting in Dropdown")
    public void selectInDropdown(WebDriver driver) {
        elementsPage.getColorsDropdown().click();
        for (WebElement option : elementsPage.getOptions()) {
            if (prop.getProperty("Yellow").equals(option.getText())) {
                option.click();
            }
        }
    }


    @Step("Logs Asserts")
    public void logsAsserts(WebDriver driver) {
        for (WebElement section : elementsPage.getPanelSection()) {
            Assert.assertTrue(section.isDisplayed());
        }
        List<String> actualCollection = elementsPage.getPanelSection().stream()
                .map(WebElement::getText).collect(Collectors.toList());
        for (int i = 0; i < actualCollection.size(); i++) {
            Assert.assertTrue(actualCollection.get(0).contains(Arrays
                    .asList(prop.getProperty("ExpectedLogsParts").split(",")).get(i)));
        }
    }
}
