package com.jequery;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

public class Level_11_Upload_Files extends BaseTest {
	HomePageObject homePage;
	private WebDriver driver;

	String dalatFileName = "DaLat.jpg";
	String hueFileName = "Hue.jpg";
	String sapaFileName = "Sapa.jpg";
	String[] multipleFileNames = { dalatFileName, hueFileName, sapaFileName };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		homePage.uploadMultipleFiles(driver, dalatFileName);
		Assert.assertTrue(homePage.isFileLoadedByName(dalatFileName));

		homePage.clickToStartButton();

		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(dalatFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(dalatFileName));
	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.refreshCurrentPage(driver);

		homePage.uploadMultipleFiles(driver, multipleFileNames);

		Assert.assertTrue(homePage.isFileLoadedByName(dalatFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(hueFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(sapaFileName));

		homePage.clickToStartButton();

		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(dalatFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(hueFileName));
		Assert.assertTrue(homePage.isFileLinkUpLoadedByName(sapaFileName));

		Assert.assertTrue(homePage.isFileImageUpLoadedByName(dalatFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(hueFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(sapaFileName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
