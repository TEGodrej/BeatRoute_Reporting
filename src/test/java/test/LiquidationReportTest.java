package test;

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
	    driverUtility.WaitTovisibility(10, loginpage.getUsername());
	    loginpage.SendkeyToUserName(user);
	    driverUtility.WaitTovisibility(10, loginpage.getPasswordTextField());
	    loginpage.sendkeyToPasswordTextField(paswrd);
	    driverUtility.WaitTovisibility(10, loginpage.getLogInButton());
	    loginpage.clickOnLogInButton();
	    driverUtility.WaitTovisibility(10, dashboardPage.getScanTab());
		dashboardPage.clickOnScanTab();
		driverUtility.WaitTovisibility(10, dashboardPage.getLiquidationTab());
		dashboardPage.clickOnLiquidationTab();
		driverUtility.WaitTovisibility(10, dashboardPage.getFilterIcon());
		dashboardPage.clickOnFilterIcon();
		driverUtility.WaitTovisibility(10, dashboardPage.getCustomDateRange());
		dashboardPage.clickOnCustomDateRange();

		dashboardPage.clickOnFromDate();
		driverUtility.WaitTovisibility(10, dashboardPage.getToDateButton());
		dashboardPage.clickOnToDateButton();
        dashboardPage.clickOnToDate();
        driverUtility.WaitTovisibility(10, dashboardPage.getApplyButton());
        dashboardPage.clickOnApplyButton();
        driverUtility.WaitTovisibility(10, dashboardPage.getToDateButton());
        dashboardPage.clickOnDownloadTab();
        driverUtility.WaitTovisibility(10, dashboardPage.getLiquidationLog());
        dashboardPage.clickOnLiquidationLog();
        driverUtility.WaitTovisibility(10, dashboardPage.getDownloadButton());
        dashboardPage.clickOnDownloadButton();
        driverUtility.WaitTovisibility(10, loginpage.getLogoutButton());
        loginpage.clickOnLogoutButton();
        
        
        
        
		
		
	}
}
