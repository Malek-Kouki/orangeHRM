package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIM_Page;

public class TC004_Chercher_un_employe extends BaseTest {

	@Test
	public void Chercher_un_employe() {
				
		LoginPage login = new LoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		PIM_Page PIM = new PIM_Page(driver);
		
		createTestReport("Chercher un employee");

		// Prérequis: TC-001
		getTest().info("saisir le nom d'utilisateur");
		String nomUtilisateur = "Admin";
		login.sendUserName(nomUtilisateur);
		
		// Prérequis: TC-001
		getTest().info("saisir le mot de passe");
		String motDePasseUtilisateur = "admin123";
		login.sendPassword(motDePasseUtilisateur);
		
		// Prérequis: TC-001
		getTest().info("cliquer sur le bouton login");
		login.clickToLogin();
		
		getTest().info("cliquer sur le bouton PIM");
		dashboard.clickToPIM();;
		
		getTest().info("verifier que la page Employee Information s'affiche");
		boolean PIMdisplayedResult = PIM.isPIMdisplayed();
		Assert.assertTrue(PIMdisplayedResult, "erreur La page Employee Information doit être affichés");
		
		getTest().info("saisir un nom dans Employee Name");
		String employeeName = "a";
		PIM.sendEmployeeName(employeeName);
		
		getTest().info("Vérifier que le champ Employee Name propose une liste de suggestions dynamiques lors de la saisie partielle du nom.");
		List <WebElement> listSuggestion = PIM.isDynamicListDisplayed();
		Assert.assertNotNull(listSuggestion, "la liste dynamic ne doit pas être vide");
		Assert.assertTrue(listSuggestion.size()>0, "la liste de suggestion doit contenir au moins un employe");
		
		getTest().info("Sélectionner le premier employé depuis la liste");
		String expectedEmployeeName = PIM.selectFirstEmployee();
		
		getTest().info("Cliquer sur Search");
		PIM.clickToSearch();
		
		getTest().info("afficher les informations de l'employé");
		List<WebElement> employeInformation = PIM.getTableResults();
		StringBuilder userInfo = new StringBuilder();
		for (WebElement row : employeInformation) {
			userInfo.append(row.getText()).append(" ");
		}
		getTest().info("les informations de l'employé sont : " + userInfo.toString());
		
		getTest().info("verifier que le tableau affiche l'employé cherché");
		String[] nameParts = expectedEmployeeName.split("\\s+");
		for (String part : nameParts) {
			Assert.assertTrue(userInfo.toString().contains(part), 
				"La partie '" + part + "' de l'employé '" + expectedEmployeeName + "' n'a pas été trouvée dans le résultat");
		}
	}
}
