package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddUserPage;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;

public class TC003_Ajouter_un_utilisateur_avec_des_informations extends BaseTest {
	
	@Test
	public void Ajouter_un_utilisateur_avec_des_informations() {
		// Initialisation du rapport
		createTestReport("Ajouter un utilisateur avec des informations.");
		
		LoginPage login = new LoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		AdminPage admin = new AdminPage(driver);
		AddUserPage addUser = new AddUserPage(driver);
		
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
				
		getTest().info("Cliquer sur le menu Admin dans la barre de navigation principale");
		dashboard.clickToAdmin();
		
		getTest().info("Le titre de la page Admin User Management doit être affiché");
		boolean titlePageAdminDisplayResult = admin.titlePageAdminIsDisplay();
		Assert.assertTrue(titlePageAdminDisplayResult, "erreur : Le titre de la page Admin User Management doit être affiché");
		
		getTest().info("Cliquer sur le bouton Add pour ajouter un nouvel utilisateur");
		admin.clickToAdd();
		
		getTest().info("La page Add User s'ouvre");
		boolean isOnAddUserResult = addUser.isAddUserPageDisplayed();
		Assert.assertTrue(isOnAddUserResult, "erreur : La page Add User s'ouvre");
		
		getTest().info("Sélectionner un rôle utilisateur dans le champ User Role");
		addUser.clickToSelectUserRole();
		boolean listboxUserRoleIsDisplayResult = addUser.listBoxUserRoleIsDisplay();
		Assert.assertTrue(listboxUserRoleIsDisplayResult, "erreur : La liste de User Role doit etre affiché");
		addUser.clickToUserRole();
		
		getTest().info("Sélectionner un status Enabled le champ Status");
		addUser.clickToSelectStatus();
		boolean listBoxStatusResult = addUser.listBoxStatusIsDisplay();
		Assert.assertTrue(listBoxStatusResult, "erreur : La liste de Status doit etre affiché");
		addUser.clickToStatus();
		
		getTest().info("Entrer un nom d'utilisateur valide");
		//String usernameValue = "newuser12";
		int randomNum = new Random().nextInt(9000) + 1000;
		String usernameValue = "newuser" + randomNum;
		addUser.sendUsername(usernameValue);
		
		getTest().info("Entrer le nom d'un employé associé dans le champ Employee Name");
		String employeNameValue = "a";
		addUser.sendEmployeName(employeNameValue);
		
		getTest().info("Vérifier que le champ Employee Name propose une liste de suggestions dynamiques lors de la saisie du nom");
		boolean employeNameListResult = addUser.employeNameListDisplay();
		Assert.assertTrue(employeNameListResult, "erreur : Le champ Employee Name doit proposer une liste de suggestions dynamiques");
		
		getTest().info("Sélectionner un employee depuis la liste");
		addUser.selectEmployeName();
		
		getTest().info("Entrer un mot de passe valide dans le champs Password");
		String passwordValue = "Passw0rd@123";
		addUser.sendPassword(passwordValue);
		
		getTest().info("Confirm Password");
		String confirmPasswordValue = "Passw0rd@123";
		addUser.sendConfirmPassword(confirmPasswordValue);
		
		getTest().info("Cliquer sur le bouton Save");
		addUser.clickToSave();
		
		getTest().info("L'utilisateur est ajouté avec succès et un message de confirmation s'affiche");
		boolean successMessageResult = addUser.successMessageDisplay();
		Assert.assertTrue(successMessageResult, "erreur : L'utilisateur doit etre ajouté avec succès et un message de confirmation s'affiche");
		
	}
}
