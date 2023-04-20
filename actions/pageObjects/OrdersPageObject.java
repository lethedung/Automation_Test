package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.OrdersPageUI;

public class OrdersPageObject extends BasePage {
	private WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrdersPageDisplay() {
		return isElementDisplay(driver, OrdersPageUI.ORDERS_HEADER);
	}
}
