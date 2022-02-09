package searchBreweries;

import Methods.Search;
import base.BaseTest;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SearchBreweries extends BaseTest {

    @Test
    public void searchWithValidParam(){
        String param = "Cat";

        Search.searchBreweries(param).then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/searchValidData.json"));
    }

    //TODO: search results also include objects with one different letter in the id/name
    @Test
    public void searchWithValidParamValidateName(){
        String param = "Bat";

        Response response = Search.searchBreweries(param).then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/searchValidData.json"))
                .extract().response();

        List<String> names = response.jsonPath().getList("name");
        for(String name: names){
            Assert.assertTrue(name.contains(param));
        }
    }

    @Test
    public void searchWithNullParam(){
        String param = null;

        Search.searchBreweries(param).then()
                .assertThat().statusCode(200)
                .body("", Matchers.hasSize(0));
    }

    @Test
    public void searchWithLongParam(){
        String param = "Dog Money Restaurant";

        Search.searchBreweries(param).then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/searchValidData.json"))
                .body("size", Matchers.equalTo(1))
                .body("name", Matchers.hasItem(param));
    }

    @Test
    public void searchValidateNameEqualsToId(){
        String param = "Lake Rat Brewing";

        Response response = Search.searchBreweries(param).then()
                .assertThat().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/searchValidData.json"))
                .extract().response();

        //validate that id starts with name
        List<String> names = response.jsonPath().getList("name");
        List<String> ids = response.jsonPath().getList("id");
        String name = names.get(0);
        String id = ids.get(0);

        name = names.get(0).replace(" ", "-").toLowerCase();

        Assert.assertTrue(id.startsWith(name));
    }

}
