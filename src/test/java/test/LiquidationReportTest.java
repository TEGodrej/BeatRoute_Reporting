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
	    loginpage.SendkeyToUserName(user);
	    loginpage.sendkeyToPasswordTextField(paswrd);
	    loginpage.clickOnLogInButton();
		Thread.sleep(1000);
		dashboardPage.clickOnSacnTab();
		Thread.sleep(1000);
		dashboardPage.clickOnLiquidationTab();
		Thread.sleep(1000);
		dashboardPage.clickOnFilterIcon();
		dashboardPage.clickOnCustomDateRange();
		Thread.sleep(1000);
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
