package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class JobsPage extends CommonMethods {

    @FindBy(id ="menu_admin_Job")
    public WebElement jobOption;

    @FindBy(id = "menu_admin_viewJobTitleList")
    public WebElement jobTitleList;

    @FindBy(xpath = "//*[@id = \"resultTable\"]/tbody/tr/td[2]")
    public List<WebElement> jobTitleListTable;

    public JobsPage(){
        PageFactory.initElements(driver,this);
    }
}
