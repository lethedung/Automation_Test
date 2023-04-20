package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Level_18_Pattern_Object extends BaseTest {
	private WebDriver driver;
	private String email, firstName, lastName, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = "nndz" + getRandomInt() + "@mail.com";
		firstName = "hlt";
		lastName = "dz";
		password = "123456";
		day = "10";
		month = "October";
		year = "2000";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register page'");
		registerPage = homePage.openRegisterPage();

		registerPage.clickToRadioButtonByLabel(driver, "Gender");

		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", day);
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);

		log.info("Register - Step 04: Enter to Email textbox with value is '" + email + "'");
		registerPage.inputToTextboxByID(driver, "Email", email);

		registerPage.clickToCheckboxByLabel(driver, "Newsletter");

		log.info("Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register - Step 07: Click to 'Register' Button");
		registerPage.clickToButtonByText(driver, "Register");

		log.info("Register - Step 08: Verify register success massage is displayed");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		homePage = registerPage.clickToLogoutLink();
		loginPage = homePage.openLoginPage();

		log.info("Login - Step 02: Enter to Email textbox with value is '" + email + "'");
		loginPage.inputToTextboxByID(driver, "Email", email);

		log.info("Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Login - Step 04: Click to Login button");
		registerPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		assertTrue(homePage.isMyAccountLinkDisplayed());

		log.info("Login - Step 06: Navigate to My Account page");
		customerInforPage = homePage.openMyAccountPage();

		log.info("Login - Step 07: Verify 'Customer Infor' page is displayed");
		assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_03_Account() {
		log.info("Account - Step 01: Navigata to My Account page");
		customerInforPage = homePage.openMyAccountPage();

		log.info("Account - Step 02: Verify 'Customer Infor' page is displayed");
		assertTrue(customerInforPage.isCustomerInforPageDisplayed());

		log.info("Account - Step 03: Verify 'First name' textbox is '" + firstName + "'");
		assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), firstName);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
	private String day, month, year;
}
