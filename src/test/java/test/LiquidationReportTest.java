package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;

/**
 *@author DivyaPrakashAmar
 */
public class LiquidationReportTest extends BaseClass{

	
	@Test
	public void liquidationReport() throws InterruptedException {
		

	    String user = "PowerBI";
	    String paswrd = "Ganpati#123456";
	    driverUtility.threadWait(10);
	    loginpage.SendkeyToUserName(user);
	    driverUtility.threadWait(10);
	    loginpage.sendkeyToPasswordTextField(paswrd);
	    driverUtility.threadWait(10);
	    loginpage.clickOnLogInButton();
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
        dashboardPage.clickOnLiquidationLog();
        driverUtility.threadWait(10);
        dashboardPage.clickOnDownloadButton();
        driverUtility.threadWait(10);
        loginpage.clickOnLogoutButton();
        
	}
	
	 	
	     @Test (dependsOnMethods = {"liquidationReport"})
	     public void upload(){
	    	// Get workspace path dynamically (works for both local and Jenkins)
	    	 String workspacePath = System.getProperty("user.dir");

	    	 // Local folder where reports are stored (inside workspace)
	    	 String localFolder = workspacePath + File.separator + "Reports";

	    	 // Get today’s date in the same format as file name
	    	 String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	    	 // Create folder object
	    	 File folder = new File(localFolder);

	    	 // Get all files starting with "Liquidation_Log_<today>" and ending with ".xlsx"
	    	 File[] todayFiles = folder.listFiles((dir, name) ->
	    	     name.startsWith("Liquidation_Log_" + today) && name.endsWith(".xlsx")
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
	         String remoteFilePath = "/Powerbi_Analytics/MD_Dashboards/CPB/" + latestFile.getName();
	         String userId ="powerbi.admin";
	         String password ="Pbianalyts@456#";

	         FTPClient ftpClient = new FTPClient();
	         try {
	             ftpClient.connect("10.9.111.212");
	             boolean login = ftpClient.login(userId, password);

	             if (login) {
	                 System.out.println("Connected to FTP server");

	                 ftpClient.enterLocalPassiveMode(); // Passive mode
	                 ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // For Excel file

	                 try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
	                     boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
	                     if (done) {
	                         System.out.println("File uploaded successfully to " + remoteFilePath);
	                     } else {
	                         System.out.println("Failed to upload file.");
	                     }
	                 }

	                 ftpClient.logout();
	             } else {
	                 System.out.println("Failed to login to FTP server.");
	             }

	             ftpClient.disconnect();
	         } catch (IOException ex) {
	             ex.printStackTrace();
	         }
	         
//	         FTPClient ftpClient = new FTPClient();
//		        try {
//		            String host = "10.9.111.212";   // FTP server
//		            int port = 22;               // <-- FTP port (change if needed)
//
//		            ftpClient.connect(host, port);
//
//		            boolean login = ftpClient.login(userId, password);
//
//		            if (login) {
//		                System.out.println("Connected to FTP server");
//
//		                ftpClient.enterLocalPassiveMode(); // Passive mode
//		                ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // For Excel file
//
//		                try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
//		                    boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
//		                    if (done) {
//		                        System.out.println("File uploaded successfully to " + remoteFilePath);
//		                    } else {
//		                        System.out.println("Failed to upload file.");
//		                    }
//		                }
//
//		                ftpClient.logout();
//		            } else {
//		                System.out.println("Failed to login to FTP server.");
//		            }
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        } finally {
//		            try {
//		                if (ftpClient.isConnected()) {
//		                    ftpClient.disconnect();
//		                }
//		            } catch (IOException ex) {
//		                ex.printStackTrace();
//		            }
//		    }
	     }
	 

}
