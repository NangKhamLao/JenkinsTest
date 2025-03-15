package test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PIMPage;
import base.BaseTest;

public class PIMTest extends BaseTest {
   @Test(priority =1 , description = "Confirm add employee in PIM Module")
   public void testaddEmployee() throws InterruptedException {
    	PIMPage pimpage = new PIMPage(driver);
    	pimpage.goToPIMModule();
    	pimpage.addEmployee("Myint", "Myat", "/Users/user/Documents/landmap.png");

    	Assert.assertTrue(pimpage.isImageUpload(), "Image Upload is not successful");
    	Assert.assertTrue(pimpage.isEmployeeAdded(),"Employee is not added successfully");
    }
    
    @Test(priority= 2, dependsOnMethods= "testaddEmployee",description = "edit employee detail")
    public void testeditEmployeeDetail() throws InterruptedException {
    	PIMPage pimpage = new PIMPage(driver);
    	pimpage.setLicenseNo("DL9098048");
    	pimpage.setLicenseExp("2025-01-23");
    	pimpage.setNationality("Greek");
    	pimpage.setMaritalStatus();
    	pimpage.setgender("male");
    	pimpage.setdateofbirth("1990-09-09");
    	pimpage.clickSave();
    	String filepath = "/Users/user/Documents/Software Quality Assurance/Test Plan/Test Plan and strategy.pdf";
    	Thread.sleep(5000);
    	pimpage.addAttachment(filepath);
    	Thread.sleep(5000);
    	pimpage.saveAttchment();
    	
    	Assert.assertTrue(pimpage.comfirmUpload(), "File upload is not successful");
    	
    	Assert.assertTrue(pimpage.comfirmEdit(), "edit employee detail not successful");
    	
    }
    @Test(priority = 3, dependsOnMethods = "testeditEmployeeDetail")
    public void testFileDownload() throws InterruptedException {
    	String downPath = System.getProperty("user.home")+"/Downloads/";
    	WebElement downloadIcon = driver.findElement(By.xpath("//i[@class='oxd-icon bi-download']"));
    	downloadIcon.click();
    	
    	String filename = "Test Plan and strategy.pdf";
    	File downloadedfile = new File(downPath+filename);
    	int waittime = 10;
    	for(int i= 0; i< waittime; i++) {
    		if (downloadedfile.exists() && downloadedfile.length() >0) {
        		System.out.println("Downloaded successfully:"+downloadedfile.getAbsolutePath());
        		break;
        	}
        	Thread.sleep(1000);
    	}
    	Assert.assertTrue(downloadedfile.exists(),"download file not exist");
    	Assert.assertTrue(downloadedfile.length() >0, "download file is empty");
    	
    	
    }
   
}
