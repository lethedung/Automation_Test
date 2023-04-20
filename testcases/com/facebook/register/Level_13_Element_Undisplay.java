package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplay extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Display() {
		loginPage.clickToCreatNewAccountButton();

		loginPage.enterToEmailAdressTextbox("hlt@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAdressTextboxDisplayed());
	}

	@Test
	public void TC_02_Verify_Element_Undisplay_In_DOM() {
		loginPage.enterToEmailAdressTextbox("");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAdressTextboxUndisplayed());
	}

	@Test
	public void TC_03_Verify_Element_Undisplay_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);

		verifyTrue(loginPage.isConfirmEmailAdressTextboxUndisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
