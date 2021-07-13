package ru.training.at.api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import ru.training.at.api.beans.TrelloApiAnswer;
import ru.training.at.api.constants.KeyAndToken;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.lessThan;


public class TrelloServiceObj {
    public static final URI TRELLO_URI = URI.create("https://api.trello.com/1");

    private final Method requestMethod;
    private final Map<String, String> parameters;
    private final Map<String, String> pathParameters;

    private TrelloServiceObj(Map<String, String> parameters,
                             Map<String, String> pathParameters, Method method) {
        this.requestMethod = method;
        this.parameters = parameters;
        this.pathParameters = pathParameters;
    }

    public static ApiRequestBuilder requestBuilder() {

        return new ApiRequestBuilder();
    }

    public static class ApiRequestBuilder {
        private Method requestMethod = Method.GET;
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> pathParameters = new HashMap<>();

        public ApiRequestBuilder setMethod(Method method) {
            requestMethod = method;
            return this;
        }

        public ApiRequestBuilder setKey(String... key) {
            parameters.put("key", Arrays.stream(key).collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setToken(String... trelloToken) {
            parameters.put("token", Arrays.stream(trelloToken).collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setName(String... name) {
            parameters.put("name", Arrays.stream(name).collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setBoardId(String... boardId) {
            parameters.put("idBoard", Arrays.stream(boardId).collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setId(String... id) {
            pathParameters.put("id", Arrays.stream(id).collect(Collectors.joining("")));
            return this;
        }


        public ApiRequestBuilder setFirstPathParam(String... firstPathParam) {
            pathParameters.put("firstPathParam", Arrays.stream(firstPathParam)
                    .collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setPathParam(String... pathParam) {
            pathParameters.put("pathParam", Arrays.stream(pathParam)
                    .collect(Collectors.joining("")));
            return this;
        }

        public ApiRequestBuilder setColor(String... color) {
            parameters.put("color", Arrays.stream(color)
                    .collect(Collectors.joining("")));
            return this;
        }


        public TrelloServiceObj buildRequest() {
            return new TrelloServiceObj(parameters, pathParameters, requestMethod);
        }
    }

    public Response sendRequest() {
        return RestAssured
                .given(requestSpecification()).log().all()
                .pathParams(pathParameters)
                .queryParams(parameters)
                .contentType("application/json")
                .request(requestMethod, TRELLO_URI.toString()
                        + "/{firstPathParam}" + "/{pathParam}" + "{id}")
                .prettyPeek()
                ;

    }

    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBaseUri(TRELLO_URI)
                .build();
    }

    public static TrelloApiAnswer getAnswers(Response response) {
        TrelloApiAnswer answers = new Gson()
                .fromJson(response.asString().trim(), new TypeToken<TrelloApiAnswer>() {
                }.getType());
        return answers;
    }

    public static TrelloApiAnswer getStringResult(Response response) {
        return getAnswers(response);
    }

    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification badResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }
}
