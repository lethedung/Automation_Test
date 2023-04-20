package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.saucedemo.LoginPageObject;
import pageObjects.saucedemo.PageGeneratorManager;
import pageObjects.saucedemo.ProductPageObject;

public class Level_19_Sort_Data extends BaseTest {
	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;

	String username = "standard_user";
	String password = "secret_sauce";

	@Parameters({ "browser", "saucedemoUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String saucedemoUrl) {
		driver = getBrowserDriver(browserName, saucedemoUrl);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.enterToUsernameTextbox(username);

		loginPage.enterToPasswordTextbox(password);

		productPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		productPage.selectItemTextInProductSortDropdown("Name (A to Z)");

		verifyTrue(productPage.isProductNameSortedBySortTypeLambda("ASC"));

		productPage.selectItemTextInProductSortDropdown("Name (Z to A)");

		verifyTrue(productPage.isProductNameSortedBySortTypeLambda("DESC"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
