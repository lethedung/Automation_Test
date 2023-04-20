package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_Part_II {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String test1Name = "test1.jpg";
	String test2Name = "test2.jpg";
	String test3Name = "test3.jpg";
	String test1Path = projectPath + "\\img\\" + test1Name;
	String test2Path = projectPath + "\\img\\" + test2Name;
	String test3Path = projectPath + "\\img\\" + test3Name;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 20);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
//	@Test 
	public void TC_01_Sendkey_Single_File() {
		driver.get("https://www.youtube.com/watch?v=Y27_spMQ4Wo"); 
		
		// Load file
		driver.findElement(By.xpath("//input[@name='files[]']")).click();
		
		
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

