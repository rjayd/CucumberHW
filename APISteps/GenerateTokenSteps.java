package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenerateTokenSteps {

    public static String token;
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {

        RequestSpecification generateTokenRequest = given().header("Content-Type","application/json")
                .body("{\n" +
                        "  \"email\": \"fairytail@abc.com\",\n" +
                        "  \"password\": \"Igneel\"\n" +
                        "}");

        Response generatedTokenResponse = generateTokenRequest.when().post("/generateToken.php");
        generatedTokenResponse.prettyPrint();


        token="Bearer " + generatedTokenResponse.jsonPath().getString("token");
        System.out.println(token);

    }

}

