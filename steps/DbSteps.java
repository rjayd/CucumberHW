package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.DBUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class DbSteps extends DBUtils {
    @Then("query database and get data")
    public void query_database_and_get_data() {
       GlobalVariables.mapDataFromDb = DBUtils.mapFromDb("Select emp_firstname, emp_middle_name, emp_lastname "+
                "From hs_hr_employees " + "where employee_id ="+ GlobalVariables.empID);

    }

    public static void main(String[] args) {

        String actual = DBUtils.mapFromDb("select * from ohrm_user where user_name='" + GlobalVariables.userName + "';").get("user_name");
        String expected = GlobalVariables.userName;
        System.out.println("Actual " + actual);
        System.out.println("Expected " + expected);
        Assert.assertEquals(actual, expected);
    }
}
