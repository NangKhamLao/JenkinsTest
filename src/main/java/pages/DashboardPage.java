package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    // Locators
    private By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard')]");
    private By pimTab = By.linkText("PIM");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verify if the dashboard is displayed
    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboardHeader).isDisplayed();
    }

    // Navigate to the PIM Module
    public void goToPIM() {
        driver.findElement(pimTab).click();
    }
}
