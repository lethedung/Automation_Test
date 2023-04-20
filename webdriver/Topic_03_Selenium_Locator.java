package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Selenium_Locator {
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
	
	public void TC_01_FindElement() {
		// Single element
		driver.findElement(By.className("")).click();
		driver.findElement(By.className("")).getText();
		
		// Mutiple element
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	} 
	
	@Test
	public void TC_02_ID() {
		// Locator
		driver.findElement(By.id("send2")).click();
		
		// Veryfy email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	} 
	
	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();
		driver.findElement(By.className("validate-password")).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	} 
	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();
		driver.findElement(By.name("send")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	} 
	@Test
	public void TC_05TagName() {
		driver.navigate().refresh();
		// Lấy hết tất cả đường link
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	} 
	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	} 
	@Test
	public void TC_07_PartialLinkText() {
		driver.navigate().refresh();
		driver.findElement(By.partialLinkText("Back to")).click();
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	} 
	@Test
	public void TC_08_Css() {
		driver.findElement(By.cssSelector("#email")).sendKeys("dung@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("12345678");
	} 
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("dung12@gmail.com");
		driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input")).sendKeys("12345678");
	} 
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
