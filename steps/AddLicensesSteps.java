package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import pages.QualificationPage;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;

public class AddLicensesSteps extends CommonMethods {
    @Given("user is able to login as Admin")
    public void user_is_able_to_login_as_admin() {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);

    }

    @When("user clicks on Licenses under Qualification")
    public void user_clicks_on_licenses_under_qualification() throws InterruptedException {
        QualificationPage qualify = new QualificationPage();
        click(qualify.adminOption);
        click(qualify.qualificationOption);
        click(qualify.licensesOption);

    }

    @Then("user should be able to add different licenses")
    public void user_should_be_able_to_add_different_licenses() throws InterruptedException {
        QualificationPage qualify = new QualificationPage();
        simpleTables(qualify.licenceTable, "Tester");
        Thread.sleep(5000);
    }
}
