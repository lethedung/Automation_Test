package webdriver;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tab {
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

	}
	
//	@Test 
	public void TC_01_ID() {
		// Tab A: parent page
		driver.get("https://automationfc.github.io/basic-form/");
		
		// get ra ID của windows/ tab đang active
		String parentPageID = driver.getWindowHandle();
		
		// Tab B
		driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();

		
		switchToWindowByID(parentPageID);
		
		String goodlePageID = driver.getWindowHandle();
		driver.findElement(By.name("q")).sendKeys("selenium");
		driver.findElement(By.name("btnK")).click();
		
		// Switch lại parent page
		switchToWindowByID(goodlePageID);
		sleepInSecond(2);
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();

		// nhảy qua google
		switchToWindowByTitle("Google");
		
		driver.findElement(By.name("q")).sendKeys("selenium");
		driver.findElement(By.name("btnK")).click();
		
		// nhảy về parent
		switchToWindowByTitle("Selenium WebDriver");
		sleepInSecond(2);
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
	
	public void switchToWindowByID(String windowPageID) {
		
		// lấy ra ID của các tab
		Set<String> allWindows = driver.getWindowHandles();
		// Dùng vòng lắp duyệt qua từng ID
		for (String window : allWindows) {
			// Nếu như ID nào khác ID truyền vào
			if (!window.equals(windowPageID)) {
				// switch qua
				driver.switchTo().window(window);
			}
		}
	}
	
	
	public void switchToWindowByTitle(String pageTitle) {
		// lấy ra ID của các tab
		Set<String> allWindows = driver.getWindowHandles();
		
		// Dùng vòng lắp duyệt qua từng ID
		for (String window : allWindows) {
			// switch qua
			driver.switchTo().window(window);
			
			// kiểm tra sau
			String actualPageTittle = driver.getTitle();
			if (actualPageTittle.equals(pageTitle)) {
				break;
			}
			
		}
	}
}

