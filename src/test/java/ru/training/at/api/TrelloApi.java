package ru.training.at.api;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.training.at.api.beans.TrelloApiAnswer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.training.at.api.constants.KeyAndToken.TRELLO_KEY;
import static ru.training.at.api.constants.KeyAndToken.TRELLO_TOKEN;
import static ru.training.at.api.service.TrelloServiceObj.*;

public class TrelloApi {

    //@Test(dataProvider = "createAndDeleteBoard", dataProviderClass = DataProviderForTrello.class)
    public void createAndDeleteBoard(String name, String boards) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId("")
                        .setFirstPathParam(boards)
                        .setPathParam("")
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setName(name)
                        .setMethod(Method.POST)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat("Board hasn't name ", result, hasProperty("name"));
        assertThat(result.getName(), containsString(name));

        TrelloApiAnswer result2 = getStringResult(
                requestBuilder()
                        .setId(result.getId())
                        .setKey(TRELLO_KEY)
                        .setFirstPathParam("boards")
                        .setPathParam("")
                        .setToken(TRELLO_TOKEN)
                        .setMethod(Method.DELETE)
                        .buildRequest()
                        .sendRequest()
        );

    }


    //@Test(dataProvider = "getBoard", dataProviderClass = DataProviderForTrello.class)
    public void getBoard(String tableId, String boards) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(tableId)
                        .setKey(TRELLO_KEY)
                        .setFirstPathParam(boards)
                        .setPathParam("")
                        .setToken(TRELLO_TOKEN)
                        .setMethod(Method.GET)
                        .buildRequest()
                        .sendRequest());
        assertThat(result, hasProperty("name"));
        assertThat("Board " + result.getName() + " hasn't property id", result, hasProperty("id"));
    }

    //@Test(dataProvider = "updateBoard", dataProviderClass = DataProviderForTrello.class)
    public void updateBoard(String tableId, String boards, String name) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(tableId)
                        .setKey(TRELLO_KEY)
                        .setFirstPathParam(boards)
                        .setPathParam("")
                        .setName(name)
                        .setToken(TRELLO_TOKEN)
                        .setMethod(Method.PUT)
                        .buildRequest()
                        .sendRequest());
        assertThat(result, hasProperty("name"));
        assertThat(result.getName(), containsString(name));
    }

    //@Test(dataProvider = "makeListOnBoard", dataProviderClass = DataProviderForTrello.class)
    public void makeListOnBoard(String tableId, String lists, String names) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId("")
                        .setFirstPathParam(lists)
                        .setPathParam("")
                        .setName(names)
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setBoardId(tableId)
                        .setMethod(Method.POST)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("name"));
        assertThat(result.getName(), containsString(names));
    }

    //@Test(dataProvider = "makeLabelOnBoard", dataProviderClass = DataProviderForTrello.class)
    public void makeLabelOnBoard(String tableId, String lable, String name, String color) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId("")
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setFirstPathParam(lable)
                        .setPathParam("")
                        .setColor(color)
                        .setName(name)
                        .setBoardId(tableId)
                        .setMethod(Method.POST)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("name"));
        assertThat(result.getName(), containsString(name));
        assertThat(result, hasProperty("color"));
        assertThat(result.getColor(), containsString(color));
    }

    //@Test(dataProvider = "updateLabelOnBoard", dataProviderClass = DataProviderForTrello.class)
    public void updateLabelOnBoard(String tableId, String label, String color, String name) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(tableId)
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setFirstPathParam(label)
                        .setPathParam("")
                        .setColor(color)
                        .setName(name)
                        .setMethod(Method.PUT)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("color"));
        assertThat(result.getColor(), containsString(color));
        assertThat(result, hasProperty("name"));
        assertThat(result.getName(), containsString(name));
    }

    //@Test(dataProvider = "getLabel", dataProviderClass = DataProviderForTrello.class)
    public void getLabel(String labelId, String labels) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(labelId)
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setFirstPathParam(labels)
                        .setPathParam("")
                        .setMethod(Method.GET)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("id"));
        assertThat(result.getId(), containsString(labelId));
    }

    //@Test(dataProvider = "updateListOnBoard", dataProviderClass = DataProviderForTrello.class)
    public void updateListOnBoard(String listId, String lists, String name) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(listId)
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setFirstPathParam(lists)
                        .setPathParam("")
                        .setName(name)
                        .setMethod(Method.PUT)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("name"));
        assertThat(result.getName(), containsString(name));
    }

    //@Test(dataProvider = "getList", dataProviderClass = DataProviderForTrello.class)
    public void getList(String listId, String lists) {
        TrelloApiAnswer result = getStringResult(
                requestBuilder()
                        .setId(listId)
                        .setKey(TRELLO_KEY)
                        .setToken(TRELLO_TOKEN)
                        .setFirstPathParam(lists)
                        .setPathParam("")
                        .setMethod(Method.GET)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(result, hasProperty("id"));
        assertThat(result.getId(), containsString(listId));
    }

    //@Test(dataProvider = "getList", dataProviderClass = DataProviderForTrello.class)
    public void getListGoodResponse(String listId, String lists) {
        requestBuilder()
                .setId(listId)
                .setKey(TRELLO_KEY)
                .setToken(TRELLO_TOKEN)
                .setFirstPathParam(lists)
                .setPathParam("")
                .setMethod(Method.GET)
                .buildRequest()
                .sendRequest()
                .then().assertThat()
                .spec(goodResponseSpecification());
    }

    @Test(dataProvider = "getBadList", dataProviderClass = DataProviderForTrello.class)
    public void getListBadResponse(String listId, String lists) {
        requestBuilder()
                .setId(listId)
                .setKey(TRELLO_KEY)
                .setToken(TRELLO_TOKEN)
                .setFirstPathParam(lists)
                .setPathParam("")
                .setMethod(Method.GET)
                .buildRequest()
                .sendRequest()
                .then().assertThat()
                .spec(badResponseSpecification());
    }
}

