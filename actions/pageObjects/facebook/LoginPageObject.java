package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreatNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREAT_NEW_ACCOUNT_BUTTON);
	}

	public void enterToEmailAdressTextbox(String emailAdress) {
		waitForAllElementVisible(driver, LoginPageUI.EMAIL_ADRESS_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_ADRESS_TEXTBOX, emailAdress);
	}

	public boolean isConfirmEmailAdressTextboxDisplayed() {
		return isElementDisplay(driver, LoginPageUI.CONFIRM_EMAIL_ADRESS_TEXTBOX);
	}

	public void clickCloseIconAtRegisterForm() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_ICON);
	}

	public boolean isConfirmEmailAdressTextboxUndisplayed() {
		waitForElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADRESS_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADRESS_TEXTBOX);
	}
}
