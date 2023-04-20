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

public class Topic_16_Upload_File_Part_I {
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
		driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 
		
		// Load file
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(test1Path);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test1Name + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(test2Path);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test2Name + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(test3Path);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test3Name + "']")).isDisplayed());
		
		List<WebElement> buttonStarts = driver.findElements(By.xpath("//tbody//button[@class=\"btn btn-primary start\"]"));
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInSecond(1);
		}
		
		// verify upload file success
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test2Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test3Name + "']")).isDisplayed());
		
		
	}
	
//	@Test
	public void TC_02_Sendkey_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/"); 
		
		// Load file
		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(test1Path + "\n" + test2Path + "\n" + test3Path);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test2Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + test3Name + "']")).isDisplayed());
		
		List<WebElement> buttonStarts = driver.findElements(By.xpath("//tbody//button[@class=\"btn btn-primary start\"]"));
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInSecond(1);
		}
		
		// verify upload file success
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test2Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + test3Name + "']")).isDisplayed());
		
	}
	
	@Test
	public void TC_03_GoFile() {
		driver.get("https://gofile.io/uploadFiles"); 
		
		// Load file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(test1Path + "\n" + test2Path + "\n" + test3Path);
		sleepInSecond(1);
		expliciWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-auto text-center']/div[text()='Your files have been successfully uploaded']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-auto text-center']/div[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		driver.get(driver.findElement(By.xpath("//div[@class='row mb-2 mainUploadSuccessLink']//a[@class='ajaxLink']")).getAttribute("href"));
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + test1Name + "']")).isDisplayed());
		
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

