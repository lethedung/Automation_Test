package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
	private WebDriver driver;

	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void enterToSearchPostTextbox(String postTitle) {
		waitForAllElementVisible(driver, AdminPostSearchPageUI.SEARCH_POSTS_TEXTBOX);
		sendKeyToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_TEXTBOX, postTitle);
	}

	public void clickToSearchPostsButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
	}

	public boolean isPostSearchTableDisplay(String headerID, String cellValue) {
		int columnIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_ID, headerID) + 1;
		return isElementDisplay(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
	}

	public AdminPostAddNewPO clickToRowLinkByHeader(String headerID, String cellValue) {
		int columnIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_ID, headerID) + 1;
		clickToElement(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(columnIndex), cellValue);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}

	public void selectToPostsCheckboxByPostTitle(String editPostTitle) {
		waitForElementClickable(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
		checkToDefaultCheckBoxOrRadio(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
	}

	public void selectTextItemInActionDropdown(String item) {
		waitForAllElementVisible(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
		selecItemInDefaultDropdown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, item);
	}

	public void clickToApplyButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);

	}

	public boolean isMovedToTheTrashMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.MOVED_TO_TRASH_MESSAGE, message);
		return isElementDisplay(driver, AdminPostSearchPageUI.MOVED_TO_TRASH_MESSAGE, message);
	}

	public boolean isNoPostsFoundMessageDisplayed(String message) {
		waitForAllElementVisible(driver, AdminPostSearchPageUI.NO_POSTS_MESSAGE, message);
		return isElementDisplay(driver, AdminPostSearchPageUI.NO_POSTS_MESSAGE, message);
	}

}
