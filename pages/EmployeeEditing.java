package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class EmployeeEditing extends CommonMethods {
    @FindBy(id = "btnSave")
    public WebElement editBtn;

    @FindBy(xpath = "//*[@id='frmEmpPersonalDetails']/fieldset/ol")
    public List<WebElement> editInfo;

    public EmployeeEditing (){
        PageFactory.initElements(driver,this);
    }

}
