package API;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamplesOfAPI {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzkzMjUxNzMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzOTM2ODM3MywidXNlcklkIjoiMzI0OCJ9.-7IzPt8KMPPWfKleGCFKYyESgloneE01Fb6mqnw3qxg";
    static String employee_id;
    @Test
    public void getDetailsOfOneEmployee() {
        //rest assured consider baseurl as baseuri

        //Given
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", "25653A");
        //when = hitting the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        System.out.println(response.asString());

        //then - result/assertion
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void createEmployeeCall(){
     //given
        RequestSpecification preparedReq = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"emp_firstname\": \"Luffy\",\n" +
                        "  \"emp_lastname\": \"Monkey\",\n" +
                        "  \"emp_middle_name\": \"D\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-05-18\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"API Tester\"\n" +
                        "}");
        Response response = preparedReq.when().post("/createEmployee.php");

        //then
        response.prettyPrint();
        //this pretty print does the same job as syso. // System.out.println(response.asString);


        //jsonPath() we use this to get specific information from the json object
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);


        response.then().assertThat().body("Employee.emp_firstname", equalTo("Luffy"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }
    @Test
    public void getCreatedEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", employee_id);


       Response response = preparedRequest.when().get("/getOneEmployee.php");

       String empID = response.jsonPath().getString("employee.employee_id");

       boolean comparingEmpID = empID.contentEquals(employee_id);

       Assert.assertTrue(comparingEmpID);

       String firstName =  response.jsonPath().getString("employee.emp_firstname");
       Assert.assertTrue(firstName.contentEquals("Luffy"));
    }
    @Test
    public void updatedCreatedEmployee(){

        RequestSpecification preparedReq = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"employee_id\": \""+ employee_id +"\",\n" +
                        "  \"emp_firstname\": \"John\",\n" +
                        "  \"emp_lastname\": \"Lennon\",\n" +
                        "  \"emp_middle_name\": \"D\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1980-10-03\",\n" +
                        "  \"emp_status\": \"Employee\",\n" +
                        "  \"emp_job_title\": \"API Tester\"\n" +
                        "}");
        Response response = preparedReq.when().put("/updateEmployee.php");
        response.prettyPrint();
    }
    @Test
    public void getAllEmployees(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json");

        Response response = request.when().post("/getAllEmployees.php");

        String allEmployees = response.prettyPrint();

        JsonPath js = new JsonPath(allEmployees);

        int count = js.getInt("Employees.size();");
        System.out.println(count);

        for (int i = 0; i < count; i++) {

            String employeeIds = js.getString("Employees["+i+"].employee_id");
            System.out.println(employeeIds);

        }
    }
}
