package test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import GenericUtility.BaseClass_Liqiudation;

/**
 *@author DivyaPrakashAmar
 */
public class OverAllLiquidationReportTest extends BaseClass_Liqiudation{

	
	@Test
	public void liquidationReport() throws InterruptedException {
		
		 WebElement ele=driver.findElement(By.id("i0116"));
	    WebElement nxtbutton=driver.findElement(By.id("idSIButton9"));   
	    ele.sendKeys("testing.engineer@godrejagrovet.com");
	    nxtbutton.click();
	    driverUtility.threadWait(10);
	    dashboardPage.sendkeyToPasswordTextfield("Gavlagrovet@1295");
	    dashboardPage.clickOnsignInButton();
	    driverUtility.threadWait(10);
	    dashboardPage.clickOnsignInButton();
		
	    driverUtility.threadWait(10);
		dashboardPage.clickOnScanTab();
		driverUtility.threadWait(10);
		dashboardPage.clickOnLiquidationTab();
		driverUtility.threadWait(10);
		dashboardPage.clickOnFilterIcon();
		driverUtility.threadWait(10);
		dashboardPage.clickOnCustomDateRange();
		dashboardPage.clickOnFromDate();
		driverUtility.threadWait(10);
		dashboardPage.clickOnToDateButton();
        dashboardPage.clickOnToDate();
        driverUtility.threadWait(10);
        dashboardPage.clickOnApplyButton();
        driverUtility.threadWait(10);
        dashboardPage.clickOnDownloadTab();
        driverUtility.threadWait(10);
        dashboardPage.clickOnOverallLiquidationTab();
        driverUtility.threadWait(10);
        dashboardPage.clickOnDownloadButton();
        driverUtility.threadWait(10);
        
	}
	
	 	
	     @Test (dependsOnMethods = {"OverAllLiquidationReportTest"})
	     public void uploadOverAllLiquidation(){
	    	// Get workspace path dynamically (works for both local and Jenkins)
	    	 String workspacePath = System.getProperty("user.dir");

	    	 // Local folder where reports are stored (inside workspace)
	    	 String localFolder = workspacePath + File.separator + "OverAllLiquidation_Reports";

	    	 // Get today’s date in the same format as file name
	    	 String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	    	 // Create folder object
	    	 File folder = new File(localFolder);

	    	 // Get all files starting with "Liquidation_Log_<today>" and ending with ".xlsx"
	    	 File[] todayFiles = folder.listFiles((dir, name) ->
	    	     name.startsWith("Overall_Liquidation" + today) && name.endsWith(".xlsx")
	    	 );

	    	 // Check if files found
	    	 if (todayFiles == null || todayFiles.length == 0) {
	    	     System.out.println("No Excel file found for today's date: " + today);
	    	     System.out.println("Checked folder: " + localFolder);
	    	     return;
	    	 }

	    	 // In case multiple files generated today, pick the most recent
	    	 File latestFile = todayFiles[0];
	    	 for (File f : todayFiles) {
	    	     if (f.lastModified() > latestFile.lastModified()) {
	    	         latestFile = f;
	    	     }
	    	 }

	    	 // Latest file path
	    	 String localFilePath = latestFile.getAbsolutePath();
	    	 System.out.println(" Found today's file: " + localFilePath);
	         String remoteFilePath = "/Powerbi_Analytics/CPB/Sankalp/Overall Liquidation";
	         String userId ="powerbi.admin";
	         String password ="Pbianalyts@456#";
	         String host ="10.9.111.212";

	         
	        Session session = null;
	 		ChannelSftp channel = null;
	 		try {
	             JSch jsch = new JSch();
	             session = jsch.getSession(userId, host, 22);
	             session.setPassword(password);

	             Properties config = new Properties();
	             config.put("StrictHostKeyChecking", "no");
	             session.setConfig(config);

	             session.connect(90000); // 90 sec timeout

	             channel = (ChannelSftp) session.openChannel("sftp");
	             channel.connect(90000);

	             channel.cd(remoteFilePath);
	             channel.put(localFilePath, latestFile.getName());

	             System.out.println("SFTP upload successful");

	         } catch (Exception e) {
	             throw new RuntimeException("SFTP upload failed", e);
	         } finally {
	             if (channel != null) channel.disconnect();
	             if (session != null) session.disconnect();
	         }  
	         
	     }
	 

}

