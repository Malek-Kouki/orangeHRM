package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	
	WebDriverWait wait;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By nomUtilisateur = By.name("username");
	private final By motDePasseUtilisateur = By.name("password");
	private final By bouttonLogin = By.className("oxd-button");
	private final By errorMessage = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");
	private final By errorMessageRequired = By.className("oxd-input-group__message");

	public boolean userNameIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(nomUtilisateur)).isDisplayed();
	}
	
	public boolean passwordIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(motDePasseUtilisateur)).isDisplayed();
	}
	
	public boolean boutonLoginisDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(bouttonLogin)).isDisplayed();
	}
	
	public boolean loginIsEnabled() {
		return driver.findElement(bouttonLogin).isEnabled();
	}
	
	public void sendUserName(String userNameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nomUtilisateur)).sendKeys(userNameValue);
	}
	
	public void sendPassword(String passwordValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(motDePasseUtilisateur)).sendKeys(passwordValue);
	}
	
	public void clickToLogin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouttonLogin)).click();
	}
	
	public boolean errorMessageIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
	}
	
	public boolean errorMessageRequiredIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageRequired)).isDisplayed();
	}
}
