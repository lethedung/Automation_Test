package pageUIs.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//ul[@class='qgrd-pagination-ul']//a[text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "xpath=//ul[@class='qgrd-pagination-ul']//a[text()='%s' and contains(@class,'active')]";

	public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr//td[text()='%s']/preceding-sibling::td";
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input";
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/select";
	public static final String ICON_NAME_BY_ROW_NUMBER = "xpath=//tbody//tr[%s]//button[@title='%s']";

	public static final String LOAD_DATA_BUTTON = "xpath=//button[@id='btnLoad']";

}
