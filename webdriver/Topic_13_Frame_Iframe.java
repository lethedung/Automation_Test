package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {
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
	public void TC_01_Iframe() {
		// HTML A (Parent)
		driver.get("https://kyna.vn/");
		
		// Switch vào Iframe chứ element // HTML B 
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		String kynaFanpageLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(kynaFanpageLike);
		
		// Back to parent page A
		driver.switchTo().defaultContent();
		
	}
	
	@Test
	public void TC_02_Frame() {
//		driver.get("");
		
		
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

