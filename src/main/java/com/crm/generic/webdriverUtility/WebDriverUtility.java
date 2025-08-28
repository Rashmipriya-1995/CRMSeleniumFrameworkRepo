package com.crm.generic.webdriverUtility;

	import java.awt.Window;
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Set;

	import org.jspecify.annotations.Nullable;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.Select;

import com.crm.generic.javaUtility.JavaUtility;
	/**
	 * @author Rashmipriya
	 */
	public class WebDriverUtility {
		/**
		 * This method is used to maximize the window
		 * @param driver
		 */
		public void maximize(WebDriver driver) {
			driver.manage().window().maximize();
		}
		/**
		 * This method is used to perform mouse hover actions on the elements
		 * @param driver
		 * @param element
		 */

		public void mouseHover(WebDriver driver, WebElement element) {
			Actions act=new Actions(driver);
			act.moveToElement(element).perform();
			
		}
		/**
		 * This method is used to perform right click actions on the elements
		 * @param driver
		 * @param element
		 */

		public void rightClick(WebDriver driver, WebElement element) {
			Actions act=new Actions(driver);
			act.contextClick(element).perform();
			
		}
		/**
		 * This method is used to perform click and hold actions on the elements
		 * @param driver
		 * @param element
		 */

		public void clickAndHold(WebDriver driver, WebElement element) {
			Actions act=new Actions(driver);
			act.clickAndHold(element).perform();
			
		}
		/**
		 * This method is used to perform drag and drop actions on the elements
		 * @param driver
		 * @param element
		 * @param target
		 */

		public void dragAndDrop(WebDriver driver, WebElement element,WebElement target) {
			Actions act=new Actions(driver);
			act.dragAndDrop(element, target).perform();
			
		}
		/**
		 * This method is used to select option in the dropdown or listbox based on the index value
		 * @param element
		 * @param index
		 */
		public void selectByIndex(WebElement element,int index) {
			Select ref=new Select(element);
			ref.selectByIndex(index);
		}
		/**
		 * This method is used to select option in the dropdown or listbox based on the value which is present in the option tag
		 * @param element
		 * @param value
		 */
		public void selectByValue(WebElement element,String value) {
			Select ref=new Select(element);
			ref.selectByValue(value);
		}
		/**
		 *  This method is used to select option in the dropdown or listbox based on the text which is present in between option tag
		 * @param element
		 * @param value
		 */
		public void selectByVisibleText(WebElement element,String text) {
			Select ref=new Select(element);
			ref.selectByVisibleText(text);
		}
		/**
		 * This method is used to capture all the options in the dropdown or listbox
		 * @param element
		 * @param text
		 * @return options
		 */
		
		public String[] getAllOptions(WebElement element,String text) {
			Select ref=new Select(element);
			List<WebElement> allOptions = ref.getOptions();
			
			String[] options = new String[allOptions.size()];
			for(int i=0;i<allOptions.size();i++) {
				options[i]=allOptions.get(i).getText();
			}
			return options;
		}
		/**
		 * This method is used to switch to frame by using Index
		 * @param driver
		 * @param index
		 */
		public void switchToFrame(WebDriver driver,int index) {
			driver.switchTo().frame(index);
			
		}
		/**
		 * This method is used to switch to frame by using name or id
		 * @param driver
		 * @param nameOrId
		 */
		public void switchToFrame(WebDriver driver,String nameOrId) {
			driver.switchTo().frame(nameOrId);
			
		}
		/**
		 * This method is used to switch to frame by using web element
		 * @param driver
		 * @param element
		 */
		public void switchToFrame(WebDriver driver,WebElement element) {
			driver.switchTo().frame(element);
			
		}
		/**
		 * This method is used to switch back to main page
		 * @param driver
		 */
		public void switchBackToMainPage(WebDriver driver) {
			driver.switchTo().defaultContent();
			
		}
		/**
		 * This method is used to switch to alert in the web page
		 * @param driver
		 * @return Alert
		 */
		public Alert switchToAlert(WebDriver driver) {
			return driver.switchTo().alert();
		}
		/**
		 * This method is used to switch to window
		 * @param driver
		 * @param expUrl
		 */
		public void switchToWindow(WebDriver driver,String expUrl) {
			Set<String> allWindowIds = driver.getWindowHandles();
			for(String id:allWindowIds) {
				driver.switchTo().window(id);
				
				String actUrl = driver.getCurrentUrl();
				if(actUrl.contains(expUrl))
				{
					break;
				}
					
			}
			
		}
		/**
		 * This method is used to take screenshot of the web page
		 * @param driver
		 * @throws IOException
		 */
		public void getPhoto(WebDriver driver) throws IOException {
			
			JavaUtility jlib=new JavaUtility();
			
			TakesScreenshot ts=(TakesScreenshot) driver;
			
			File temp = ts.getScreenshotAs(OutputType.FILE);
			
			File dest=new File("./Screenshots/"+jlib.getSystemTime()+".png");
			
			FileHandler.copy(temp, dest);
		}
		/**
		 * This method is used to scrolling the window
		 * @param driver
		 * @param x
		 * @param y
		 */
		public void jsScroll(WebDriver driver,int x,int y) {
			JavascriptExecutor js=(JavascriptExecutor) driver;
			
			js.executeScript("window.scrollTo("+x+","+y+")");
			
		}
	}



