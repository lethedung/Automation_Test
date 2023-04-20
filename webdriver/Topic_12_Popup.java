package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
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
	
//	@Test 
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPupup = By.xpath("//div[@class='modal fade in']");
		
		Assert.assertFalse(driver.findElement(loginPupup).isDisplayed());
		
		driver.findElement(By.xpath("//button[@onclick='openLoginDialog(1)']")).click();
		Assert.assertTrue(driver.findElement(loginPupup).isDisplayed());
		
	}
	
//	@Test
	public void TC_02_Random_Popup_In_DOM() {
		// step 1
		driver.get("https://blog.testproject.io/");
		
		By mailPopup = By.cssSelector("div.mailch-wrap");
		
		// step 2: Nếu hiển thị thì close popup
		if (driver.findElement(mailPopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			Assert.assertFalse(driver.findElement(mailPopup).isDisplayed());
		}
		
		// Wait for page load
		Assert.assertTrue(isPageLoadSuccess(driver));
		
		// step 3
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		// step 4 verify tất cả post title contain selenium
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postTitle : postTitles) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
		
		
	}
	
	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		sleepInSecond(5);
		
		By shopeePopupBy = By.cssSelector("div.shopee-popup__container");
		
		// step 2: Nếu hiển thị thì close popup
		// ko hiển thị thì qua step 3
		
		List<WebElement> shopeePopupElement = driver.findElements(shopeePopupBy);

		
		if (shopeePopupElement.size() > 0) {
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			
//			Assert.assertEquals(driver.findElements(shopeePopupBy).size(), 0);
		}
		
		// step 3
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
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
	
	public boolean isPageLoadSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.action === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
}

