package ru.training.at.api;

import org.testng.annotations.DataProvider;

import static ru.training.at.api.constants.Ids.*;
import static ru.training.at.api.constants.Colors.*;
import static ru.training.at.api.constants.FirstPathParams.*;
import static ru.training.at.api.constants.Names.*;
import static ru.training.at.api.constants.NamesForLabels.*;
import static ru.training.at.api.constants.NamesForLists.*;

public class DataProviderForTrello {

    @DataProvider
    public Object[][] createAndDeleteBoard() {
        return new Object[][]{
                {NAME_1, BOARDS},
                {NAME_2, BOARDS},
                {NAME_3, BOARDS}
        };
    }

    @DataProvider
    public Object[][] getBoard() {
        return new Object[][]{
                {TABLE_ID, BOARDS}
        };
    }

    @DataProvider
    public Object[][] updateBoard() {
        return new Object[][]{
                {TABLE_ID, BOARDS, NAME_3},
                {TABLE_ID, BOARDS, NAME_2},
                {TABLE_ID, BOARDS, NAME_1}
        };
    }

    @DataProvider
    public Object[][] makeListOnBoard() {
        return new Object[][]{
                {TABLE_ID, LISTS, LIST_NAME_1},
                {TABLE_ID, LISTS, LABEL_NAME_2},
                {TABLE_ID, LISTS, LABEL_NAME_3}
        };
    }

    @DataProvider
    public Object[][] makeLabelOnBoard() {
        return new Object[][]{
                {TABLE_ID, LABELS, LABEL_NAME_1, GREEN},
                {TABLE_ID, LABELS, LABEL_NAME_2, BLUE},
                {TABLE_ID, LABELS, LABEL_NAME_3, RED}
        };
    }

    @DataProvider
    public Object[][] updateLabelOnBoard() {
        return new Object[][]{
                {LABEL_ID, LABELS, RED, LABEL_NAME_3},
                {LABEL_ID, LABELS, GREEN, LABEL_NAME_1},
                {LABEL_ID, LABELS, BLUE, LABEL_NAME_2}
        };
    }

    @DataProvider
    public Object[][] getLabel() {
        return new Object[][]{
                {LABEL_ID, LABELS},
        };
    }

    @DataProvider
    public Object[][] updateListOnBoard() {
        return new Object[][]{
                {LIST_ID, LISTS, LIST_NAME_3},
                {LIST_ID, LISTS, LIST_NAME_1},
                {LIST_ID, LISTS, LIST_NAME_2}
        };
    }

    @DataProvider
    public Object[][] getList() {
        return new Object[][]{
                {LIST_ID, LISTS},
        };
    }

    @DataProvider
    public Object[][] getBadList() {
        return new Object[][]{
                {BAD_LIST_ID, LISTS},
        };
    }
}
