//Jatin's code

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationAssignment {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		// I want to launch the browser!!! ChromeBrowser
		WebDriver wd = new ChromeDriver(chromeOptions);
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));

		// Visit redbus.in website!!
		wd.get("https://www.redbus.in");

		By sourceButtonLocator = By.xpath("//div[contains(@class,\"srcDestWrapper\") and @role=\"button\"]");
//		WebElement sourceButton=wd.findElement(sourceButtonLocator); 
		WebElement sourceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
		sourceButton.click();

		By searchSuggestionSectionLocator = By.xpath("//div[contains(@class,\"searchSuggestionWrapper\")]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionSectionLocator));
	
		
		WebElement searchTextBoxElement = wd.switchTo().activeElement(); // give me that textbox!!
		searchTextBoxElement.sendKeys("Mumbai");

		By searchCategoryLocator = By.xpath("//div[contains(@class,\"searchCategory\")]");
		List<WebElement> searchList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 2));

		System.out.println(searchList.size());

		WebElement locationSearchResult = searchList.get(0);
		// Chaining Of WebElements
		By locationNameLocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		List<WebElement> locationList = locationSearchResult.findElements(locationNameLocator);
		System.out.println(locationList.size());

		for (WebElement location : locationList) {
			String lName = location.getText();

			if (lName.equalsIgnoreCase("Mumbai")) {
				location.click();
				break;
			}
		}

		// Focus on the To Section!!!

		WebElement toTextBox = wd.switchTo().activeElement();

		toTextBox.sendKeys("Pune");

		By toSearchCategoryLocator = By.xpath("//div[contains(@class,\"searchCategory\")]");
		List<WebElement> toSearchList = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(toSearchCategoryLocator, 2));

		WebElement toLocationCategory = toSearchList.get(0);
		By toLocationNameLocator = By.xpath(".//div[contains(@class,\"listHeader\")]");

		List<WebElement> toLocationList = toLocationCategory.findElements(toLocationNameLocator);
		for (WebElement toLocation : toLocationList) {

			if (toLocation.getText().equalsIgnoreCase("Pune")) {
				toLocation.click();
				break;
			}
		}

	}

}