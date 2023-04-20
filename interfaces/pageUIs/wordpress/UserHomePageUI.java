package pageUIs.wordpress;

public class UserHomePageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//a[text()='%s']";
	public static final String POST_POSTED_DAY_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']//ancestor::header//following-sibling::div[@class='entry-meta']//span[@class='posted-on']//time[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']//ancestor::header//span[@class='author vcard']//a[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//a[text()='%s']//ancestor::header//following-sibling::div[@class='entry-content']//p[text()='%s']";
	public static final String SEARCH_POST_TEXTBOX = "css=input.wp-block-search__input";
	public static final String SEARCH_POST_BUTTON = "css=button.wp-block-search__button";
}
