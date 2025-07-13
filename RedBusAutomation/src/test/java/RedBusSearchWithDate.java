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

public class RedBusSearchWithDate {
	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(chromeOptions);
		
		//searched RedBus 
		driver.get("https://www.redbus.in/");
		
		//WebDriverWait to Synch webElements
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));		
		
		//clicking on From section
		By fromButtonLocator = By.xpath("//div[contains(@class,'srcDestWrapper') and @role='button']");
		WebElement fromButtonElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(fromButtonLocator));
		fromButtonElement.click();
		
		// Checking if Auto Suggestion sections loaded
		By suggestion = By.xpath("//div[contains(@class,\"searchSuggestionWrapper___\")]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
		

		//Once From section is clicked a dynamic text box loads, getting locator  for text box and passing Mumbai
		WebElement fromTextBox = driver.findElement(By.xpath("//input[contains(@class,'inputField___')]"));
		webDriverWait.until(ExpectedConditions.visibilityOf(fromTextBox));
		fromTextBox.sendKeys("Mumbai");

		//After Passing Mumbai waiting for auto-suggestion to load mumbai then clicking auto suggestion
		By sourceAutoSuggestLocator = By.xpath("//div[@class=\"searchSuggestionWrapper___7b5aba\"]//child::div[@class=\"listHeader___40b031\"and text()='Mumbai']");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(sourceAutoSuggestLocator)).click();

		// once auto-suggestion is click Control moves to TO section, verifying if TO section suggestion is loaded
		By toSuggestionBox = By.xpath("//div[contains(@class,'searchCategory___')][1]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(toSuggestionBox));
		
		//getting TO section text box and passing Hyderabad
		By toTextBox = By.id("srcDest");
		WebElement toTextBoxElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(toTextBox));
		toTextBoxElement.sendKeys("Hyderabad");
		
		//Waiting for auto suggestion and clicking Hyderabad
		By destinationAutoSuggestion = By.xpath("//div[@class='listHeader___40b031' and text()='Hyderabad']");
		WebElement destinationElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(destinationAutoSuggestion));
		destinationElement.click();
		
		//Selecting a date from date Picker-next month 15
		By dateButtonBtnLocator = By.xpath("//div[contains(@class,'dateInputWrapper___')]");
		WebElement dateButtonElement =  webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(dateButtonBtnLocator));
		dateButtonElement.click();
		
		By dataPickerLocator = By.xpath("//div[contains(@class,'datepicker___')]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(dataPickerLocator));
		
		//date picker - moving to next month
		By datePickerRightLocator=By.xpath("//i[contains(@class,'icon icon-arrow arrow___4df7ff right___90a032 ')]");
		WebElement datePickerRightElement =webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(datePickerRightLocator));
		datePickerRightElement.click();
		
		//selecting 15 of next month
		By dateLocator = By.xpath("//div[@data-datetype='AVAILABLE']//child::span[text()='15']");
		WebElement dateElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(dateLocator));
		dateElement.click();
		
		//Clicking on Search Button
		By searchBtnLocator = By.xpath("//button[contains(@class,'primaryButton')]");
		WebElement searachBtnElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBtnLocator));
		searachBtnElement.click();
		
		//checking if filter section is loaded in new page
		By filterSectionLocator = By.xpath("//div[@class='filterSection___eac7e2']");
		WebElement filterSection = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(filterSectionLocator));
		System.out.println(filterSection);
		
		//Selecting Primo from filter section
		//By primoFilterButtonLocatr = By.xpath("//i[@class='iconLeft___ff8639 icon icon-onlyShow_21']//following-sibling::div");
		By primoFilterButtonLocatr = By.xpath("//img[@data-src='https://s3.rdbuz.com/Images/reddeal/srptiles/IND/PRIMO_Main.png']");
		WebElement primoFilterButtonElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(primoFilterButtonLocatr));
		primoFilterButtonElement.click();
		
		//Getting number of bus found message
		By noOfBussesFoundLocator = By.xpath("//div[@class='busesFoundText__ind-search-styles-module-scss-PHVGD']");
		WebElement noOfBussesFoundElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(noOfBussesFoundLocator));
		String noOfbussesFound = noOfBussesFoundElement.getText();
		System.out.println(noOfbussesFound);
		
		//Scroll to End of Page Element
		By endOfPageLocator = By.xpath("//span[text()='End of list']");
		WebElement endOfPageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(endOfPageLocator));
		Actions actions = new Actions(driver);
		actions.scrollToElement(endOfPageElement).perform();
		
		//getting available bus names and printing
		By travelersNameLocator= By.xpath("//ul[@class='srpList__ind-search-styles-module-scss-EOdde']//descendant::div[contains(@class,'travelsName___')]");
		List<WebElement>travellersNameWebElement = webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(travelersNameLocator));
		for(WebElement travellerName:travellersNameWebElement) {
			System.out.println(travellerName.getText());
		}
	}

}
