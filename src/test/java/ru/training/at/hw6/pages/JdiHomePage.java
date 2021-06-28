package ru.training.at.hw6.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.*;
import ru.training.at.hw6.entities.User;
import ru.training.at.hw6.forms.LoginForm;

public class JdiHomePage extends WebPage {

    LoginForm loginForm;

    @UI("img#user-icon")
    public static Link userIcon;
    @UI("#user-name")
    public static Text userName;
    @UI("/html/body/header/div/nav/ul[1]/li[4]/a")
    public static Button metalsAndColors;

    public void login(User user) {
        userIcon.click();
        loginForm.login(user);
    }

    public void openHeaderMenu() {
        metalsAndColors.click();
    }
}
