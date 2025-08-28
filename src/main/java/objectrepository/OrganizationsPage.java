package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrganizationIcon;
	
	@FindBy(name = "search_text")
	private WebElement searchForTextField;
	
	@FindBy(id = "bas_searchfield")
	private WebElement inDropdown;
	
	@FindBy(name = "submit")
	private WebElement searchNowButton;
	
	@FindBy(linkText = "del")
	private WebElement deleteLink;
	
	public OrganizationsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		}

	public WebElement getCreateOrganizationIcon() {
		return createOrganizationIcon;
	}

	public WebElement getSearchForTextField() {
		return searchForTextField;
	}

	public WebElement getInDropdown() {
		return inDropdown;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public WebElement getDeleteLink() {
		return deleteLink;
	}
	
	
	

}
