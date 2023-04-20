package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on " + browserName);
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);

		// emailAdress = "nndz" + getRandomInt() + "@mail.com";
		firstName = "hlt";
		lastName = "dz";
		password = "123456";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		homePage.openRegisterPage();

		registerPage = new RegisterPageObject(driver);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtFistnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		homePage.openRegisterPage();

		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("asdf^%%");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	// @Test
	// public void TC_03_Register_Success() {
	// homePage.clickToRegisterlink();
	//
	// registerPage = new RegisterPageObject(driver);
	//
	// registerPage.inputToFirstnameTextbox(firstName);
	// registerPage.inputToLastnameTextbox(lastName);
	// registerPage.inputToEmailTextbox(emailAdress);
	// registerPage.inputToPasswordTextbox(password);
	// registerPage.inputToConfirmPasswordTextbox(password);
	//
	// registerPage.clickToRegisterButton();
	//
	// Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	//
	// registerPage.clickToLogoutLink();
	// }
	//
	// @Test
	// public void TC_04_Register_Existing_Email() {
	// homePage.clickToRegisterlink();
	//
	// registerPage = new RegisterPageObject(driver);
	//
	// registerPage.inputToFirstnameTextbox(firstName);
	// registerPage.inputToLastnameTextbox(lastName);
	// registerPage.inputToEmailTextbox(emailAdress);
	// registerPage.inputToPasswordTextbox(password);
	// registerPage.inputToConfirmPasswordTextbox(password);
	//
	// registerPage.clickToRegisterButton();
	//
	// Assert.assertEquals(registerPage.getExistingEmailErrorMessage(), "The specified email already exists");
	// }
	//
	// @Test
	// public void TC_05_Register_Password_Less_Than_6_Chars() {
	// homePage.clickToRegisterlink();
	//
	// registerPage = new RegisterPageObject(driver);
	//
	// registerPage.inputToFirstnameTextbox(firstName);
	// registerPage.inputToLastnameTextbox(lastName);
	// registerPage.inputToEmailTextbox(emailAdress);
	// registerPage.inputToPasswordTextbox("123");
	// registerPage.inputToConfirmPasswordTextbox("123");
	//
	// registerPage.clickToRegisterButton();
	//
	// Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	// }
	//
	// @Test
	// public void TC_06_Register_Invalid_Confirm_Password() {
	// homePage.clickToRegisterlink();
	//
	// registerPage = new RegisterPageObject(driver);
	//
	// registerPage.inputToFirstnameTextbox(firstName);
	// registerPage.inputToLastnameTextbox(lastName);
	// registerPage.inputToEmailTextbox(emailAdress);
	// registerPage.inputToPasswordTextbox(password);
	// registerPage.inputToConfirmPasswordTextbox(emailAdress);
	//
	// registerPage.clickToRegisterButton();
	//
	// Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	// }

	public int getRandomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
