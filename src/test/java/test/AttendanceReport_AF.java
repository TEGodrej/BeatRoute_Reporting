package test;

import org.testng.annotations.Test;

import GenericUtility.BaseClassAF_Attendance;

public class AttendanceReport_AF extends BaseClassAF_Attendance{

	
	@Test
	public void attendanceReport_AF() throws InterruptedException {
		String user = "ajit.sahu@godrejagrovet.com";
        String paswrd = "Ganpati@123456";

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
