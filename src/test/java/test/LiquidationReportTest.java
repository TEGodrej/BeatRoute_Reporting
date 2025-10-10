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
	    Thread.sleep(8000);
	    loginpage.SendkeyToUserName(user);
	    Thread.sleep(8000);
	    loginpage.sendkeyToPasswordTextField(paswrd);
	    Thread.sleep(8000);
	    loginpage.clickOnLogInButton();
		Thread.sleep(8000);
		dashboardPage.clickOnSacnTab();
		Thread.sleep(1000);
		dashboardPage.clickOnLiquidationTab();
		Thread.sleep(5000);
		dashboardPage.clickOnFilterIcon();
		dashboardPage.clickOnCustomDateRange();
		Thread.sleep(5000);
		dashboardPage.clickOnFromDate();
		dashboardPage.clickOnToDateButton();
		Thread.sleep(1000);
        dashboardPage.clickOnToDate();
        Thread.sleep(1000);
        dashboardPage.clickOnApplyButton();
        Thread.sleep(1000);
        dashboardPage.clickOnDownloadTab();
        Thread.sleep(1000);
        dashboardPage.clickOnLiquidationLog();
        Thread.sleep(1000);
        dashboardPage.clickOnDownloadButton();
        Thread.sleep(1000);
        loginpage.clickOnLogoutButton();
        
        
        
        
		
		
	}
}
