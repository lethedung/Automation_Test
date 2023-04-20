package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
	private WebDriver driver;

	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToPostTitleTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitle);
	}

	public void enterToPostBodyTextbox(String postBody) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sleepInSecond(1);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clearValueInElementByDeleteKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
	}

	public void clickToPublishOrUpdateButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
	}

	public void clickToPrePublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.RE_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.RE_PUBLISH_BUTTON);
	}

	public boolean isPostPublishedOrUpdatedMessageDisplayed(String postPublishedMessage) {
		return isElementDisplay(driver, AdminPostAddNewPageUI.POST_PUBLISHED_MESSAGE, postPublishedMessage);
	}
}
