package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserDataMapper;

import commons.BaseTest;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Level_20_Manage_Data_Json extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		userData = UserDataMapper.getUserData();
		emailAddress = userData.getEmailAddress() + getRandomInt() + "@fakemail.com";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to 'Register page'");
		registerPage = homePage.openRegisterPage();

		registerPage.clickToRadioButtonByLabel(driver, "Male");

		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + userData.getFirstName() + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", userData.getFirstName());

		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + userData.getLastName() + "'");
		registerPage.inputToTextboxByID(driver, "LastName", userData.getLastName());

		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", userData.getDate());
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", userData.getMonth());
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", userData.getYear());

		log.info("Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		registerPage.clickToCheckboxByLabel(driver, "Newsletter");

		log.info("Register - Step 05: Enter to Password textbox with value is '" + userData.getPassword() + "'");
		registerPage.inputToTextboxByID(driver, "Password", userData.getPassword());

		log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + userData.getPassword() + "'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", userData.getPassword());

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

		log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("Login - Step 03: Enter to Password textbox with value is '" + userData.getPassword() + "'");
		loginPage.inputToTextboxByID(driver, "Password", userData.getPassword());

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

		log.info("Account - Step 03: Verify 'First name' textbox is '" + userData.getFirstName() + "'");
		assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), userData.getFirstName());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
	private String emailAddress;
	UserDataMapper userData;
}
