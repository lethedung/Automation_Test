package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_V_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		
		expliciWait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
	}
	
//	@Test
	public void TC_01_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// Wait cho loading icon biến mất
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
//	@Test
	public void TC_02_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// Wait cho chữ hiện lên
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
	@Test
	public void TC_03_Date_Picker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Thursday, March 16, 2023']/a")));
		
		driver.findElement(By.xpath("//td[@title='Thursday, March 16, 2023']/a")).click();
		
		// Wait cho loading mất đi
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']/div[@class='raDiv']")));
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='16']")));
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='16']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Thursday, March 16, 2023");
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

