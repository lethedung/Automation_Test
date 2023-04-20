package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Common_01_Register_Cookie extends BaseTest {

	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Class Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		email = "nndz" + getRandomInt() + "@mail.com";
		firstName = "hlt";
		lastName = "dz";
		password = "123456";

		log.info("Pre-Condition - Step 01: Navigate to 'Register page'");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '" + email + "'");
		registerPage.inputToEmailTextbox(email);

		log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-Condition - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-Condition - Step 07: Click to 'Register' Button");
		registerPage.clickToRegisterButton();

		log.info("Pre-Condition - Step 08: Verify register success massage is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-Condition - Step 09: Click to logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("Condition - Step 10: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Condition - Step 11: Enter to Email textbox with value is '" + email + "'");
		loginPage.inputToEmailTextbox(email);

		log.info("Condition - Step 12: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Condition - Step 13: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		loggedCokies = homePage.getAllCookies(driver);

	}

	@AfterTest
	public void afterClass() {
		// driver.quit();
	}

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private WebDriver driver;
	private String email, password, firstName, lastName;
	public static Set<Cookie> loggedCokies;
}
