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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		expliciWait= new WebDriverWait(driver,20);
		
	}
	
//	@Test 
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"))).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("dunglt@gmail.com");
		// Wait cho Element được visible
		Dimension confirmEmail = expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))).getSize();
		System.out.println("height" + confirmEmail.getHeight());
		System.out.println("Width" + confirmEmail.getWidth());
		
	}
	
//	@Test
	public void TC_02_Invisible_In_DOM() {
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"))).click();

		// Wait cho Element được invisible/ undisplayed
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
//	@Test
	public void TC_03_Presence_In_UI () {
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"))).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("dunglt@gmail.com");
		
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
//	@Test
	public void TC_03_Presence_Not_In_UI () {
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"))).click();
		
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']"))).click();
		
		sleepInSecond(3);
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		// Element đang có trong DOM
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img"))).click();
		
		// Wait cho confirm email staleness
		expliciWait.until(ExpectedConditions.stalenessOf(confirmEmail));
		
		
		
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

