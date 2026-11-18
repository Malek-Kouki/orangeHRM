package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIM_Page {

	WebDriver driver;
	
	WebDriverWait wait;
	public PIM_Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isPIMdisplayed() {
		String actualPageURL = driver.getCurrentUrl();
		boolean isOnPIM = actualPageURL.contains("pim");
		return isOnPIM;
	}
	
	public final By employeeName = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
	public final By dynamicList = By.xpath("//div[@role='listbox']");
	public final By employeeOption = By.xpath("//div[@role='listbox']//div[@role='option']");
	public final By bouttonSearch = By.xpath("//button[normalize-space()='Search']");
	public final By recordsFound = By.xpath("//span[@class='oxd-text oxd-text--span']");
	private final By firstRow = By.className("oxd-table-row--clickable");
	private final By loadingSpinner = By.className("oxd-table-loader");
	
	public void sendEmployeeName(String employeeNameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).sendKeys(employeeNameValue);
	}
	
	public List<WebElement> isDynamicListDisplayed() {
		return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(dynamicList, 0));
	}
	
	public String selectFirstEmployee() {
	    wait.until(ExpectedConditions.invisibilityOfElementWithText(dynamicList, "Searching...."));
	    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(employeeOption));
	    String selectedName = options.get(0).getText().trim();
	    options.get(0).click();
	    return selectedName;
	}
	
	public void clickToSearch() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouttonSearch)).click();
	}
	
	//Verifier le texte de Record Found
	public String isRecordFound() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound)).isDisplayed();
		String valueOfRecord = driver.findElement(recordsFound).getText();
		return valueOfRecord;
	}
	
	public List<WebElement> getTableResults() {
	    // 1. Optionnel : attendre brièvement que le spinner de chargement apparaisse (s'il existe)
	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingSpinner));
	    } catch (Exception e) {
	        // Le spinner peut disparaître trop vite, on continue
	    }
	    
	    // 2. Attendre que le spinner disparaisse complètement
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
	    
	    // 3. Attendre explicitement que les nouvelles lignes du tableau soient présentes dans le DOM
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(firstRow));
	    
	    return driver.findElements(firstRow);
	}
}
