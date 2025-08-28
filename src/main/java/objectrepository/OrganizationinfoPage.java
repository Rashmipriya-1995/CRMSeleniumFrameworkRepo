package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationinfoPage {
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTextField;
	
	@FindBy(name = "phone")
	private WebElement phonetextField;
	
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	
	@FindBy(name = "accounttype")
	private WebElement typedropdown;
	
	@FindBy(name = "button")
	private WebElement saveButton;
	
	public OrganizationinfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}

	public WebElement getPhonetextField() {
		return phonetextField;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypedropdown() {
		return typedropdown;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	

	
}
