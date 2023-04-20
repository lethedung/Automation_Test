package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_II_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Timeout_Less_Than_Element_Display() {
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
	@Test
	public void TC_02_Timeout_Equal_Element_Display() {
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
	}
	
	@Test
	public void TC_03_Timeout_Greater_Than_Element_Display() {
//		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
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

