package webdriver;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_VI_Mixing {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}
	
//	@Test 
	public void TC_01_Found() {
		driver.get("https://www.facebook.com/"); 
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
		
		System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
		
	}
	
//	@Test 
	public void TC_02_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/"); 
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
		
		
	}
	
	@Test 
	public void TC_03_Not_Found_Implicit_And_Explicit() {
		driver.get("https://www.facebook.com/"); 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);

		
		System.out.println("1.1 - Start implicit wait: " + getDateTimeNow());
		try {
			driver.findElement(By.id("selenium"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("1.2 - End implicit wait: " + getDateTimeNow());
		
		System.out.println("2.1 - Start explicit wait: " + getDateTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selenium")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2.2 - End explicit wait: " + getDateTimeNow());
		
		
	}
	
	@Test
	public void TC_02_() {
		
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
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
		
	}
}

