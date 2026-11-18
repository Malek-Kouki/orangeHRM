package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class TC001_Tester_la_connexion_avec_des_informations_valides extends BaseTest{

	@Test
	public void Tester_la_connexion_avec_des_informations_valides() {
		// Initialisation du rapport
		createTestReport("Chercher un employee");
		
		LoginPage login = new LoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		
		getTest().info("vérifier que le champ nom d'utilisateur s'affiche dans la page d'acceuil");
		boolean userNameIsDisplayResult = login.userNameIsDisplay();
		Assert.assertTrue(userNameIsDisplayResult, "erreur le champ nom utilisateur doit être affichés");
		
		getTest().info("vérifier que le champ mot de passe s'affiche dans la page d'acceuil");
		boolean passwordIsDisplayResult = login.passwordIsDisplay();
		Assert.assertTrue(passwordIsDisplayResult, "erreur le champ mot de passe doit être affichés");
		
		getTest().info("vérifier que le boutton Login s'affiche dans la page d'acceuil");
		boolean boutonLoginIsDisplayResult = login.boutonLoginisDisplay();
		Assert.assertTrue(boutonLoginIsDisplayResult, "erreur le bouton Login doit être affichés");
		
		getTest().info("vérifier que le boutton Login est actif");
		boolean boutonLoginIsEnabledResult = login.loginIsEnabled();
		Assert.assertTrue(boutonLoginIsEnabledResult, "erreur le bouton Login doit être actif");
		
		getTest().info("saisir le nom d'utilisateur");
		String nomUtilisateur = "Admin";
		login.sendUserName(nomUtilisateur);
		
		getTest().info("saisir le mot de passe");
		String motDePasseUtilisateur = "admin123";
		login.sendPassword(motDePasseUtilisateur);
		
		getTest().info("cliquer sur le bouton login");
		login.clickToLogin();
		
		getTest().info("L'utilisateur est redirigé vers la page d'accueil (dashboard).");
		boolean isOnDashboardReusult = dashboard.isOnDashboard();
		Assert.assertTrue(isOnDashboardReusult, "erreur l'utilisateur doit être redirigé vers la page dashboard");
		
		getTest().info("La photo de profil s'affiche en haut de la page à droite");
		boolean isImageTopRightResult = dashboard.isImageTopRight();
		Assert.assertTrue(isImageTopRightResult, "La photo de profil doit etre afficher en haut de la page à droite");
		
		getTest().info("La bare principale s'affiche");
		boolean isBarrePrincipalResult = dashboard.barrePrincipalIsDisplay();
		Assert.assertTrue(isBarrePrincipalResult, "La bare principale doit être afficher");
		
	}
	
}
