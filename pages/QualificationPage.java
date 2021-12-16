package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class QualificationPage extends CommonMethods {
    @FindBy(xpath = "//*[@id=\"menu_admin_viewAdminModule\"]")
    public WebElement adminOption;


    @FindBy(xpath = "//*[@id ='menu_admin_Qualifications']")
    public WebElement qualificationOption;

    @FindBy(id = "menu_admin_viewLicenses")
    public WebElement licensesOption;

    @FindBy(xpath = "//*[@id='recordsListTable']/tbody/tr")
    public List<WebElement> licenceTable;

    @FindBy(id = "btnAdd")
    public WebElement addBtn;

    public QualificationPage() {
        PageFactory.initElements(driver,this);

    }
}