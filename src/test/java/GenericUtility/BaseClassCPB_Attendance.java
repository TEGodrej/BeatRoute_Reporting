package GenericUtility;


import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ObjectRepository.DashBoardPage;
import ObjectRepository.LoginPage_AF;
import ObjectRepository.TeamActivityPage;


public class BaseClassCPB_Attendance {

	public static WebDriver driver;
	public LoginPage_AF loginPage_AF;
	public DashBoardPage dashboardPage;
	public Driverutility driverUtility;
	public TeamActivityPage teamActivityPage;
	
	@BeforeClass
	public void Login() throws InterruptedException {

		
		String downloadFilepath1 = System.getProperty("user.dir") + "\\AttendanceReport_CPB";
		
		File file = new File(downloadFilepath1);
		if (!file.exists()) file.mkdirs();
		
		Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
			 
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://app.beatroute.io/site/login");
		
		loginPage_AF               = new LoginPage_AF(driver);
		dashboardPage              = new DashBoardPage(driver);
		driverUtility              = new Driverutility(driver);
		teamActivityPage           = new TeamActivityPage(driver);
	}
	
	@AfterClass
	public void closeInstance() {
		driver.quit();
		System.out.println("File has been downloaded inside the project");
	}
	
}
