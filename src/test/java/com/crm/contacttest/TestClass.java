package com.crm.contacttest;



	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class TestClass {

		public static void main(String[] args) {
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			driver.get("https://www.amazon.in/");
			driver.findElement(By.xpath("//a[@data-csa-c-content-id='nav_cs_bestsellers']")).click();
			driver.findElement(By.xpath("(//a[contains(text(),'Book')])[2]")).click();
			WebElement productname = driver.findElement(By.xpath("//div[contains(text(),'PW EaJEE Handwritten Notes For Organic Chemistry')]"));
			String prodname=productname.getText();
			System.out.println(prodname);
			WebElement product = driver.findElement(By.xpath("//div[contains(text(),'PW EaJEE Handwritten Notes For Organic Chemistry')]/../../../div[4]"));
			String value=product.getText();
			System.out.println(value);
			
			
		}

	}
