package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.Test;

import GenericUtility.BaseClassAF;

public class AF_Report extends BaseClassAF{

    @Test
    public void reportGeneration() throws InterruptedException {
        String user = "ajit.sahu@godrejagrovet.com";
        String paswrd = "Ganpati@123456";

        loginPage_AF.sendkeyToUserNameTextField(user);
        loginPage_AF.clickOnProceedButton();
        loginPage_AF.sendkeyToPasswordTextField(paswrd);
        loginPage_AF.clickOnLoginButton();
        driverUtility.threadWait(2);
        teamActivityPage.errorMessageDisplay();
        driverUtility.threadWait(2);
        teamActivityPage.clickOnChangedate();
        driverUtility.threadWait(2);
        teamActivityPage.clickOnFromDate();
        driverUtility.threadWait(2);
        teamActivityPage.clickOnToDate();
        driverUtility.threadWait(2);
        teamActivityPage.clickOnactivityLogTab();
        driverUtility.threadWait(2);
        teamActivityPage.clickOngenerateFileButton();
        driverUtility.allowAlertPopUp();
        teamActivityPage.clickOnactivityLogTab();
        teamActivityPage.downloadFile();
        loginPage_AF.clickOnDropDownButton();
        driverUtility.threadWait(2);
        loginPage_AF.clickOnLogoutButton();
        

    }
    
    @Test (dependsOnMethods = {"reportGeneration"})
    public void UploadBR_ReportToFTP() {
    	// Get workspace path dynamically (works for both local and Jenkins)
    	String workspacePath = System.getProperty("user.dir");

    	// Local folder where reports are stored (inside workspace)
    	String localFolder = workspacePath + File.separator + "Reports_AF";

    	// Get today’s date in the same format as file name
    	String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    	// Create folder object
    	File folder = new File(localFolder);

    	// Step 1: Get all .tmp files (downloaded by driver)
    	File[] tmpFiles = folder.listFiles((dir, name) -> name.endsWith(".csv"));

    	// Step 2: Check if any .tmp files exist
    	if (tmpFiles == null || tmpFiles.length == 0) {
    	    System.out.println("❌ No .tmp file found in folder: " + localFolder);
    	    return;
    	}

    	// Step 3: Pick the most recent .tmp file
    	File latestTmpFile = tmpFiles[0];
    	for (File f : tmpFiles) {
    	    if (f.lastModified() > latestTmpFile.lastModified()) {
    	        latestTmpFile = f;
    	    }
    	}

    	System.out.println("📄 Found latest .tmp file: " + latestTmpFile.getName());

    	// Step 4: Define the final CSV file name
    	String newFileName = "Beatroute_AF_" + today + ".csv";
    	File renamedFile = new File(folder, newFileName);

    	// Step 5: Wait until file is fully downloaded (optional but safer)
    	long previousSize = 0;
    	long currentSize = latestTmpFile.length();
    	int stableCount = 0;

    	System.out.println("⏳ Waiting for download to complete...");
    	while (stableCount < 3) { // Check stability 3 times in a row
    	    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    	    previousSize = currentSize;
    	    currentSize = latestTmpFile.length();
    	    if (currentSize == previousSize) stableCount++;
    	    else stableCount = 0;
    	}
    	System.out.println("✅ Download seems complete.");

    	// Step 6: Rename file to CSV format
    	boolean renamed = latestTmpFile.renameTo(renamedFile);

    	if (renamed) {
    	    System.out.println("✅ File renamed successfully to: " + renamedFile.getAbsolutePath());
    	} else {
    	    System.out.println("❌ Failed to rename file: " + latestTmpFile.getName());
    	    return;
    	}

    	// Step 7: Proceed with FTP upload using renamedFile
    	// (Example placeholder)
    	System.out.println("📤 Ready to upload: " + renamedFile.getName());



   	 // Latest file path
   	 String localFilePath = renamedFile.getAbsolutePath();
   	 System.out.println("✅ Found today's file: " + localFilePath);
        String remoteFilePath = "/Powerbi_Analytics/Beatroute/Animal Feed/Activity Log/" + renamedFile.getName();
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
    
    }
}
