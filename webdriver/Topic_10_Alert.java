package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
//	@Test 
	public void TC_01_Accept_Alert() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);
		
		// wait cho alert xuất hiện trong vòng 15s
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		// get text của 1 alert chưa biến mất
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		// switchTo: Alert/ Windows/Tab/ Frame/ IFrame
//		alert = driver.switchTo().alert();
		// Accept alert: alert sẽ biến mất (OK)
		alert.accept();	
		
		// Cancel alert: alert sẽ biến mất (Cancel)
//		alert.dismiss();
		
		
	}
	
//	@Test
	public void TC_02_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Alert\"]")).click();
		sleepInSecond(2);
		
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();	
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()=\"You clicked an alert successfully \"]")).getText(), "You clicked an alert successfully");
	}
	
//	@Test 
	public void TC_03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		sleepInSecond(2);
		
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();	
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()=\"You clicked: Ok\"]")).getText(), "You clicked: Ok");
		
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		sleepInSecond(2);
		
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()=\"You clicked: Cancel\"]")).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();
		sleepInSecond(2);
		
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String addressName = "Thanh Hoa";
		
		alert.sendKeys(addressName);
		
		sleepInSecond(2);
		
		alert.accept();	
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()=\"You entered: Thanh Hoa\"]")).getText(), "You entered: " + addressName);
		

		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();
		sleepInSecond(2);
		
		// Vừa wait vừa switch
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		
		alert.sendKeys(addressName);
		
		sleepInSecond(2);
		
		alert.dismiss();	
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()=\"You entered: null\"]")).getText(), "You entered: null");
		
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getRandomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}

