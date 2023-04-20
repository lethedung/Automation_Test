package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_I {
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
	public void TC_01_() {
		// Web browser command/ method/ API
		// driver instance/variable
		
		//Web Element command/ method/ API
		// 1
		driver.findElement(By.name("login")).click();
		
		// 2
		WebElement element = driver.findElement(By.name("email"));
		// xoá dữ liệu trong textbox
		element.clear();
		// Nhập dữ liệu vào 
		element.sendKeys("aa");
		element.sendKeys(Keys.ENTER);
		
		// click vào button
		element.click();
		// trả về dữ liệu nằm trong Attribute của element
		element.getAttribute("");
		
		// lấy thuộc tính của element /font size/size/color
		element.getCssValue("");
		
		// GUI
		element.getLocation();
		element.getRect();
		element.getSize();
		
		
		// Take screeenshot -> attach to HTML report
		element.getScreenshotAs(OutputType.FILE);
		
		// tên thẻ HTML
		// Dùng By.id/class/css/name
		element.getTagName();
		
		// lấy text
		element.getText();
		
		// kiểm tra 1 element có hiển thị hay ko( nhìn thấy và thao tác đc)
		element.isDisplayed();
		
		// Kiểm tra 1 element có thể thao tác đc hay ko( ko bị disable/ko phải readonly)
		element.isEnabled();
		// Ktra 1 element đã đc chọn hay chưa(radio/checkbox/dropdown)
		element.isSelected();
		// submit vào 1 form
		element.submit();
	}
	
	@Test
	public void TC_02_() {
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

