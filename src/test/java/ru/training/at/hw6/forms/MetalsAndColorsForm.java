package ru.training.at.hw6.forms;

import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.complex.*;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;
import org.openqa.selenium.WebElement;
import ru.training.at.hw6.entities.MetalsAndColorsData;

import java.util.List;

public class MetalsAndColorsForm extends Form<MetalsAndColorsData> {
    @JDropdown(root = "#colors", list = "li")
    public static Dropdown colors;
    @JDropdown(root = "#metals", list = "li")
    public static Dropdown metals;
    @UI("#submit-button")
    public static Button submitButton;
    @JDropdown(root = "#vegetables", list = "li")
    public static Dropdown vegetables;
    @UI("#elements-checklist input[type=checkbox]")
    public static Checklist elements;
    @UI("input[name=custom_radio_odd]")
    public static RadioButtons oddButtons;
    @UI("input[name=custom_radio_even]")
    public static RadioButtons evenButtons;
    @UI(".info-panel-body-result .results li")
    public static List<WebElement> textResults;

}
