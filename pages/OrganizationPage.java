package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class OrganizationPage extends CommonMethods {

    @FindBy(id="menu_admin_Organization")
    public WebElement organizationOption;

    @FindBy(id="menu_admin_viewLocations")
    public WebElement officeLocations;

    @FindBy (xpath = "//*[@id = \"resultTable\"]/tbody/tr/td[2]")
    public List<WebElement> locationTable;

    public OrganizationPage(){
        PageFactory.initElements(driver,this);
    }
}
