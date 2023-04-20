package pageUIs.wordpress;

public class AdminPostSearchPageUI {

	public static final String ADD_NEW_BUTTON = "css=a.page-title-action";
	public static final String SEARCH_POSTS_TEXTBOX = "css=input#post-search-input";
	public static final String SEARCH_POSTS_BUTTON = "css=input#search-submit";
	public static final String TABLE_HEADER_INDEX_BY_HEADER_ID = "xpath=//table[contains(@class,'table-view-list post')]/thead/tr/*[@id='%s']/preceding-sibling::*";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class,'table-view-list post')]/tbody/tr/*[%s]//a[text()='%s']";
	public static final String POST_TITLE = "xpath=//table[contains(@class,'table-view-list post')]/tbody/tr/*[%s]//a[text()='%s']";
	public static final String ROW_CHECKBOX_BY_TITLE_NAME = "xpath=//table[contains(@class,'table-view-list post')]/tbody/tr//label[contains(text(),'%s')]/following-sibling::input";
	public static final String ACTION_DROPDOWN = "css=select#bulk-action-selector-top";
	public static final String APPLY_BUTTON = "css=input#doaction";
	public static final String MOVED_TO_TRASH_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'1 post moved to the Trash.')]";
	public static final String NO_POSTS_MESSAGE = "xpath=//table[contains(@class,'table-view-list post')]//tr[@class='no-items']//td[text()='%s']";
}
