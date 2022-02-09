package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup(){
        //url to hit
        RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/";

        //logging for failed tests
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
