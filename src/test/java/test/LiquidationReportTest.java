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
		 // 🔹 Set custom download path before launching browser
		

//        String downloadFilepath1 = System.getProperty("user.dir") + "\\Reports";
        
//        String today1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String downloadFilepath1 = System.getProperty("user.dir") + "\\Reports";
//
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("download.default_directory", downloadFilepath1);
//        prefs.put("download.prompt_for_download", false);
//        prefs.put("download.directory_upgrade", true);
//        prefs.put("safebrowsing.enabled", true);
//        
//       
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		 
//		 WebDriver driver = new ChromeDriver(options); // ✅ use this driver everywhere

//	        driver.manage().window().maximize();
//	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // Login
//	        driver.get("https://gavl.uniqid.io");
	        String user = "PowerBI";
	        String paswrd = "Ganpati#123456";
	        
//		WebElement userName= driver.findElement(By.id("Username"));
//		userName.sendKeys(user);
	        
	    loginpage.SendkeyToUserName(user);
	        
		
//		WebElement password= driver.findElement(By.id("Password"));
//		password.sendKeys(paswrd);
	    
	    loginpage.sendkeyToPasswordTextField(paswrd);
		
//		WebElement loginButton=driver.findElement(By.xpath("//button[@type='submit']"));
//		loginButton.click();
	    
	    loginpage.clickOnLogInButton();
		
		Thread.sleep(Duration.ofSeconds(2));
		
//		WebElement scanTab=driver.findElement(By.xpath("//mat-label[text()='Scan']"));
//		scanTab.click();
		
		dashboardPage.clickOnSacnTab();
		
		Thread.sleep(Duration.ofSeconds(2));
//		WebElement liquidatonTab=driver.findElement(By.xpath("//mat-label[text()='Liquidation']"));
//		liquidatonTab.click();
		
		dashboardPage.clickOnLiquidationTab();
		
		Thread.sleep(Duration.ofSeconds(6));
		
//		WebElement filter=driver.findElement(By.xpath("(//span[@title='Advanced filters'])[1]"));
//		filter.click();
		
		dashboardPage.clickOnFilterIcon();
		
//		WebElement customeDateRange=driver.findElement(By.xpath("//li[text()=' Custom date range ']"));
//		customeDateRange.click();
		
		dashboardPage.clickOnCustomDateRange();
		
		Thread.sleep(Duration.ofSeconds(4));
		
//		LocalDate today = LocalDate.now();
//		
//        LocalDate yesterday = today.minusDays(1);
//        
//        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
//        System.out.println("Today: " + today);
//        System.out.println("Yesterday: " + yesterday);
//        
//        int day=yesterday.getDayOfMonth();
//       
//        if (today.isEqual(firstDayOfMonth)) {
//            // Click the "previous month" button
//            WebElement previousMonthBtn = driver.findElement(By.xpath(
//                "//button[@class='mat-calendar-previous-button mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']"
//            ));
//            previousMonthBtn.click();
//
//            // Small wait to allow calendar to load previous month
//            Thread.sleep(1000);
//
//            // Re-locate yesterday’s day in the *previous month’s calendar*
//            
//            WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
//            yesterdays.click();
//
//        } else {
//            // Same month → just click yesterday’s date
//           
//            WebElement yesterdays = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
//            yesterdays.click();
//        }
		
		dashboardPage.clickOnFromDate();
        
//        WebElement toDate=driver.findElement(By.xpath("//input[@value='toDate']"));
//        toDate.click();
		
		dashboardPage.clickOnToDateButton();
        
        Thread.sleep(Duration.ofSeconds(4));
        
//        WebElement yesterday1 = driver.findElement(By.xpath("//div[text()=' "+day+" ']"));
//        yesterday1.click();
        
//        if (today.isEqual(firstDayOfMonth)) {
//            // Click the "previous month" button
//            WebElement previousMonthBtn = driver.findElement(By.xpath(
//                "//button[@class='mat-calendar-previous-button mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']"
//            ));
//            previousMonthBtn.click();
//
//            // Small wait to allow calendar to load previous month
//            Thread.sleep(1000);
//
//            // Re-locate yesterday’s day in the *previous month’s calendar*
//            
//            WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
//            yesterdays1.click();
//
//        } else {
//            // Same month → just click yesterday’s date
//           
//            WebElement yesterdays1 = driver.findElement(By.xpath("//div[text()=' " + day + " ']"));
//            yesterdays1.click();
//        }
//        
        dashboardPage.clickOnToDate();
        
        Thread.sleep(Duration.ofSeconds(2));
        
//        WebElement applyButton=driver.findElement(By.xpath("//button[text()='Apply ']"));
//        applyButton.click();
        
        dashboardPage.clickOnApplyButton();
        
        Thread.sleep(Duration.ofSeconds(2));
//        WebElement downloadTab=driver.findElement(By.xpath("//i[@class='fa fa-download scASFIconStyle scCMCenterXY']"));
//        downloadTab.click();
        
        dashboardPage.clickOnDownloadTab();
        
//        WebElement overAllLiquidation=driver.findElement(By.xpath("//div[@class='scASFDropdownListItems']/child::span[text()='Overall liquidation']"));
//        overAllLiquidation.click();   //div[@class='scASFDropdownListItems']/child::span[text()='Liquidation log']
        
        Thread.sleep(Duration.ofSeconds(2));
//        WebElement liquidationLog=driver.findElement(By.xpath("//div[@class='scASFDropdownListItems']/child::span[text()='Liquidation log']"));
//        liquidationLog.click();
        
        dashboardPage.clickOnLiquidationLog();
        
        Thread.sleep(Duration.ofSeconds(4));
//        WebElement downloadButton=driver.findElement(By.xpath("//button[text()='Download ']"));
//        downloadButton.click();
        
        dashboardPage.clickOnDownloadButton();
        
        Thread.sleep(Duration.ofSeconds(7));
//        WebElement logout=driver.findElement(By.xpath("//mat-icon[text()='logout']"));
//        logout.click();
        
        loginpage.clickOnLogoutButton();
        
//        driver.quit();
        
        
        
		
		
	}
}
