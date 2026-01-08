package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.testng.annotations.Test;

import GenericUtility.BaseClassAFUserList;

public class AF_UserListReportTest extends BaseClassAFUserList{

	@Test
	public void AF_userListReport() {
		String user = "ajit.sahu@godrejagrovet.com";
        String paswrd = "Ganpati@123456";
		
		driverUtility.implicitlyWait(10);
		loginPage_AF.sendkeyToUserNameTextField(user);
		  driverUtility.threadWait(10);
        loginPage_AF.clickOnProceedButton();
        driverUtility.threadWait(10);
        loginPage_AF.sendkeyToPasswordTextField(paswrd);
        driverUtility.threadWait(10);
        loginPage_AF.clickOnLoginButton();
        driverUtility.threadWait(10);
        teamActivityPage.clickOnUserTab();
        driverUtility.threadWait(10);
        teamActivityPage.clickOnUserDownload();
        driverUtility.threadWait(10);
        driverUtility.allowAlertPopUp();
	}
	
	@Test (dependsOnMethods = {"AF_userListReport"})
	public void uploadToFTP_AF_User() {
		// Get workspace path dynamically (works for both local and Jenkins)
    	String workspacePath = System.getProperty("user.dir");

    	// Local folder where reports are stored (inside workspace)
    	String localFolder = workspacePath + File.separator + "Reports_AF_UserList";

    	// Get today’s date in the same format as file name
    	String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    	// Create folder object
    	File folder = new File(localFolder);

    	// Step 1: Get all UserList files (downloaded by driver)
    	File[] UserListFiles = folder.listFiles((dir, name) -> name.startsWith("UserList"));

    	// Step 2: Check if any UserList files exist
    	if (UserListFiles == null || UserListFiles.length == 0) {
    	    System.out.println("❌ No UserList file found in folder: " + localFolder);
    	    return;
    	}

    	// Step 3: Pick the most recent UserList file
    	File latestTmpFile = UserListFiles[0];
    	for (File f : UserListFiles) {
    	    if (f.lastModified() > latestTmpFile.lastModified()) {
    	        latestTmpFile = f;
    	    }
    	}

    	System.out.println("📄 Found latest UserList file: " + latestTmpFile.getName());

    	// Step 4: Define the final CSV file name
    	String newFileName = "UserList_AF_" + today + ".csv";
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
        String remoteFilePath = "/Powerbi_Analytics/Beatroute/Animal Feed/Users/" + renamedFile.getName();
        String userId ="powerbi.admin";
        String password ="Pbianalyts@456#";

//        FTPClient ftpClient = new FTPClient();
//        try {
//        	ftpClient.connect("10.9.111.212");
//            boolean login = ftpClient.login(userId, password);
//
//            if (login) {
//                System.out.println("Connected to FTP server");
//
//                ftpClient.enterLocalPassiveMode(); // Passive mode
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // For Excel file
//
//                try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
//                    boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
//                    if (done) {
//                        System.out.println("File uploaded successfully to " + remoteFilePath);
//                    } else {
//                        System.out.println("Failed to upload file.");
//                    }
//                }
//
//                ftpClient.logout();
//            } else {
//                System.out.println("Failed to login to FTP server.");
//            }
//
//            ftpClient.disconnect();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    
//    }
        
        FTPClient ftpClient = new FTPClient();
        try {
            String host = "10.9.111.212";   // FTP server
            int port = 22;               // <-- FTP port (change if needed)

            ftpClient.connect(host, port);

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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
  }

}

