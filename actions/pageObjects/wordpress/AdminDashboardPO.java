package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
	private WebDriver driver;

	public AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostSearchPO clickToPostSearchLink() {
		waitForElementVisible(driver, AdminDashboardPageUI.POST_SEARCH_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_SEARCH_LINK);
		return PageGeneratorManager.getAdminPostSearchPage(driver);
	}
}
