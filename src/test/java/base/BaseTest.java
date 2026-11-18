package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
    // Chaque thread parallèle a son propre WebDriver
    protected WebDriver driver;
    
    // Un seul rapport HTML global pour toute la suite
    protected static ExtentReports extent;
    
    // ThreadLocal isole chaque cas de test dans son propre thread (évite que les logs se mélangent)
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    protected final String baseUrl =
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String reportDir =
            System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "custom-report";

    // Méthode à appeler au début de chaque @Test pour créer l'entrée dans le rapport
    protected void createTestReport(String testName) {
        ExtentTest testInstance = extent.createTest(testName);
        extentTest.set(testInstance);
    }

    // Méthode à appeler dans vos tests à la place de "test.info(...)"
    protected ExtentTest getTest() {
        return extentTest.get();
    }

    @BeforeSuite
    public void beforeSuite() {
        new File(reportDir).mkdirs();
        // Nom de fichier fixe pour éviter de multiplier les fichiers avec des horodatages
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                reportDir + File.separator + "ExtentReport.html"
        );
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                currentTest.fail("FAILED: " + result.getName());
                currentTest.fail(result.getThrowable());

                String path = takeScreenshot(result.getName());
                currentTest.addScreenCaptureFromPath(path);
            } else if (result.getStatus() == ITestResult.SKIP) {
                currentTest.skip("SKIPPED: " + result.getName());
            } else {
                currentTest.pass("PASSED: " + result.getName());
            }
        }

        if (driver != null) {
            driver.quit();
        }
        // Nettoie la mémoire du thread courant
        extentTest.remove();
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    private String takeScreenshot(String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String destination = reportDir + File.separator + screenshotName + "_" + dateName + ".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }
}