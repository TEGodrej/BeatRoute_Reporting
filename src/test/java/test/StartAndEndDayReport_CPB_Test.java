package test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import GenericUtility.BaseClassAF_Log_CPB;

public class StartAndEndDayReport_CPB_Test extends BaseClassAF_Log_CPB{

	@Test
	public void start_N_EndDayLog() {
		
		String user = "Beatroute.admin@godrejagrovet.com";
        String paswrd = "GAVL@123";

        System.out.println("=========StartAndEndDayReport_CPB===========");
        loginPage_AF.sendkeyToUserNameTextField(user);
        loginPage_AF.cancelPopup();
        loginPage_AF.clickOnProceedButton();
        loginPage_AF.sendkeyToPasswordTextField(paswrd);
        loginPage_AF.clickOnLoginButton();
        driverUtility.threadWait(2);
        dashboardPage.clickOnAttendanceTab();
        driverUtility.threadWait(2);
        dashboardPage.clickOncheckInTab();
        driverUtility.threadWait(2);
        teamActivityPage.clickOnAttendanceDateChange();
        teamActivityPage.select_FromDate();
        driverUtility.threadWait(2);
        teamActivityPage.select_ToDate();
        driverUtility.threadWait(15);
        teamActivityPage.clickOnDailyAttendanceButton();
        driverUtility.moveToElementByAction(teamActivityPage.getuserDay());
        driverUtility.threadWait(4);
        teamActivityPage.clickOnGenerate_FileTab();
        driverUtility.threadWait(4);
        teamActivityPage.clickOnDownload_Button();
        driverUtility.threadWait(20);

	}
	
	@Test(dependsOnMethods = {"start_N_EndDayLog"})
	public void upload_start_N_EndDayLogReport() {
		// Get workspace path dynamically (works for both local and Jenkins)
    	String workspacePath = System.getProperty("user.dir");

    	// Local folder where reports are stored (inside workspace)
    	String localFolder = workspacePath + File.separator + "UserLogReport_CPB";

    	// Get today’s date in the same format as file name
    	String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    	// Create folder object
    	File folder = new File(localFolder);

    	// Step 1: Get all UserList files (downloaded by driver)
    	File[] UserListFiles = folder.listFiles((dir, name) -> name.startsWith("Start_End_Day") && name.endsWith(".csv"));

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

    	System.out.println("📄 Found latest Start_End_Day file: " + latestTmpFile.getName());
    	
    	// Step 4: Define the final CSV file name
    	String newFileName = "CPB_Start_End_Day_Activities_Report" + today + ".csv";
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
    	// (Example placeholder)
    	System.out.println(" Ready to upload: " + renamedFile.getName());
    	
    	 // Latest file path
      	 String localFilePath = renamedFile.getAbsolutePath();
      	 System.out.println(" Found today's file: " + localFilePath);
           String remoteFilePath = "/Powerbi_Analytics/Beatroute/CPB/Start End Day Activity";
           String userId ="powerbi.admin";
           String password ="Pbianalyts@456#";
           String host="10.9.111.212";


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
