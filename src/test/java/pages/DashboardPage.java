package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
		WebDriver driver;
		
		WebDriverWait wait;
		public DashboardPage(WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		}
		
		public boolean isOnDashboard() {
			String dashboardUrl = driver.getCurrentUrl();
			return dashboardUrl.contains("dashboard");
		}
		
		public final By barrePrincipal = By.className("oxd-navbar-nav");
		public final By photoDeProfil = By.className("oxd-userdropdown-img");
		public final By PIM = By.xpath("//span[normalize-space()='PIM']");
		public final By adminMenu = By.xpath("//span[normalize-space()='Admin']");
		
		public boolean barrePrincipalIsDisplay() {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(barrePrincipal)).isDisplayed();
		}
		
		public boolean isImageTopRight() {
			Point positionPhoto = wait.until(ExpectedConditions.visibilityOfElementLocated(photoDeProfil)).getLocation();	
			System.out.print("la position de l'image est" + positionPhoto);
			
			int x = positionPhoto.getX();
			System.out.print("la valeur de x est" + x);
			
			int y = positionPhoto.getY();
			System.out.print("la valeur de y est" + y);
			
			//Largeur de navigateur
			int browserWindowWidth = driver.manage().window().getSize().getWidth();
			System.out.print("la largeur de fenetre du navigateur est" + browserWindowWidth);
			
			boolean isOnTop = y < 150;
			
			int acceptedRightPosition = (int) (browserWindowWidth * 0.7);
			boolean isOnRight = x > acceptedRightPosition;
			return isOnTop && isOnRight;
		}	
		
		public void clickToPIM() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(PIM)).click();
		}
		
		public void clickToAdmin() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(adminMenu)).click();
		}
}
