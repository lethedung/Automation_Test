package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import reportConfig.ExtentManager;

public class Level_15_ExtentV2_Screenshot extends BaseTest {
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
	}

	@Test
	public void User_01_Register(Method method) {
		// ExtentManager.startTest(method.getName(), "User_01_Register");
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register page'");
		// registerPage = homePage.openRegisterPage();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		// registerPage.inputToFirstnameTextbox(firstName);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		// registerPage.inputToLastnameTextbox(lastName);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Enter to Email textbox with value is '" + email + "'");
		// registerPage.inputToEmailTextbox(email);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		// registerPage.inputToPasswordTextbox(password);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		// registerPage.inputToConfirmPasswordTextbox(password);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to 'Register' Button");
		// registerPage.clickToRegisterButton();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08: Verify register success massage is displayed");
		// assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed!");
		// ExtentManager.endTest();
	}

	@Test
	public void User_02_Login(Method method) {
		// ExtentManager.startTest(method.getName(), "User_02_Login");
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
		// homePage = registerPage.clickToLogoutLink();
		// loginPage = homePage.openLoginPage();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 02: Enter to Email textbox with value is '" + email + "'");
		// loginPage.inputToEmailTextbox(email);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		// loginPage.inputToPasswordTextbox(password);
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click to Login button");
		// homePage = loginPage.clickToLoginButton();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
		// assertFalse(homePage.isMyAccountLinkDisplayed());
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to My Account page");
		// customerInforPage = homePage.openMyAccountPage();
		//
		// ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 07: Verify 'Customer Infor' page is displayed");
		// assertFalse(customerInforPage.isCustomerInforPageDisplayed());
		// ExtentManager.endTest();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
}
