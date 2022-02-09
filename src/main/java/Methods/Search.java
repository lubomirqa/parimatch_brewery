package Methods;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Search {

    public static Response searchBreweries(String queryValue){
        return given().
            queryParam("query", queryValue).
        when().
            get("search");
    }
}
