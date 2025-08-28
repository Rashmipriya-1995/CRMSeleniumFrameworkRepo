package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.generic.excelUtility.ExcelUtility;

public class GetProductInfoTest {
	
	@Test(dataProvider="getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//search product
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(brandName,Keys.ENTER);
		
		//capture price of the product
		
		WebElement priceEle = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]"));
		String productprice = priceEle.getText();
		System.out.println(productprice);
		
		driver.quit();
				//driver.quit();

		
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
	ExcelUtility eLib=new ExcelUtility();
	int rowCount = eLib.getRowCount("Sheet2");
		
	Object[][] objArr=new Object[rowCount][2];
	
	for(int i=0; i<rowCount;i++) {
		objArr[i][0]=eLib.getDataFromExcelFile("Sheet2", i+1, 0);// 0 index is header so don't need capture header row and start from i+1
		objArr[i][1]=eLib.getDataFromExcelFile("Sheet2", i+1, 1);
		


	}
		
	return objArr;
	}
		

}
