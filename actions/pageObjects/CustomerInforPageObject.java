package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.CustomerInforPageUI;

public class CustomerInforPageObject extends BasePage {
	private WebDriver driver;

	public CustomerInforPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInforPageDisplayed() {
		return isElementDisplay(driver, CustomerInforPageUI.CUSTOMER_INFOR_HEADER);
	}
}
