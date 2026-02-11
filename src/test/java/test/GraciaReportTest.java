package test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import GenericUtility.BaseClass_Gracia;

public class GraciaReportTest extends BaseClass_Gracia{

	@Test
	public void report_Gracia() {
		String username="admin";
		String password="admin@gracia";
		driverutility.threadWait(4);
		loginGracia.sendkeyToUserNameTextField(username);
		driverutility.threadWait(4);
		loginGracia.sendkeyToPasswordTextField(password);
		loginGracia.clickOnSubmitButton();
		driverutility.threadWait(6);
		homePageGracia.clickOnTransactionButton();
		driverutility.threadWait(4);
		homePageGracia.clickOnCalenderButton();
		driverutility.threadWait(10);
		homePageGracia.fromDate();
		driverutility.threadWait(4);
		homePageGracia.select_To_Date();
		driverutility.threadWait(4);
		homePageGracia.clickOnApplyFilter();
		driverutility.threadWait(10);
		homePageGracia.clickOnDownloadButton();
		driverutility.threadWait(8);
		driverutility.allowAlertPopUp();
		driverutility.threadWait(60);
		loginGracia.ClickOnLogoutButton();
	}
	
	@Test (dependsOnMethods = {"report_Gracia"})
	public void gracia_Report_Upload() {
		String workspacePath = System.getProperty("user.dir");
		
		// Local folder where reports are stored (inside workspace)
    	String localFolder = workspacePath + File.separator + "Reports_Gracia";
    	
    	String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	
    	// Create folder object
    	File folder = new File(localFolder);
    	
    	 // Get all files starting with "Liquidation_Log_<today>" and ending with ".xlsx"
   	 File[] todayFiles = folder.listFiles((dir, name) ->
   	     name.startsWith("budget") && name.endsWith(".csv")
   	 );
    	// Step 2: Check if any .tmp files exist
    	if (todayFiles == null || todayFiles.length == 0) {
    	    System.out.println(" No .tmp file found in folder: " + localFolder);
    	    return;
    	}

    	// Step 3: Pick the most recent .tmp file
    	File latestTmpFile = todayFiles[0];
    	for (File f : todayFiles) {
    	    if (f.lastModified() > latestTmpFile.lastModified()) {
    	        latestTmpFile = f;
    	    }
    	} 
    	
    	System.out.println("Found latest .csv file: " + latestTmpFile.getName());
    	

    	// Step 4: Define the final CSV file name
    	String newFileName = "Gracia_Budget_Data" + today + ".csv";
    	File renamedFile = new File(folder, newFileName);

    	// Step 5: Wait until file is fully downloaded (optional but safer)
    	long previousSize = 0;
    	long currentSize = latestTmpFile.length();
    	int stableCount = 0;

    	System.out.println(" Waiting for download to complete...");
    	while (stableCount < 3) { // Check stability 3 times in a row
    	    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    	    previousSize = currentSize;
    	    currentSize = latestTmpFile.length();
    	    if (currentSize == previousSize) stableCount++;
    	    else stableCount = 0;
    	}
    	System.out.println(" Download seems complete.");

    	// Step 6: Rename file to CSV format
    	boolean renamed = latestTmpFile.renameTo(renamedFile);
    	
    	if (renamed) {
    	    System.out.println(" File renamed successfully to: " + renamedFile.getAbsolutePath());
    	} else {
    	    System.out.println(" Failed to rename file: " + latestTmpFile.getName());
    	    return;
    	}

    	// Step 7: Proceed with FTP upload using renamedFile
    	
    	System.out.println(" Ready to upload: " + renamedFile.getName());
    	
    	 // Latest file path
      	 String localFilePath = renamedFile.getAbsolutePath();
      	 System.out.println(" Found today's file: " + localFilePath);
          
           String userId ="powerbi.admin";
           String password ="Pbianalyts@456#";
           
//           String remoteFilePath = "/Powerbi_Analytics/CPB/Gracia Spinwheel/" + renamedFile.getName();
//
//           FTPClient ftpClient = new FTPClient();
//           try {
//           	ftpClient.connect("10.9.111.212");
//               boolean login = ftpClient.login(userId, password);
//
//               if (login) {
//                   System.out.println("Connected to FTP server");
//
//                   ftpClient.enterLocalPassiveMode(); // Passive mode
//                   ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // For Excel file
//
//                   try (FileInputStream inputStream = new FileInputStream(localFilePath)) {
//                       boolean done = ftpClient.storeFile(remoteFilePath, inputStream);
//                       if (done) {
//                           System.out.println("File uploaded successfully to " + remoteFilePath);
//                       } else {
//                           System.out.println("Failed to upload file.");
//                       }
//                   }
//
//                   ftpClient.logout();
//               } else {
//                   System.out.println("Failed to login to FTP server.");
//               }
//
//               ftpClient.disconnect();
//           } catch (IOException ex) {
//               ex.printStackTrace();
//           }
       
           
           String host="10.9.111.212";
           String remoteFilePath = "/Powerbi_Analytics/CPB/Gracia Spinwheel/";

           
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
               channel.put(localFilePath, renamedFile.getName());

               System.out.println("SFTP upload successful");

           } catch (Exception e) {
               throw new RuntimeException("SFTP upload failed", e);
           } finally {
               if (channel != null) channel.disconnect();
               if (session != null) session.disconnect();
           }


	}
}
