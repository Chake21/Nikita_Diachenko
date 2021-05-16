package ru.training.at.hw3;

import java.util.Arrays;
import java.util.List;

public class Locators {

    private static final String TITLE = "Home Page";
    private static final String NAME = "Roman";
    private static final String PASSWORD = "Jdi1234";
    private static final String HOMEPAGE = "https://jdi-testing.github.io/jdi-light/index.html";
    private static final List<String> EXPECTEDTEXTSOFMENU = Arrays
            .asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
    private static final List<String> EXPECTEDTEXTXOFIMAGES = Arrays
            .asList("To include good practices\n" + "and ideas from successful\n"
                            + "EPAM project", "To be flexible and\n" + "customizable",
                    "To be multiplatform", "Already have good base\n"
                            + "(about 20 internal and\n"
                            + "some external projects),\n" + "wish to get more…");
    private static final String NAMEFORLOGINCHECK = "ROMAN IOVLEV";
    private static final String MESSAGEFORLOGINCHECK = "LOGOUT";
    private static final String ALLERTFORMESSAGEISNTDISPLAYED = "image is not displayed!";
    private static final String WATER = "Water";
    private static final String WIND = "Wind";
    private static final String ALLERTFORFRAMEISNTDISPLAYED = "The frame doesnt exist!";
    private static final String EXPECTEDNAVIGATIONSIDEBARSTEXT = "Home\n" + "Contact form\n"
            + "Service\n" + "Metals & Colors\n"
            + "Elements packs";
    private static final String SELEN = "Selen";
    private static final String YELLOW = "Yellow";
    private static final List<String> EXPECTEDLOGSPARTS = Arrays
            .asList("Yellow", "Selen", "Water", "Wind");

    public static List<String> getExpectedLogsParts() {
        return EXPECTEDLOGSPARTS;
    }

    public static String getYellow() {
        return YELLOW;
    }

    public static String getSelen() {
        return SELEN;
    }

    public static String getWater() {
        return WATER;
    }

    public static String getWind() {
        return WIND;
    }

    public static String getTitle() {
        return TITLE;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getHomepage() {
        return HOMEPAGE;
    }

    public static List<String> getExpectedTextsOfMenu() {
        return EXPECTEDTEXTSOFMENU;
    }

    public static List<String> getExpectedTextsOfImages() {
        return EXPECTEDTEXTXOFIMAGES;
    }

    public static String getName() {
        return NAME;
    }

    public static String getExpectedNavigationSidebarsText() {
        return EXPECTEDNAVIGATIONSIDEBARSTEXT;
    }

    public static String getAllertForFrameIsntDisplayed() {
        return ALLERTFORFRAMEISNTDISPLAYED;
    }

    public static String getAllertForMessageIsntDisplayed() {
        return ALLERTFORMESSAGEISNTDISPLAYED;
    }

    public static String getMessageForLoginCheck() {
        return MESSAGEFORLOGINCHECK;
    }

    public static String getNameForLoginCheck() {
        return NAMEFORLOGINCHECK;
    }


}
