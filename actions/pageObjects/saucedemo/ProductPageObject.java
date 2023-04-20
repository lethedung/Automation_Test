package pageObjects.saucedemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucedemo.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemTextInProductSortDropdown(String itemText) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN);
		selecItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN, itemText);
	}

	public boolean isProductNameSortedBySortType(String sortType) {
		List<String> productUIs = new ArrayList<String>();
		List<String> productSorted = new ArrayList<String>();

		List<WebElement> productNamesText = new ArrayList<WebElement>();

		productNamesText = getListWebElement(driver, ProductPageUI.ITEM_NAME);

		for (WebElement webElement : productNamesText) {
			productUIs.add(webElement.getText());
		}

		for (String product : productUIs) {
			productSorted.add(product);
		}

		Collections.sort(productSorted);
		if (sortType == "DESC")
			Collections.reverse(productSorted);

		return productSorted.equals(productUIs);
	}

	public boolean isProductNameSortedBySortTypeLambda(String sortType) {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.ITEM_NAME);
		List<String> names = elementLists.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		if (sortType == "DESC")
			Collections.reverse(sortedNames);

		return names.equals(sortedNames);
	}
}
