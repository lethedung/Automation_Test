package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class Post_01_Creat_Read_Update_Search extends BaseTest {
	private WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;

	String adminUsename = "naunem";
	String adminPassword = "naunem";
	int randomInt = getRandomInt();
	String postTitle = "Naunem dep trai vcl title " + String.valueOf(randomInt);
	String postBody = "Naunem dep trai vcl body " + String.valueOf(randomInt);
	String author = "Nau Nem";
	String searchPostUrl;
	String adminUrl;
	String endUserUrl;
	String editPostTitle = "Edit Post title " + String.valueOf(randomInt);
	String editPostBody = "Edit Post Body " + String.valueOf(randomInt);
	String currentDay = getCurrentDay();

	@Parameters({ "browser", "urlAdmin", "urlEndUser" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;

		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminLoginPage.enterToUsernameTextbox(adminUsename);

		adminLoginPage.enterToPasswordTextbox(adminPassword);

		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Creat_New_Post() {
		log.info("Register - Step 01: Navigate to 'Register page'");

		adminPostSearchPage = adminDashboardPage.clickToPostSearchLink();

		searchPostUrl = driver.getCurrentUrl();

		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();

		adminPostAddNewPage.enterToPostTitleTextbox(postTitle);

		adminPostAddNewPage.enterToPostBodyTextbox(postBody);

		adminPostAddNewPage.clickToPublishOrUpdateButton();
		adminPostAddNewPage.clickToPrePublishButton();

		verifyTrue(adminPostAddNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post published."));
	}

	@Test
	public void Post_02_Search_And_View_Post() {
		adminPostSearchPage = adminPostAddNewPage.openPostSearchPageUrl(driver, searchPostUrl);

		adminPostSearchPage.enterToSearchPostTextbox(postTitle);

		adminPostSearchPage.clickToSearchPostsButton();

		verifyTrue(adminPostSearchPage.isPostSearchTableDisplay("title", postTitle));

		verifyTrue(adminPostSearchPage.isPostSearchTableDisplay("author", author));

		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostPostedDay(postTitle, currentDay));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle, author));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));

		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);

		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostPostedDay(postTitle, currentDay));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postTitle, author));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
	}

	@Test
	public void Post_03_Edit_Post() {
		adminDashboardPage = userPostDetailPage.openAdminsite(driver, adminUrl);

		adminPostSearchPage = adminDashboardPage.clickToPostSearchLink();

		adminPostAddNewPage = adminPostSearchPage.clickToRowLinkByHeader("title", postTitle);

		adminPostAddNewPage.enterToPostTitleTextbox(editPostTitle);

		adminPostAddNewPage.enterToPostBodyTextbox(editPostBody);

		adminPostAddNewPage.clickToPublishOrUpdateButton();

		verifyTrue(adminPostAddNewPage.isPostPublishedOrUpdatedMessageDisplayed("Post updated."));

		adminPostSearchPage = adminPostAddNewPage.openPostSearchPageUrl(driver, searchPostUrl);

		adminPostSearchPage.enterToSearchPostTextbox(editPostTitle);

		adminPostSearchPage.clickToSearchPostsButton();

		verifyTrue(adminPostSearchPage.isPostSearchTableDisplay("title", editPostTitle));

		verifyTrue(adminPostSearchPage.isPostSearchTableDisplay("author", author));

		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostPostedDay(editPostTitle, currentDay));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(editPostTitle, author));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));

		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);

		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostPostedDay(editPostTitle, currentDay));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(editPostTitle, author));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
	}

	@Test
	public void Post_04_Delete_Post() {
		adminDashboardPage = userPostDetailPage.openAdminsite(driver, adminUrl);

		adminPostSearchPage = adminDashboardPage.clickToPostSearchLink();

		adminPostSearchPage.enterToSearchPostTextbox(editPostTitle);

		adminPostSearchPage.clickToSearchPostsButton();

		adminPostSearchPage.selectToPostsCheckboxByPostTitle(editPostTitle);

		adminPostSearchPage.selectTextItemInActionDropdown("Move to Trash");

		adminPostSearchPage.clickToApplyButton();

		verifyTrue(adminPostSearchPage.isMovedToTheTrashMessageDisplayed("1 post moved to the Trash."));

		adminPostSearchPage.enterToSearchPostTextbox(editPostTitle);

		adminPostSearchPage.clickToSearchPostsButton();

		verifyTrue(adminPostSearchPage.isNoPostsFoundMessageDisplayed("No posts found."));

		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		verifyTrue(userHomePage.isPostInforUndisplayedWithPostTitle(editPostTitle));

		userHomePage.enterToSearchTextbox(editPostTitle);

		userSearchPostPage = userHomePage.clickToSearchButton();

		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
