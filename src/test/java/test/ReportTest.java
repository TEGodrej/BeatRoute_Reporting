package test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class ReportTest {

    @Test
    public void reportGeneration() throws InterruptedException {
        // 🔹 Set custom download path before launching browser

        String downloadFilepath1 = System.getProperty("user.dir") + "/Reports";

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options); // ✅ use this driver everywhere

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Login
        driver.get("https://app.beatroute.io/site/login");
        String user = "ajit.sahu@godrejagrovet.com";
        String paswrd = "Ganpati@123456";

        WebElement userName = driver.findElement(By.xpath("//input[@placeholder='Please enter your email']"));
        userName.sendKeys(user);

        WebElement proceedBtn = driver.findElement(By.xpath("//input[@value='PROCEED']"));
        proceedBtn.click();

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
        password.sendKeys(paswrd);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Log In']"));
        loginBtn.click();

        // Handle date
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("dd MMM, yyyy");
        String day = sim.format(date);

        WebElement dateChange = driver.findElement(By.xpath(
            "//div[@id='activityDateFilterTrigger1']//span[@class='printDate'][normalize-space()='" + day + "']"));
        dateChange.click();

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String day1 = sdf.format(date);
        WebElement reportDate = driver.findElement(By.xpath("//a[@data-date='" + day1 + "']"));
        reportDate.click();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        String day2 = sdf1.format(date);
        WebElement reportDate1 = driver.findElement(By.xpath("//a[@data-date='" + day2 + "']"));
        reportDate1.click();

        // Open activity log
        WebElement activityLog = driver.findElement(By.xpath(
            "//body[1]/div[5]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/div[1]/i[1]"));
        activityLog.click();

        WebElement generateFile = driver.findElement(By.xpath(
            "//a[contains(@href,'/download/CSV/logRequest/type/activity')]"));
        generateFile.click();

        // Handle alert
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement downloadBtn = driver.findElement(By.xpath(
            "//a[@href='/download/CSV/getLast/type/activity']"));

        // Wait until 50 minutes later
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetTime = now.plusMinutes(50);

        System.out.println("Current Time: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("Will click at: " + targetTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        while (LocalDateTime.now().isBefore(targetTime)) {
            Thread.sleep(1000);
        }

        // Click to download (file goes to /downloads inside project)
        downloadBtn.click();

//        System.out.println("File downloaded to: " + downloadFilepath);
    }
}
