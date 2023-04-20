package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
//	@Test 
	public void TC_01_Hover_Textbox() {
		driver.get("https://automationfc.github.io/jquery-tooltip//");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	}
	
//	@Test 
	public void TC_01_Hover_Menu() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()=\"Kids\"]"))).perform();
		
		driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()=\"Home & Bath\"]")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
	}
	
	
	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// click and hold(1-4)
		
		action.clickAndHold(allNumber.get(0)) // click vào số 1 và giữ chuột
		.moveToElement(allNumber.get(3)) // di chuột đến số 4
		.release() // nhả chuột trái
		.perform(); // thực hiện các hành động
		Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 4);
	}
	
	@Test 
	public void TC_03_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// nhấn Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Chọn random các số
		
		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(4))
		.release() // nhả chuột trái
		.perform(); // thực hiện các hành động
		
		// nahr phím Ctrl
		action.keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 3);
		
	}
	
	@Test
	public void TC_04_Double_Click() {
		
		// sử dụng action.doubleClick()
		// có thể click 2 lần
		
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

