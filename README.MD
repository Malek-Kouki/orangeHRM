# Automatisation des tests - OrangeHRM

Projet d'automatisation de tests fonctionnels sur l'application web **OrangeHRM**, réalisé avec **Java**, **Selenium WebDriver** et **TestNG** en suivant le design pattern **Page Object Model (POM)**.

## 🛠️ Technologies et Outils
- **Langage :** Java 17
- **Automatisation :** Selenium WebDriver
- **Framework de test :** TestNG
- **Gestionnaire de projet :** Maven
- **Architecture :** Page Object Model (POM)

## 📁 Structure du projet
- `src/test/java/base` : Configuration initiale du navigateur et gestion du cycle de vie des tests (`BaseTest.java`).
- `src/test/java/pages` : Classes représentant les pages de l'application et leurs éléments (`LoginPage`, `DashboardPage`, `AdminPage`, `AddUserPage`, `PIM_Page`).
- `src/test/java/tests` : Classes contenant les scénarios de tests automatisés.

## 🧪 Scénarios de tests automatisés
1. **TC001** : Tester la connexion avec des informations valides.
2. **TC002** : Tester la connexion avec des informations invalides.
3. **TC003** : Ajouter un nouvel utilisateur dans le module Admin.
4. **TC004** : Rechercher un employé dans le module PIM.

## ⚙️ Exécution des tests
1. Cloner le dépôt :
   ```bash
   git clone [https://github.com/Malek-Kouki/orangeHRM.git](https://github.com/Malek-Kouki/orangeHRM.git)