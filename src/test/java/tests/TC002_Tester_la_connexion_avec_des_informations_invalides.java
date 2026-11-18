package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class TC002_Tester_la_connexion_avec_des_informations_invalides extends BaseTest {

	@DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][] {
            // { scénario, username, password, typeErreurAttendue }
            { "Nom d'utilisateur invalide & mot de passe valide", "InvalidUser", "admin123", "Invalid credentials" },
            { "Nom d'utilisateur valide & mot de passe invalide","Admin", "InvalidPass", "Invalid credentials" },
            { "Nom d'utilisateur vide & mot de passe rempli", "", "admin123", "Required" },
            { "Mot de passe vide & nom d'utilisateru rempli", "Admin", "", "Required" }
        };
    }
	
	@Test(dataProvider = "invalidLoginData")
	public void Tester_la_connexion_avec_des_informations_invalides (String scenario, String username, String password, String expectedError) {
		createTestReport("Connexion invalide - " + scenario);
        LoginPage login = new LoginPage(driver);

        getTest().info("Saisir le nom d'utilisateur : '" + username + "'");
        login.sendUserName(username);

        getTest().info("Saisir le mot de passe : '" + password + "'");
        login.sendPassword(password);

        getTest().info("Cliquer sur le bouton Login");
        login.clickToLogin();

        getTest().info("Vérifier l'affichage du message d'erreur : " + expectedError);
        if (expectedError.equals("Invalid credentials")) {
            Assert.assertTrue(login.errorMessageIsDisplay(),
                "Le message 'Invalid credentials' ne s'est pas affiché pour le cas : " + scenario);
        } else {
            Assert.assertTrue(login.errorMessageRequiredIsDisplay(),
                "Le message 'Required' ne s'est pas affiché pour le cas : " + scenario);
        }
		
	}
}
