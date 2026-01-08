package test;

import org.testng.annotations.Test;

import GenericUtility.BaseClassCPB_Attendance;

public class AttendanceReport_CPB extends BaseClassCPB_Attendance{

	
	@Test
	public void attendanceReport_CPB() throws InterruptedException {
		String user = "Beatroute.admin@godrejagrovet.com";
        String paswrd = "GAVL@123";

        loginPage_AF.sendkeyToUserNameTextField(user);
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
        dashboardPage.clickOnAttendanceSummaryDownloadTab();
        driverUtility.threadWait(15);
        dashboardPage.clickOnGenerate_File();
        driverUtility.threadWait(15);
//        driverUtility.allowAlertPopUp();
//        driverUtility.threadWait(15);
        dashboardPage.clickOnAttendanceSummaryDownloadTab();
        driverUtility.threadWait(15);
        teamActivityPage.attendanceFileDownload();
        driverUtility.threadWait(15);
        
        
	}
}
