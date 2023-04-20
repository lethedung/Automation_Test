package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String name, emailAddress, password, phone;

	// Action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By cfEmailTextboxBy = By.id("txtCEmail");
	By pwTextboxBy = By.id("txtPassword");
	By cfPwTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButton = By.xpath("//form[@id='frmLogin']//button");

	// Errors
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By cfEmailErrorMsgBy = By.id("txtCEmail-error");
	By pwErrorMsgBy = By.id("txtPassword-error");
	By cfPwErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		name = "dunglt";
		emailAddress = "dunglt@gmail.com";
		password = "1234567";
		phone = "0987654321";
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_02_Empty() {
		// Vui lòng nhập họ tên
		// Vui lòng nhập email
		// Vui lòng nhập lại địa chỉ email
		// Vui lòng nhập mật khẩu
		// Vui lòng nhập lại mật khẩu
		// Vui lòng nhập số điện thoại.

		driver.findElement(registerButton).click();

		// get message error
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(cfEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(pwErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(cfPwErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_03_Invalid_Email() {
		
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123@123");
		driver.findElement(cfEmailTextboxBy).sendKeys("123@123@123");
		driver.findElement(pwTextboxBy).sendKeys(password);
		driver.findElement(cfPwTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(cfEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Incorrect_Confirm_Email() {
		// Email nhập lại không đúng
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(cfEmailTextboxBy).sendKeys("123@gmail.com");
		driver.findElement(pwTextboxBy).sendKeys(password);
		driver.findElement(cfPwTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(cfEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_05_PW_Less_Than_6_Chars() {
		// Mật khẩu phải có ít nhất 6 ký tự
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(cfEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(pwTextboxBy).sendKeys("123");
		driver.findElement(cfPwTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(pwErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(cfPwErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_06_Incorrect_Confirm_PW() {
		// Mật khẩu bạn nhập không khớp
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(cfEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(pwTextboxBy).sendKeys(password);
		driver.findElement(cfPwTextboxBy).sendKeys("1233212");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(cfPwErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_07_Invalid_Phone() {

		// Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 -
		// 07 - 08

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(cfEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(pwTextboxBy).sendKeys(password);
		driver.findElement(cfPwTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys("00");
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		// Số điện thoại phải từ 10-11 số.
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("098765432123456");
		driver.findElement(registerButton).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
