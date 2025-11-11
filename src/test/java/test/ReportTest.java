package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.Test;

import GenericUtility.BaseClassAF;

public class ReportTest extends BaseClassAF{

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

    }
    
    @Test
    public void UploadToFTP() {
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
   	     System.out.println("❌ No Excel file found for today's date: " + today);
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
   	 System.out.println("✅ Found today's file: " + localFilePath);
        String remoteFilePath = "/Bizom/CARGOFL/<Create BITROUTE>/<AF/CPB>/<files>" + latestFile.getName();
        String userId ="CARGOFL.ADMIN";
        String password ="CargoGod@678#";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("10.9.111.212",21);
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
