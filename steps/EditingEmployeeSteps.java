package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.an.E;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.EmployeeEditing;
import pages.addEmployeePage;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class EditingEmployeeSteps extends CommonMethods {
    @When("user is on personal details, user should be able to edit all information")
    public void user_is_on_personal_details_user_should_be_able_to_edit_all_information(DataTable data) throws InterruptedException {
        List<Map<String,String>> dataTable = data.asMaps();
        for(Map<String,String> datum:dataTable){
            String valueFirstName =  datum.get("firstName");
            String valueMiddleName =  datum.get("middleName");
            String valueLastName =  datum.get("lastName");

            addEmployeePage addEmployeePage = new addEmployeePage();
            sendText(addEmployeePage.firstName, valueFirstName);
            sendText(addEmployeePage.middleName, valueMiddleName);
            sendText(addEmployeePage.lastName,valueLastName);
            click(addEmployeePage.saveBtn);
        }
        EmployeeEditing edit = new EmployeeEditing();
        click(edit.editBtn);
        Thread.sleep(5000);
    }
}
