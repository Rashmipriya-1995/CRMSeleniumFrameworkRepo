package com.crm.baseclasstest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.databaseUtility.DatabaseUtility;
import com.crm.generic.excelUtility.ExcelUtility;
import com.crm.generic.fileUtility.FileUtility;
import com.crm.generic.javaUtility.JavaUtility;
import com.crm.generic.webdriverUtility.WebDriverUtility;

import objectrepository.HomePage;
import objectrepository.LoginPage;

public class BaseClass {
	//create Object
	public FileUtility fLib=new FileUtility();
	public ExcelUtility excelLib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public DatabaseUtility dbLib=new DatabaseUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public static WebDriver driver=null;
	
	

	@BeforeSuite
	public void configBS()
	{
		System.out.println("Connect to DB, Report Configuration");
		
	}
//	@Parameters("browser")
	@BeforeClass
	public void openBrowser() throws IOException
	{
		System.out.println("Launch the Browser");
		String browsername = fLib.getDataFromPropertiesFile("browser");
		if(browsername.equals("chrome")){
			driver=new ChromeDriver();
		}
		else if(browsername.equals("edge")){
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
//		String browsername=browser;
		String url = fLib.getDataFromPropertiesFile("url");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
	}
	
	@BeforeMethod
	public void login() throws IOException
	{
		System.out.println("Login the Applicatiion");
		String username = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");

		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(username, password);
		
	}
	
	@AfterMethod
	public void logout()
	{
		System.out.println("Logout the Applicatiion");
		HomePage hp=new HomePage(driver);
		wUtil.mouseHover(driver,hp.getMyProfileIcon());

		hp.getLogoutLink().click();
	
	}
	
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("Close the Browser");
		driver.quit();
	}
	
	@AfterSuite
	public void con()
	{
		System.out.println("Disconnect to DB, Report back up");
	}
}
