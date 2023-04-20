package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
	private WebDriver driver;

	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
		return isElementDisplay(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInforDisplayedWithPostBody(String postTitle, String postBody) {
		return isElementDisplay(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBody);
	}

	public boolean isPostInforDisplayedWithPostAuthor(String postTitle, String author) {
		return isElementDisplay(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle, author);
	}

	public boolean isPostInforDisplayedWithPostPostedDay(String postTitle, String currentDay) {
		return isElementDisplay(driver, UserPostDetailPageUI.POST_POSTED_DAY_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}

}
