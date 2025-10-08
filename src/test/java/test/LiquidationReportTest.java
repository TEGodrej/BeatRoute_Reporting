package test;

import java.time.Duration;

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
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		dashboardPage.clickOnSacnTab();
		Thread.sleep(Duration.ofSeconds(2).toMillis());
		dashboardPage.clickOnLiquidationTab();
		Thread.sleep(Duration.ofSeconds(6).toMillis());
		dashboardPage.clickOnFilterIcon();
		dashboardPage.clickOnCustomDateRange();
		Thread.sleep(Duration.ofSeconds(4).toMillis());
		dashboardPage.clickOnFromDate();
		dashboardPage.clickOnToDateButton();
        Thread.sleep(Duration.ofSeconds(4).toMillis());
        dashboardPage.clickOnToDate();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        dashboardPage.clickOnApplyButton();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        dashboardPage.clickOnDownloadTab();
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        dashboardPage.clickOnLiquidationLog();
        Thread.sleep(Duration.ofSeconds(4).toMillis());
        dashboardPage.clickOnDownloadButton();
        Thread.sleep(Duration.ofSeconds(7).toMillis());
        loginpage.clickOnLogoutButton();
        
        
        
        
		
		
	}
}
