package webdriver;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command_I {
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
	}
	
	@Test
	public void TC_01_Browser() {
		// Mở ra page URL
		driver.get("https://web.skype.com/");
		
		// đóng 1 tab đang active
		driver.close();
		
		//Đóng trình duyệt
		driver.quit();
		
		// lấy ra ID hiện tại của window/tab đang active
		String messengerID = driver.getWindowHandle();
		
		// Lấy ra tất cả ID của các tab
		Set<String> allDs = driver.getWindowHandles();
		
		// switch/nhảy đến 1 tab/window đang có
		driver.switchTo().window(messengerID);
		
		// Tìm ra 1 element với locator nào đó
		WebElement emailTextbox = driver.findElement(By.id(""));
		
		// Tìm ra tất cả element với locator nào đó
		List<WebElement> textboxes = driver.findElements(By.id(""));
		
		// Trả về URL page hiện tại
		String homePageUrl = driver.getCurrentUrl();
		
		// Trả về HTML source của page hiện tại
		String homePageSource = driver.getPageSource();
		
		// trả về title của page hiện tại
		String homePageTitlr = driver.getTitle();		
		
		// get/xoá cookie của page 
		driver.manage().deleteAllCookies();
		
		// build framework: get ra log của browser
		driver.manage().logs().getAvailableLogTypes();
		
		// chờ cho việc tìm element(findElement, findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Chờ 1 page đc load thành công(Option)
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		// Chờ 1 script đc execute thành công
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		// mở browser full màn hình
		driver.manage().window().fullscreen();
		
		// maximize màn hình
		driver.manage().window().maximize();
		
		// lấy ra vị trí hiện tại của Browser
		driver.manage().window().getPosition();
		
		// lấy ra kích thước hiện tại của Browser
		driver.manage().window().getSize();
//		driver.manage().window().setSize(new Dimension(1920,1080));
		
		
		// forward to page
		driver.navigate().forward();
		
		// tải lại trang
		driver.navigate().refresh();
		
		// keep history
		driver.navigate().to("https://web.skype.com/");
		
		driver.switchTo().alert();
		driver.switchTo().window("");
		driver.switchTo().frame("");
		
	}
	
	@Test
	public void TC_02_Element() {
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
