package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}
//	@Test
//	public void TC_01_Xpath() {
//		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//	} 
	
	@Test
	public void TC_02_Login_Empty_Email_And_Pw() {
		// nhập dữ liệu vào textbox
		driver.findElement(By.id("email")).sendKeys("");
		driver.getPageSource();
		// click button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		// get message error
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	} 
	
	@Test
	public void TC_03_Login_Invalid_Email() {
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("123@122.123");
		driver.findElement(By.name("login[password]")).sendKeys("");
		// click button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	} 
	
	@Test
	public void TC_04_Login_Invalid_Pw() {
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("dunglt@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		// click button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	} 
	
	@Test
	public void TC_05_Incorrect_Email() {
		driver.navigate().refresh();
		driver.findElement(By.id("email")).sendKeys("dunglt123@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1231234");
		// click button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");
	} 
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
