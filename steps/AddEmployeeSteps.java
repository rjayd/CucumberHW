package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.EmployeeEditing;
import pages.LoginPage;
import pages.addEmployeePage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;
import utils.GlobalVariables;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashboardPage dash = new DashboardPage();
        click(dash.pimOption);

    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        DashboardPage dash = new DashboardPage();
        click(dash.addEmployeeBtn);
    }

    @When("user enters firstname, middlename, and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        addEmployeePage addEmployeePage = new addEmployeePage();
        sendText(addEmployeePage.firstName, "Fire");
        sendText(addEmployeePage.middleName, "Fist");
        sendText(addEmployeePage.lastName, "Ace");
    }

    @When("user selects checkbox")
    public void user_selects_checkbox() {
        addEmployeePage addEmployeePage = new addEmployeePage();
        click(addEmployeePage.createLoginCheckbox);
    }

    @When("user enters username password and confirm password")
    public void user_enters_username_password_and_confirmpassword() {
        addEmployeePage addEmployeePage = new addEmployeePage();
        sendText(addEmployeePage.createUsername, "firefist1");
        sendText(addEmployeePage.createPassword, "Hum@nhrm123");
        sendText(addEmployeePage.rePassword, "Hum@nhrm123");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        addEmployeePage addEmployeePage = new addEmployeePage();
        click(addEmployeePage.saveBtn);
    }

    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("user deletes employee id")
    public void user_deletes_employee_id() {
        addEmployeePage addEmployeePage = new addEmployeePage();
        addEmployeePage.employeeId.clear();
    }

    @And("user enters {string} {string} and {string}")
    public void user_Enters_And(String firstName, String middleName, String lastName) {
        GlobalVariables.firstName = firstName;
        GlobalVariables.middleName = middleName;
        GlobalVariables.lastName = lastName;

        addEmployeePage addEmployeePage = new addEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user enters {string} {string} and {string} for an employee")
    public void user_enters_and_for_an_employee(String firstName, String middleName, String lastName) {
        // Write code here that turns the phrase above into concrete actions
        addEmployeePage addEmployeePage = new addEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("I add multiple employees and verify them that user has been added successfully")
    public void i_Add_Multiple_Employees_And_Verify_Them_That_User_Has_Been_Added_Successfully(DataTable employees) throws InterruptedException {

        List<Map<String, String>> employeeNames = employees.asMaps();

        for (Map<String, String> employeeName : employeeNames) {
            String valueFirstName = employeeName.get("firstName");
            String valueMiddleName = employeeName.get("middleName");
            String valueLastName = employeeName.get("lastName");

            addEmployeePage addEmployeePage = new addEmployeePage();
            sendText(addEmployeePage.firstName, valueFirstName);
            sendText(addEmployeePage.middleName, valueMiddleName);
            sendText(addEmployeePage.lastName, valueLastName);
            click(addEmployeePage.saveBtn);

            DashboardPage dash = new DashboardPage();
            click(dash.addEmployeeBtn);
            Thread.sleep(2000);
        }
    }

    @When("user enters invalid credentials and clicks on login and verify the error")
    public void user_enters_invalid_credentials_and_clicks_on_login_and_verify_the_error(DataTable errorValidation) {
        List<Map<String, String>> errorData = errorValidation.asMaps();
        for (Map<String, String> validation : errorData) {
            String usernameValue = validation.get("username");
            String passwordValue = validation.get("password");
            String errorMessageValue = validation.get("errorMessage");

            LoginPage login = new LoginPage();
            sendText(login.usernameBox, usernameValue);
            sendText(login.passwordBox, passwordValue);
            click(login.loginBtn);

            String errorMessageActual = login.errorMessage.getText();

            Assert.assertEquals("Values do not match", errorMessageActual, errorMessageValue);
        }
    }

    @When("user enters invalid {string} and {string} and click on login and verify the {string}")
    public void user_enters_invalid_and_and_click_on_login_and_verify_the(String username, String password, String errorMessage) {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, username);
        sendText(login.passwordBox, password);
        click(login.loginBtn);
        String errorMessageActual = login.errorMessage.getText();

        String errorActual = login.errorMessage.getText();
        Assert.assertEquals("Values do not match", errorActual, errorMessage);
    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the added employee")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_added_employee(String sheetName) {
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetName);

        DashboardPage dash = new DashboardPage();
        addEmployeePage add = new addEmployeePage();

        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()) {
            Map<String, String> mapNewEmp = it.next();
            sendText(add.firstName, mapNewEmp.get("FirstName"));
            sendText(add.firstName, mapNewEmp.get("MiddleName"));
            sendText(add.firstName, mapNewEmp.get("LastName"));
            click(add.saveBtn);
            click(dash.addEmployeeBtn);

            //Assertion in HW
        }
    }

    @When("captures employee id")
    public void captures_employee_id() {
        addEmployeePage emp = new addEmployeePage();
        GlobalVariables.empID = emp.employeeId.getAttribute("value");
    }

    @Then("verify employee data is matched in ui and db")
    public void verify_employee_data_is_matched_in_ui_and_db() {
        Assert.assertEquals(GlobalVariables.mapDataFromDb.get("emp_firstname"), GlobalVariables.firstName);
        Assert.assertEquals(GlobalVariables.mapDataFromDb.get("emp_middle_name"), GlobalVariables.middleName);
        Assert.assertEquals(GlobalVariables.mapDataFromDb.get("emp_lastname"), GlobalVariables.lastName);

    }
}
