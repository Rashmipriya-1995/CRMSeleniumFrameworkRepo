package com.crm.opportunitytest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.generic.excelUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;

public class CreateOpportuinityWithOrganizationTest {
	
	@Test
	public void creatingOpportunityWithOrganization() throws IOException, InterruptedException {
		
		//create Object
		FileUtility fileLib=new FileUtility();
		ExcelUtility excelLib=new ExcelUtility();
		
		//Read data from property file
		
		
		String browsername = fileLib.getDataFromPropertiesFile("browser");
		String url= fileLib.getDataFromPropertiesFile("url");
		String username = fileLib.getDataFromPropertiesFile("username");
		String password = fileLib.getDataFromPropertiesFile("password");

		
		//generate Random Number
		Random random=new Random();
		int randomInt = random.nextInt(2000);
		
		//Read data from Excel file
		FileInputStream fis1=new FileInputStream("./TestData/TestScriptData1.xlsx");		
		Workbook wb = WorkbookFactory.create(fis1);
		String opporName = excelLib.getDataFromExcelFile("Sheet1", 4, 2)+randomInt;
		String orgName = excelLib.getDataFromExcelFile("Sheet1", 4, 3)+randomInt;

		
		WebDriver driver=null;
		if(browsername.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		
		else if(browsername.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else 
		{
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		String parentWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();

		
		
		Thread.sleep(4000);
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(opporName);
		
		driver.findElement(By.xpath("//input[@id='related_to_display']/following-sibling::img")).click();
		Set<String> allWindowIds = driver.getWindowHandles();
		
		for(String id:allWindowIds)
		{
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains("Accounts&action"))
				break;
		}
		
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		
		//to click organization name(dynamic)
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		driver.switchTo().window(parentWindow);
		driver.findElement(By.name("button")).click();
		
		//verify
		
		String headerinformation = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinformation.contains(opporName))
		{
			System.out.println(opporName+ "is created == PASS");
		}
		else {
			System.out.println(opporName+ "is not created == Fail");

		}
		

		String organizationName = driver.findElement(By.xpath("//a[@title='Organizations']")).getText();
		if(organizationName.contains(orgName))
		{
			System.out.println(orgName+ " is created == PASS");

		}
		else {
			System.out.println(orgName + " is not created == Fail");

		}
		
	

		
		WebElement logouticon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(logouticon).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		Thread.sleep(5000);
		
		driver.quit();

		
		
	}

}
