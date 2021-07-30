package ru.training.at.hw3.dataproviders;

import org.testng.annotations.DataProvider;

import static ru.training.at.hw3.constants.Allerts.ALLERTFORFRAME;
import static ru.training.at.hw3.constants.Allerts.ALLERTFORMESSAGE;
import static ru.training.at.hw3.constants.Links.HOMEPAGE;
import static ru.training.at.hw3.constants.Texts.*;
import static ru.training.at.hw3.constants.User.*;

public class DataProviderForHw3 {


    @DataProvider
    public Object[][] ex1DataProvider() {
        return new Object[][]{
                {HOMEPAGE, TITLE, NAME, PASSWORD,
                        FULLNAME, TEXTSOFMENU, ALLERTFORMESSAGE,
                        TEXTSOFIMAGES, ALLERTFORFRAME, SIDEBARTEXT}
        };
    }

    @DataProvider
    public Object[][] ex2DataProvider() {
        return new Object[][]{
                {HOMEPAGE, TITLE, NAME, PASSWORD, FULLNAME, WATER, SELEN, YELLOW, LOGPARTS}
        };
    }
}
