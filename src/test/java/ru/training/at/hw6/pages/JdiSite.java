package ru.training.at.hw6.pages;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Link;
import ru.training.at.hw6.entities.MetalsAndColorsData;

import java.util.List;

@JSite("https://jdi-testing.github.io/jdi-light/")
public class JdiSite {

    @Url("/index.html")
    public static JdiHomePage jdiHomePage;

    @Url("/metals-colors.html")
    public static MetalsAndColorsPage metalsAndColorsPage;

    @UI("img#user-icon")
    public static Link userIcon;

    @UI(".logout button")
    public static Button logout;

    public static MetalsAndColorsData metalsAndColorsData;

    public static void openMetalsPage() {
        metalsAndColorsPage.open();
    }

    public static void checkMetalsIsOpened() {
        metalsAndColorsPage.checkOpened();
    }

    public static void open() {
        jdiHomePage.open();
    }

    public static void fillMetalsAndColors(Object allData) {
        metalsAndColorsPage.fill(allData);
    }

    public static void openHeaderMenu() {
        jdiHomePage.openHeaderMenu();
    }

    public static void exit() {
        userIcon.click();
        logout.click();
    }

    public static List<String> getActualResults() {
        return metalsAndColorsPage.getActualResults();
    }

    public static List<String> getExpectedResults(Object allData) {
        return metalsAndColorsPage.getExpectedResults(allData);
    }
}
