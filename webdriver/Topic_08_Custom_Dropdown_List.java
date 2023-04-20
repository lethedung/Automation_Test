package webdriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		driver = new ChromeDriver();
		// Driver ID
		
		// khi tìm đc element rồi, wait tìm trạng thái
		explicitWait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		// Wait để tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");
		By child = By.cssSelector("ul#number-menu div");

		selectItemInDropdown(parent, child, "19");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19'] ")));

		selectItemInDropdown(parent, child, "5");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5'] ")));
	}
	
	@Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parent = By.xpath("//div[@class='ui fluid selection dropdown']");
		By child = By.xpath("//div[@class='visible menu transition']//span");

		selectItemInDropdown(parent, child, "Matt");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//div[@class='divider text' and text()= 'Matt']")));
		
		selectItemInDropdown(parent, child, "Christian");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//div[@class='divider text' and text()= 'Christian']")));
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		By parent = By.cssSelector("li.dropdown-toggle");
		By child = By.cssSelector("ul.dropdown-menu a");

		selectItemInDropdown(parent, child, "Third Option");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//li[@class='dropdown-toggle' and contains(text(), 'Third Option')]")));
		
		selectItemInDropdown(parent, child, "First Option");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//li[@class='dropdown-toggle' and contains(text(), 'First Option')]")));
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getRandomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed())
			return true;
		else
			return false;
	}
	
	public void selectItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
		driver.findElement(parentBy).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		List<WebElement> allItems = driver.findElements(childBy);
		System.out.println("All item= " + allItems.size());
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
			}
		}
	}
}

