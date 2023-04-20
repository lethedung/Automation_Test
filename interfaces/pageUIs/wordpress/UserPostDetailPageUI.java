package pageUIs.wordpress;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']";
	public static final String POST_POSTED_DAY_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']//ancestor::header//following-sibling::div[@class='entry-meta']//span[@class='posted-on']//time[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']//ancestor::header//span[@class='author vcard']//a[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']//ancestor::header//following-sibling::div[@class='entry-content']//p[text()='%s']";
}
