import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusSearch {
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.redbus.in/");
		By fromButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and @role='button']");
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fromButtonElement = webDriverWait
				.until(ExpectedConditions.visibilityOfElementLocated(fromButtonLocator));
		fromButtonElement.click();
		// clicked on from button

		By suggestion = By.xpath("//div[contains(@class,\"searchSuggestionWrapper___\")]");
		WebElement suggestionElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
		// waiting for auto suggestion section to load,and fromtext box to appear

		// getting text box locator and passing mumbai
		WebElement fromTextBox = driver.findElement(By.xpath("//input[contains(@class,'inputField___')]"));
		webDriverWait.until(ExpectedConditions.visibilityOf(fromTextBox));
		fromTextBox.sendKeys("Mumbai");

		// once mumbai is passed waiting for nalsopara element to apperar then clicking
		// on it
		By optionNalaSuparah = By.xpath(
				"//div[@class=\"searchSuggestionWrapper___7b5aba\"]//child::div[@class=\"listHeader___40b031\"and text()='Mumbai']");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(optionNalaSuparah)).click();

		// control goes to TO, spo checking if auto suggestion box is loaded before
		// looking for to check box
		By toSuggestionBox = By.xpath("//div[contains(@class,'searchCategory___')][1]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(toSuggestionBox));

		By toTextBox = By.id("srcDest");
		WebElement toTextBoxElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(toTextBox));
		toTextBoxElement.sendKeys("Hyderabad");
		

		By kukatpallyLocator = By.xpath("//div[@class='listHeader___40b031' and text()='Hyderabad']");
		WebElement kukatpallyElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(kukatpallyLocator));
		kukatpallyElement.click();
		
		By searchBtnLocator = By.xpath("//button[contains(@class,'primaryButton')]");
		WebElement searachBtnElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBtnLocator));
		searachBtnElement.click();
		
		//checking if filter section is loaded in new page
		By filterSectionLocator = By.xpath("//div[@class='filterSection___eac7e2']");
		WebElement filterSection = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(filterSectionLocator));
		System.out.println(filterSection);
		
		//select 
		//By primoFilterButtonLocatr = By.xpath("//i[@class='iconLeft___ff8639 icon icon-onlyShow_21']//following-sibling::div");
		By primoFilterButtonLocatr = By.xpath("//img[@data-src='https://s3.rdbuz.com/Images/reddeal/srptiles/IND/PRIMO_Main.png']");
		WebElement primoFilterButtonElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(primoFilterButtonLocatr));
		primoFilterButtonElement.click();
		
		By noOfBussesFoundLocator = By.xpath("//div[@class='busesFoundText__ind-search-styles-module-scss-PHVGD']");
		WebElement noOfBussesFoundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(noOfBussesFoundLocator));
		String noOfbussesFound = noOfBussesFoundElement.getText();
		System.out.println(noOfbussesFound);
		//Scroll to End of Page Element
		By endOfPageLocator = By.xpath("//span[text()='End of list']");
		WebElement endOfPageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(endOfPageLocator));
		Actions actions = new Actions(driver);
		actions.scrollToElement(endOfPageElement).perform();
		
		//getting travellers names
		By travelersNameLocator= By.xpath("//ul[@class='srpList__ind-search-styles-module-scss-EOdde']//descendant::div[contains(@class,'travelsName___')]");
		List<WebElement>travellersNameWebElement = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(travelersNameLocator));
		for(WebElement travellerName:travellersNameWebElement) {
			System.out.println(travellerName.getText());
		}
	}

}
