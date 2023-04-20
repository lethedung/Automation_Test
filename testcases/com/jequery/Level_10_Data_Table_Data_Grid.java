package com.jequery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_10_Data_Table_Data_Grid extends BaseTest {
	HomePageObject homePage;

	private WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("3");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("3"));

		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("7"));

		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("10"));

		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("20"));
	}

	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);

		homePage.enterToHeaderTextboxByLabel("Country", "South");
		homePage.enterToHeaderTextboxByLabel("Females", "");
		homePage.enterToHeaderTextboxByLabel("Males", "");
		homePage.enterToHeaderTextboxByLabel("Total", "");
	}

	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadDataButton();
		homePage.sleepInSecond(2);

		homePage.enterToTextboxAtRowNumberByColumnName("Album", "1", "Michael 97");

		homePage.enterToTextboxAtRowNumberByColumnName("Artist", "2", "Michael Jackson");

		homePage.enterToTextboxAtRowNumberByColumnName("Year", "3", "1997");

		homePage.enterToTextboxAtRowNumberByColumnName("Price", "4", "300");

		homePage.selectDropdownByColumnNameAtRowNumber("Origin", "5", "Japan");

		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(2);

		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(2);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
