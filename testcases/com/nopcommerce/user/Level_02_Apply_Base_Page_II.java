// package com.nopcommerce.user;
//
// import java.util.Random;
// import java.util.concurrent.TimeUnit;
//
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.testng.Assert;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Test;
//
// import commons.BasePage;
//
// public class Level_02_Apply_Base_Page_II {
// WebDriver driver;
// BasePage basePage;
// String projectPath = System.getProperty("user.dir");
// String emailAdress;
//
// @BeforeClass
// public void beforeClass() {
// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
// driver = new ChromeDriver();
// basePage = BasePage.getBasePageObject();
//
// emailAdress = "nndz" + getRandomInt() + "@mail.com";
// driver.manage().window().maximize();
// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
// driver.get("https://demo.nopcommerce.com/");
// }
//
// @Test
// public void TC_01_Register_Empty_Data() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
// }
//
// @Test
// public void TC_02_Register_Invalid_Email() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.checkToDefaultCheckBoxRadio(driver, "//input[@id='gender-male']");
// basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "nn");
// basePage.sendKeyToElement(driver, "//input[@id='LastName']", "dz");
// basePage.sendKeyToElement(driver, "//input[@id='Email']", "dfg@sfdg");
// basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
// basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "Wrong email");
// }
//
// @Test
// public void TC_03_Register_Success() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.checkToDefaultCheckBoxRadio(driver, "//input[@id='gender-male']");
// basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "nn");
// basePage.sendKeyToElement(driver, "//input[@id='LastName']", "dz");
// basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
// basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
// basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
//
// basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
// basePage.clickToElement(driver, "//a[@class='ico-logout']");
// }
//
// @Test
// public void TC_04_Register_Existing_Email() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.checkToDefaultCheckBoxRadio(driver, "//input[@id='gender-male']");
// basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "nn");
// basePage.sendKeyToElement(driver, "//input[@id='LastName']", "dz");
// basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
// basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
// basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//div[@class='message-error validation-summary-errors']//li"), "The specified email already exists");
// }
//
// @Test
// public void TC_05_Register_Password_Less_Than_6_Chars() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.checkToDefaultCheckBoxRadio(driver, "//input[@id='gender-male']");
// basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "nn");
// basePage.sendKeyToElement(driver, "//input[@id='LastName']", "dz");
// basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
// basePage.sendKeyToElement(driver, "//input[@id='Password']", "123");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6
// characters");
// }
//
// @Test
// public void TC_06_Register_Invalid_Confirm_Password() {
// basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
// basePage.clickToElement(driver, "//a[@class='ico-register']");
//
// basePage.checkToDefaultCheckBoxRadio(driver, "//input[@id='gender-male']");
// basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "nn");
// basePage.sendKeyToElement(driver, "//input[@id='LastName']", "dz");
// basePage.sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
// basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
// basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123123");
//
// basePage.waitForElementClickable(driver, "//button[@id='register-button']");
// basePage.clickToElement(driver, "//button[@id='register-button']");
//
// Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
//
// }
//
// public int getRandomInt() {
// Random rand = new Random();
// return rand.nextInt(99999);
// }
//
// @AfterClass
// public void afterClass() {
// driver.quit();
// }
//
// }
