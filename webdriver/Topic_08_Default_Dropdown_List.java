package webdriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	String firstName, lastName, email, companyName, passWord, cPassword, day, month, year;

	By genderRadioBy = By.id("gender-female");
	By firstNameTextboxBy = By.name("FirstName");
	By lastNameTextboxBy = By.name("LastName");
	By emailTextboxBy = By.name("Email");
	By companyNameTextboxBy = By.name("Company");
	By passwordTextboxBy = By.name("Password");
	By cPasswordTextboxBy = By.name("ConfirmPassword");
	By dateDropdownBy = By.name("DateOfBirthDay");
	By monthDropdownBy = By.name("DateOfBirthMonth");
	By yearDropdownBy = By.name("DateOfBirthYear");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Dung";
		lastName = "lt"; 
		day = "20";
		month = "January";
		year = "1997";
		companyName = "Test1";
		email = "test" + getRandomInt() + "@mail.net";
		passWord = "123456";
	}
	
	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class=\"ico-register\"]")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		assertEquals(select.getFirstSelectedOption().getText(), day);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		assertEquals(select.getFirstSelectedOption().getText(), year);

		
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(passWord);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passWord);
		driver.findElement(By.id("register-button")).click();
		 
		assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
//		driver.findElement(By.cssSelector("a.ico-account")).click();
//		
//		assertTrue(driver.findElement(genderRadioBy).isSelected());
//		assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
//		assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
//		assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
//		assertEquals(driver.findElement(companyNameTextboxBy).getAttribute("value"), companyName);
//		


	}
	
	@Test
	public void TC_02_Rode() {
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

