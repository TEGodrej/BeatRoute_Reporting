package GenericUtility;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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


public class BaseClassAF {

	public static WebDriver driver;
	public LoginPage_AF loginPage_AF;
	public DashBoardPage dashboardPage;
	public Driverutility driverUtility;
	public TeamActivityPage teamActivityPage;
	
	@BeforeClass
	public void Login() {
		// Get today’s date in dd-MM-yyyy format
		String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

		// Create download folder (e.g., <project>\Reports_AF_10-11-2025)
		String downloadDirPath = System.getProperty("user.dir") + "\\Reports_AF";
		File downloadDir = new File(downloadDirPath);

		// Create the folder if it doesn’t exist
		if (!downloadDir.exists()) {
		    downloadDir.mkdirs();
		}

		// Set Chrome download preferences
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadDirPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("safebrowsing.enabled", true);

		// Apply preferences to ChromeOptions
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		// --- Wait for Chrome to finish renaming .tmp → .csv, then rename final CSV ---
		try {
		    System.out.println("⏳ Waiting for new file to download...");

		    long timeoutMillis = 120_000; // 2 minutes
		    long pollingInterval = 1000;  // 1 sec
		    long startTime = System.currentTimeMillis();

		    File finalCsvFile = null;

		    while (System.currentTimeMillis() - startTime < timeoutMillis) {
		        // Look for any .csv file in the folder
		        File[] csvFiles = downloadDir.listFiles((dir, name) -> name.endsWith(".csv"));
		        if (csvFiles != null && csvFiles.length > 0) {
		            // Pick the latest modified CSV file
		            File latest = csvFiles[0];
		            for (File f : csvFiles) {
		                if (f.lastModified() > latest.lastModified()) {
		                    latest = f;
		                }
		            }
		            finalCsvFile = latest;
		            break;
		        }

		        // If not found, check for a .tmp file (still downloading)
		        File[] tmpFiles = downloadDir.listFiles((dir, name) -> name.endsWith(".tmp"));
		        if (tmpFiles != null && tmpFiles.length > 0) {
		            System.out.print("."); // show progress
		        }

		        Thread.sleep(pollingInterval);
		    }

		    if (finalCsvFile != null) {
		        System.out.println("\n📥 Download completed: " + finalCsvFile.getName());

		        // Rename downloaded CSV to AF_<date>.csv
		        File renamed = new File(downloadDirPath + "\\AF_" + today + time+".csv");
		        if (finalCsvFile.renameTo(renamed)) {
		            System.out.println("✅ File renamed to: " + renamed.getName());
		        } else {
		            System.out.println("❌ Rename failed for: " + finalCsvFile.getName());
		        }

		    } else {
		        System.out.println("⚠️ No CSV file detected after waiting.");
		    }

		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
			 
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
