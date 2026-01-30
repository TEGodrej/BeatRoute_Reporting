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

import ObjectRepository.HomePageGracia;
import ObjectRepository.LoginGracia;

public class BaseClass_Gracia {

	public  WebDriver driver;
	public LoginGracia loginGracia;
	public HomePageGracia homePageGracia;
	public Driverutility  driverutility;
	
	@BeforeClass
	public void Login() throws InterruptedException {

		
		String downloadFilepath1 = System.getProperty("user.dir") + "\\Reports_Gracia";
		
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
		
		driver.get("https://gracia-admin.vercel.app/");
		
		loginGracia = new LoginGracia(driver);
		homePageGracia = new HomePageGracia(driver);
		driverutility = new Driverutility(driver);
		
		
  }
	@AfterClass
	
	public void closeApp() {
		driver.close();
		
//		driver.quit();
	}
}
