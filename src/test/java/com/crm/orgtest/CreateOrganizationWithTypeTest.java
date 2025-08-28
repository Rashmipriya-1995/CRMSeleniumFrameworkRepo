package com.crm.orgtest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.baseclasstest.BaseClass;
import objectrepository.HomePage;
import objectrepository.OrganizationinfoPage;
import objectrepository.OrganizationsPage;

public class CreateOrganizationWithTypeTest extends BaseClass {
	@Test
	public void creatingOrganizationWithTypeTest() throws IOException, InterruptedException {

		// Read data from Excel file

		String orgName = excelLib.getDataFromExcelFile("Organization", 4, 2) + jlib.getRandomNumber();
		String indus = excelLib.getDataFromExcelFile("Organization", 4, 3);
		String type = excelLib.getDataFromExcelFile("Organization", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationIcon().click();
		OrganizationinfoPage opInfo = new OrganizationinfoPage(driver);
		opInfo.getOrganizationNameTextField().sendKeys(orgName);
		Select sel1 = new Select(opInfo.getIndustryDropdown());
		sel1.selectByVisibleText(indus);
		Select sel = new Select(opInfo.getTypedropdown());
		sel.selectByVisibleText(type);
		opInfo.getSaveButton().click();

		// verify expected results

		String headerinformation = driver.findElement(By.xpath("//span[@clas='dvHeaderText']")).getText();
		// header information is a dynamic data
		boolean status = headerinformation.contains(orgName);
		Assert.assertEquals(status, true);

		String industryinfo = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if (industryinfo.contains(indus)) {
			System.out.println(indus + " is created == PASS");
		} else {
			System.out.println(indus + " is not created == Fail");

		}

		String typeinfo = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if (typeinfo.equals("Partner")) {
			System.out.println(typeinfo + " is created == PASS");
		} else {
			System.out.println(typeinfo + " is not created == Fail");

		}

	}

}
