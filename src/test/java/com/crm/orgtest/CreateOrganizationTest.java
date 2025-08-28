package com.crm.orgtest;

import java.io.IOException;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.baseclasstest.BaseClass;

import objectrepository.HomePage;
import objectrepository.OrganizationinfoPage;
import objectrepository.OrganizationsPage;
@Listeners(com.crm.generic.listenerUtility.ListenerUtility.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(priority = 1)
	public void creatingOrganizationTest() throws IOException, InterruptedException {

		// Read data from Excel file
		String orgName = excelLib.getDataFromExcelFile("Organization", 1, 2) + jlib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationIcon().click();
		OrganizationinfoPage opInfo = new OrganizationinfoPage(driver);
		opInfo.getOrganizationNameTextField().sendKeys(orgName);
		opInfo.getSaveButton().click();

		// verify expected result

		String headerinformation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//header information is a dynamic data
		boolean status=headerinformation.contains(orgName);
		Assert.assertEquals(status, true);

		
	}
	
	@Test(priority = 2)
	public void creatingOrganizationWithTypeTest() throws IOException, InterruptedException {
		
				
		//Read data from Excel file
		
		String orgName =excelLib.getDataFromExcelFile("Organization",4,2) +jlib.getRandomNumber();
		String indus = excelLib.getDataFromExcelFile("Organization",4,3);
		String type = excelLib.getDataFromExcelFile("Organization",4,4);
		
		

		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationIcon().click();
		OrganizationinfoPage opInfo = new OrganizationinfoPage(driver);
		opInfo.getOrganizationNameTextField().sendKeys(orgName);
		Select sel1= new Select(opInfo.getIndustryDropdown());
		sel1.selectByVisibleText(indus);
		Select sel=new Select(opInfo.getTypedropdown());
		sel.selectByVisibleText(type);
		opInfo.getSaveButton().click();
		
		//verify expected results
		
		String headerinformation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//header information is a dynamic data
		boolean status=headerinformation.contains(orgName);
		Assert.assertEquals(status, true);
		
		String industryinfo = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(industryinfo,indus);
		

		
		
		String typeinfo = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		soft.assertEquals(typeinfo,type);
		
		soft.assertAll();

		}
}


