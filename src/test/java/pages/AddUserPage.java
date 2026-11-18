package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPage {

WebDriver driver;
	
	WebDriverWait wait;
	public AddUserPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isAddUserPageDisplayed() {
		String actualPageURL = driver.getCurrentUrl();
		boolean isOnAddUser = actualPageURL.contains("saveSystemUser");
		return isOnAddUser;
	}
	
	public final By selectUserRole = By.xpath("(//div[@class='oxd-select-wrapper'])[1]");
	public final By listBoxUserRole = By.className("oxd-select-dropdown"); 
	public final By userRoleESS = By.xpath("//span[normalize-space()='ESS']");
	public final By selectStatus = By.xpath("(//div[@class='oxd-select-wrapper'])[2]");
	public final By listBoxStatus = By.xpath("//div[@role='listbox']");
	public final By statusEnabled = By.xpath("//span[normalize-space()='Enabled']");
	public final By username = By.xpath("//label[text()='Username']/following::input[1]");
	public final By employeName = By.cssSelector("input[placeholder='Type for hints...']");
	public final By employeNameList = By.cssSelector("div[role='listbox']");
	public final By employeNameRow = By.xpath("//div[@role='listbox']//div[@role='option'][1]//span");
	public final By password = By.xpath("//label[text()='Password']/following::input[1]");
	public final By confirmPassword = By.xpath("//label[text()='Confirm Password']/following::input[1]");
	public final By saveBoutton = By.xpath("//button[normalize-space()='Save']");
	public final By successMesaage = By.cssSelector(".oxd-toast--success");
	
	public void clickToSelectUserRole() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectUserRole)).click();
	}
	
	public boolean listBoxUserRoleIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(listBoxUserRole)).isDisplayed();
	}
	
	public void clickToUserRole() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userRoleESS)).click();
	}
	
	public void clickToSelectStatus() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectStatus)).click();
	}
	
	public boolean listBoxStatusIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(listBoxStatus)).isDisplayed();
	}
	
	public void clickToStatus() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(statusEnabled)).click();
	}
	
	public void sendUsername(String UsernameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(UsernameValue);
	}
	
	public void sendEmployeName(String employeNameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeName)).sendKeys(employeNameValue);
	}
	
	public boolean employeNameListDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeNameList)).isDisplayed();
	}
	
	public void selectEmployeName() {
		wait.until(ExpectedConditions.invisibilityOfElementWithText(employeNameList, "Searching...."));
	    wait.until(ExpectedConditions.elementToBeClickable(employeNameRow)).click();
	}
	
	public void sendPassword(String passwordValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(passwordValue);
	}
	
	public void sendConfirmPassword(String confirmPasswordValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(confirmPasswordValue);
	}
	
	public void clickToSave() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(saveBoutton)).click();
	}
	
	public boolean successMessageDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successMesaage)).isDisplayed();
	}
	
	
}
