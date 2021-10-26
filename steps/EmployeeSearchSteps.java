package steps;

import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;

public class EmployeeSearchSteps extends CommonMethods {
    @Given("user is navigated to HRMS")
    public void user_is_navigated_to_hrms() {
        openBrowser();
    }

    @Given("user is logged in with valid admin credentials")
    public void user_is_logged_in_with_valid_admin_credentials(){
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);
    }
    @When("user navigates to employee list page")
    public void user_navigates_to_employee_list_page(){
        DashboardPage dash = new DashboardPage();
        click(dash.pimOption);
        click(dash.employeeListOption);
    }
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id (){
        EmployeeListPage emp = new EmployeeListPage();
        sendText(emp.idEmployee, "19353000");
    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {
        Thread.sleep(2000);
        EmployeeListPage emp = new EmployeeListPage();
        click(emp.searchButton);
    }
    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> table = driver.findElements(By.xpath("//table[@id=\"resultTable\"]/tbody/tr/td"));
        for (int i =0; i<table.size(); i++){
            if(table.get(i).getText().equalsIgnoreCase("Trafalgar")){
                System.out.println("Employee is verified");
                break;
            }
        }
    }
    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() throws InterruptedException {
        Thread.sleep(2000);
        EmployeeListPage emp = new EmployeeListPage();
        sendText(emp.employeeNameField,"Trafalgar");
    }
}
