package pages;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
    private WebDriver driver;
    WebDriverWait wait;

    // Locators for add employee
    private By pimTab = By.linkText("PIM");
    private By addEmployeeButton = By.xpath("//button[normalize-space()='Add']");
    private By empID = By.xpath("//label[text()='Employee Id']/following::input");
    private By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private By imgSrc = By.xpath("//input[@type='file']");
    private By saveButton = By.xpath("//button[@type='submit' and text()=' Save ']");
    private By defaultimg = By.className("employee-image");
    
    //Locators for edit employee detail 
    private By driverLN = By.xpath("//form/div[2]/div[2]/div[1]/div/div[2]/input");
    private By licenseExp = By.xpath("//label[text() = 'License Expiry Date']/following::input");
    private By nationality = By.xpath("//label[text()='Nationality']/following::div");
    private By maritalStatus = By.xpath("//label[text()='Marital Status']/following::div");
    private By dob = By.xpath("//label[text()='Date of Birth']/following::input");
    private By genderMale = By.xpath("//label[normalize-space()='Male']");
    private By genderFemale = By.xpath("//label[normalize-space()='Female']");
    private By saveBtn = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']");
    
    //locators for custom field 
    private By bloodtype = By.xpath("//label[text()='Blood Type']/following::div");
    private By cusSaveBtn = By.xpath("//div[@class='orangehrm-custom-fields']//button[@type='submit'][normalize-space()='Save']");
    
    //locators for attachment
    private By addbtn = By.xpath("//button[normalize-space()='Add']");
    private By fileInput = By.xpath("//input[@type='file']");
    private By attSave = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/form/div[3]/button[2]");

    // Constructor
    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to PIM module
    public void goToPIMModule() {
        driver.findElement(pimTab).click();
    }

    // Add Employee
    public void addEmployee(String firstName, String lastName, String img) {
    	
        driver.findElement(addEmployeeButton).click();
     	WebElement empIdfield = driver.findElement(empID);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(imgSrc).sendKeys(img);
        driver.findElement(saveButton).click();
        empIdfield.clear();
        // Define explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
        	empIdfield.clear();
        	 WebElement errMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Employee Id already exists']")));
             if (errMsg.isDisplayed()) {
             	System.out.println("Employee ID already exists. Enter Manually: ");
             	
//             	Scanner scanner = new Scanner(System.in);
//             	String manualInput = scanner.nextLine();
//             	
             	String manualInput = JOptionPane.showInputDialog("Enter Employee ID Manually:");
             	
             	empIdfield.sendKeys("");
             	empIdfield.sendKeys(manualInput);
             	
             	driver.findElement(saveButton).click();
             }
        }catch(Exception e) {
        	System.out.println("No ID conflicted!");
        }
       
       
    }
   
    // Validate Employee Creation
    public boolean isEmployeeAdded() throws InterruptedException {
    	Thread.sleep(8000);
    	String expUrl = driver.getCurrentUrl();
        return expUrl.contains("empNumber");
    }
    //validate image upload 
    public boolean isImageUpload() {
    	@SuppressWarnings("deprecation")
		String defaultsrc = driver.findElement(defaultimg).getAttribute("src");
		return defaultsrc != "/web/images/default-photo.png";
    	
    }
    
    public void setLicenseNo(String licenseNo) {
    	WebElement driverLicense = driver.findElement(driverLN);
    	driverLicense.clear();
    	driverLicense.sendKeys(licenseNo);
    	
    }
    public void setLicenseExp(String licExp) {
    	WebElement exp = driver.findElement(licenseExp);
    	exp.clear();
    	exp.sendKeys(licExp);
    	
    }
    
    public void setNationality(String national) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));

    	driver.findElement(nationality).click();
//        List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']"));
//    	for (WebElement option: options) {
//    		if(option.getText().trim().equalsIgnoreCase(national)) {
//    			Thread.sleep(10);
//    			option.click();
//    			break;
//    		}
//    	}
    	WebElement option_path = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='"+national+"']")));
    	option_path.click();
    }
    
    public void setMaritalStatus() {
    	driver.findElement(maritalStatus).click();
    	WebElement maritalStatusInput = driver.switchTo().activeElement();
    	maritalStatusInput.sendKeys(Keys.ARROW_DOWN);
    	maritalStatusInput.sendKeys(Keys.ARROW_DOWN);
    	maritalStatusInput.sendKeys(Keys.ENTER);

    	
    }
    public void setdateofbirth (String dateBirth) {
    	driver.findElement(dob).clear();
    	driver.findElement(dob).sendKeys(dateBirth);
    }
    
    public void setgender(String gender) {
    	if(gender.equalsIgnoreCase("male")){
    		driver.findElement(genderMale).click();
    	}
    	else {
    		driver.findElement(genderFemale).click();
    	}
    }
    
    public void clickSave() {
    	driver.findElement(saveBtn).click();
    }
    
    public void addAttachment(String filepath) throws InterruptedException {
    	driver.findElement(addbtn).click();
    	driver.findElement(fileInput).sendKeys(filepath);
    	
    }
    public void saveAttchment() {
    	driver.findElement(attSave).click();
    }
    
    public boolean comfirmUpload() {
    	WebElement label = driver.findElement(By.xpath("//div[contains(text(),'Test Plan and strategy.pdf')]"));
		return label.isDisplayed();
    	
    }
    public boolean comfirmEdit() {
    	WebElement successmsg = driver.findElement(By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']"));
		return successmsg.isDisplayed();
    }
    
    
}
