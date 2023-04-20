package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	String userID, password, loginUrl;
	String name, gender, dobInput, dobOutput, addressInput, addressOutput, city, state, pin, phone, email;
	String customerID, addressInputEdit, addressOutputEdit, cityEdit, stateEdit, pinEdit, mobileNumberEdit, emailEdit;

	By nameTextboxBy = By.name("name");
	By genderRadionBy = By.xpath("//input[@value='m']");
	By dobTextboxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTexboxBy = By.name("city");
	By stateTexboxBy = By.name("state");
	By pinTexboxBy = By.name("pinno");
	By phoneTexboxBy = By.name("telephoneno");
	By emailTexboxBy = By.name("emailid");
	By passwordTexboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");

		name = "test";
		gender = "male";
		dobInput = "01-01-1990";
		dobOutput = "1990-01-01";
		addressInput = "03 Duy Tan\nCau Giay";
		addressOutput = "03 Duy Tan Cau Giay";
		city = "Ha Noi";
		state = "DBSH";
		pin = "666666";
		phone = "0987654321";
		email = "test" + getRandomInt() + "@mail.net";

		addressInputEdit = "Edited\nAddress";
		addressOutputEdit = "Edited Address";
		cityEdit = "Edited  City";
		stateEdit = "Edited State";
		pinEdit = "999999";
		mobileNumberEdit = "0123456789";
		emailEdit = "test1" + getRandomInt() + "@hotmail.net";
	}

	@Test
	public void TC_01_Register() {
		loginUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(emailTexboxBy).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadionBy).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('tyte')", driver.findElement(dobTextboxBy));
		
		driver.findElement(dobTextboxBy).sendKeys(dobInput);
		driver.findElement(addressTextAreaBy).sendKeys(addressInput);
		driver.findElement(cityTexboxBy).sendKeys(city);
		driver.findElement(stateTexboxBy).sendKeys(state);
		driver.findElement(pinTexboxBy).sendKeys(pin);
		driver.findElement(phoneTexboxBy).sendKeys(phone);
		driver.findElement(emailTexboxBy).sendKeys(email);
		driver.findElement(passwordTexboxBy).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

		assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());
		assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOutput);
		assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		assertEquals(driver.findElement(addressTextAreaBy).getText(), addressInput);

		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(addressTextAreaBy).sendKeys(addressInputEdit);
		driver.findElement(cityTexboxBy).clear();
		driver.findElement(cityTexboxBy).sendKeys(cityEdit);
		driver.findElement(stateTexboxBy).clear();
		driver.findElement(stateTexboxBy).sendKeys(state);
		driver.findElement(pinTexboxBy).clear();
		driver.findElement(pinTexboxBy).sendKeys(pinEdit);
		driver.findElement(phoneTexboxBy).clear();
		driver.findElement(phoneTexboxBy).sendKeys(mobileNumberEdit);
		driver.findElement(emailTexboxBy).clear();
		driver.findElement(emailTexboxBy).sendKeys(emailEdit);

		driver.findElement(By.name("sub")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}