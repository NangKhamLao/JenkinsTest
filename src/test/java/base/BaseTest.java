package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import pages.DashboardPage;
import pages.LoginPage;
import utils.WebDriverManager;
import utils.TestListener;

@Listeners// Attach TestNG Listener for reporting
public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.login("Admin", "admin123");
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
