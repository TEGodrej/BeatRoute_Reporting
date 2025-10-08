package GenericUtility;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ObjectRepository.DashBoardPage;
import ObjectRepository.LoginPage;


public class BaseClass {

	public static WebDriver driver;
	public LoginPage loginpage;
	public DashBoardPage dashboardPage;
	
	@BeforeClass
	public void Login() {
		 String downloadFilepath1 = System.getProperty("user.dir") + "\\Reports";

	        Map<String, Object> prefs = new HashMap<>();
	        prefs.put("download.default_directory", downloadFilepath1);
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("download.directory_upgrade", true);
	        prefs.put("safebrowsing.enabled", true);
	        
	       
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			 
			  driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
		
		driver.get("https://gavl.uniqid.io");
		
		loginpage                  =new LoginPage(driver);
		dashboardPage              = new DashBoardPage(driver);
	}
	
	@AfterClass
	public void closeInstance() {
		driver.quit();
		System.out.println("File has been downloaded inside the project");
	}
	
}
