package webdriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		email = "dunglt" + getRandomInt() + "@gmail.net";
		
	}
	
//	@Test 
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys(email);
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(1);
		
		// verify màu button 
		String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		Assert.assertEquals(hexaColor, "#C92127");
		// verify login button isEnabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// remove disable attribute của login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		
		
		sleepInSecond(2);
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		
		
		
	}
	
//	@Test
	public void TC_02_Radio_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		By petrolTwo = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By petrolThree = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
		driver.findElement(petrolTwo).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		driver.findElement(petrolTwo).click();
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		
		Assert.assertFalse(driver.findElement(petrolThree).isSelected());
		driver.findElement(petrolThree).click();
		Assert.assertTrue(driver.findElement(petrolThree).isSelected());
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
		
	}
	
//	@Test
	public void TC_03_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By heated = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By dualZone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(heated).isSelected());
//		driver.findElement(heated).click();
		checkToCheckbox(heated);
		Assert.assertTrue(driver.findElement(heated).isSelected());
//		driver.findElement(heated).click();
		unCheckToCheckbox(heated);
		Assert.assertFalse(driver.findElement(heated).isSelected());
		checkToCheckbox(heated);
		Assert.assertTrue(driver.findElement(heated).isSelected());
		
		Assert.assertFalse(driver.findElement(dualZone).isSelected());
		checkToCheckbox(dualZone);
		Assert.assertTrue(driver.findElement(dualZone).isSelected());
		Assert.assertTrue(driver.findElement(heated).isSelected());
		
	}
	
	@Test
	public void TC_04_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winter = By.xpath("//input[@value='Winter']");
//		driver.findElement(winter).click();
		clickToElementByJS(winter);
		
		Assert.assertTrue(driver.findElement(winter).isSelected());
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
	
	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void clickToElementByJS(By by) {
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
	}
}

