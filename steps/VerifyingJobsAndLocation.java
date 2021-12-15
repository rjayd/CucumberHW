package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.JobsPage;
import pages.OrganizationPage;
import pages.QualificationPage;
import utils.CommonMethods;
import utils.DBUtils;
import utils.GlobalVariables;

import java.util.Map;

public class VerifyingJobsAndLocation extends CommonMethods {

    @When("user clicks on Job Title under Jobs")
    public void user_clicks_on_job_title_under_jobs() throws InterruptedException {
        QualificationPage qualify = new QualificationPage();
        JobsPage job = new JobsPage();

        click(qualify.adminOption);
        click(job.jobOption);
        sleep(2000);
        click(job.jobTitleList);


    }

    @When("when user query database and get data")
    public void when_user_query_database_and_get_data() {
        GlobalVariables.mapDataFromDb = DBUtils.mapFromDb("select distinct job_title from hs_hr_employees join ohrm_job_title on job_title_code = id where note is not null order by 1 asc;");
    }

    @Then("user should be able to verify the list against the SQL list")
    public void user_should_be_able_to_verify_the_list_against_the_sql_list() {
        JobsPage job = new JobsPage();
        for (int i = 0; i < job.jobTitleListTable.size(); i++) {
            String jobText = "{job_title=" + job.jobTitleListTable.get(i).getText() + "}";
            System.out.println(jobText);
            Assert.assertEquals(GlobalVariables.mapDataFromDb, jobText);
        }
    }

    @When("user clicks on location under Organization")
    public void user_clicks_on_location_under_organization() throws InterruptedException {
        QualificationPage qualify = new QualificationPage();
        OrganizationPage organize = new OrganizationPage();

        click(qualify.adminOption);
        click(organize.organizationOption);
        sleep(2000);
        click(organize.officeLocations);

    }

    @Then("user should verify result with SQL")
    public void user_should_verify_result_with_sql() {
        OrganizationPage organize = new OrganizationPage();
        GlobalVariables.mapDataFromDb = DBUtils.mapFromDb("select name from ohrm_location order by 1 asc;");
        for (int i = 0; i < organize.locationTable.size(); i++) {
            String locationText = organize.locationTable.get(i).getText();
            System.out.println(locationText);
        }
        System.out.println(GlobalVariables.mapDataFromDb);
    }

}
