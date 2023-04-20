package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command_II {
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
//		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_01_() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Customer Login");
	}
	
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.navigate().back();
		
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Customer Login");
		
	}
	
	@Test
	public void TC_04_Page_Source() {
//		driver.get("http://live.techpanda.org/index.php/");
//		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//		String registerPageSource = driver.getPageSource();
//		Assert.assertTrue(registerPageSource.contains("LOGIN OR CREATE AN ACCOUNT"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

