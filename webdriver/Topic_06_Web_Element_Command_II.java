package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By emailTextBoxBy = By.id("mail");
	By educationTextareaBy = By.id("edu");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");
	By passwordTextboxBy = By.id("disable_password");
	By slider2By = By.id("slider-2");
	By ageUnder18RadioBy = By.id("under_18");
	By javaBy = By.id("java");
	By rubyBy = By.id("ruby");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/");
	}
	
	@Test
	public void TC_01_Is_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		
		if(emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("automation testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		
		if(ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is displayed");
		}
		
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		
		if(educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("automation testing");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}
		
		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		
		if(user5Text.isDisplayed()) {
			System.out.println("User 5 Text is displayed");
		} else {
			System.out.println("User 5 Text is not displayed");
		}
	}
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}
	
	public void sendKeysToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element [" + by + "] is Enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is Disable");
			return false;
		}
	}
	
	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element [" + by + "] is Selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is deSelected");
			return false;
		}
	}
	
	@Test
	public void TC_02_Is_Displayed_Refactor() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		if(isElementDisplayed(emailTextBoxBy)) {
			sendKeysToElement(emailTextBoxBy, "Automation Testing");
		}
		
		if(isElementDisplayed(ageUnder18RadioBy)) {
			clickToElement(ageUnder18RadioBy);
		}
		
		if(isElementDisplayed(educationTextareaBy)) {
			sendKeysToElement(emailTextBoxBy, "Automation Testing");
		}
		
		Assert.assertFalse(isElementDisplayed(user5TextBy));
	}
	
	@Test
	public void TC_03_Is_Enable() {
		driver.get("https://automationfc.github.io/basic-form/");
		Assert.assertTrue(isElementEnable(emailTextBoxBy));
		Assert.assertTrue(isElementEnable(ageUnder18RadioBy));
		Assert.assertTrue(isElementEnable(educationTextareaBy));
		Assert.assertFalse(isElementEnable(passwordTextboxBy));
		Assert.assertFalse(isElementEnable(slider2By));
		
	}
	
	@Test
	public void TC_04_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/");
		clickToElement(ageUnder18RadioBy);
		clickToElement(javaBy);
		
		Assert.assertTrue(isSelected(ageUnder18RadioBy));
		Assert.assertTrue(isSelected(javaBy));
		Assert.assertFalse(isSelected(rubyBy));
		
		clickToElement(ageUnder18RadioBy);
		clickToElement(javaBy);
		
		Assert.assertTrue(isSelected(ageUnder18RadioBy));
		Assert.assertFalse(isSelected(javaBy));
		Assert.assertFalse(isSelected(rubyBy));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
