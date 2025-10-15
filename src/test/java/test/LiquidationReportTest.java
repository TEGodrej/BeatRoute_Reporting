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
	    driverutility.WaitTovisibility(10, loginpage.getUsername());
	    loginpage.SendkeyToUserName(user);
	    driverutility.WaitTovisibility(10, loginpage.getPasswordTextField());
	    loginpage.sendkeyToPasswordTextField(paswrd);
	    driverutility.WaitTovisibility(10, loginpage.getLogInButton());
	    loginpage.clickOnLogInButton();
	    driverutility.WaitTovisibility(10, dashboardPage.getScanTab());
		dashboardPage.clickOnScanTab();
		driverutility.WaitTovisibility(10, dashboardPage.getLiquidationTab());
		dashboardPage.clickOnLiquidationTab();
		driverutility.WaitTovisibility(10, dashboardPage.getFilterIcon());
		dashboardPage.clickOnFilterIcon();
		driverutility.WaitTovisibility(10, dashboardPage.getCustomDateRange());
		dashboardPage.clickOnCustomDateRange();

		dashboardPage.clickOnFromDate();
		driverutility.WaitTovisibility(10, dashboardPage.getToDateButton());
		dashboardPage.clickOnToDateButton();
        dashboardPage.clickOnToDate();
        driverutility.WaitTovisibility(10, dashboardPage.getApplyButton());
        dashboardPage.clickOnApplyButton();
        driverutility.WaitTovisibility(10, dashboardPage.getToDateButton());
        dashboardPage.clickOnDownloadTab();
        driverutility.WaitTovisibility(10, dashboardPage.getLiquidationLog());
        dashboardPage.clickOnLiquidationLog();
        driverutility.WaitTovisibility(10, dashboardPage.getDownloadButton());
        dashboardPage.clickOnDownloadButton();
        driverutility.WaitTovisibility(10, loginpage.getLogoutButton());
        loginpage.clickOnLogoutButton();
        
        
        
        
		
		
	}
}
