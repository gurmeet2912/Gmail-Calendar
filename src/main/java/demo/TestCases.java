package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import dev.failsafe.internal.util.Assert;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
   ChromeDriver driver;
    

    public TestCases() {
        System.out.println("Constructor: TestCases");
        
        
        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        //options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

          driver = new ChromeDriver(options);
          

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        // verifying URL

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("calendar.")) {
            System.out.println("URL verification successful: " + currentUrl);
        } else {
            System.out.println("URL verification failed. Current URL: " + currentUrl);
            return;
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Start Test case: testCase02");
        driver.get("https://calendar.google.com/");
        // Select Month View
        WebElement viewButton= driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-INsAgc VfPpkd-LgbsSe-OWXEXe-Bz112c-UbuQg VfPpkd-LgbsSe-OWXEXe-dgl2Hf Rj2Mlf OLiIxf PDpWxe LQeN7 j9Fkxf I2n60c']"));
        viewButton.click();
        WebElement monthElement = driver.findElement(By.xpath("//span[text()='Month']"));
        monthElement.click();

        WebElement pageContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body"))); // Get the page body
        String pageText = pageContent.getText();
        
        if (pageText.contains("Month")) {
            System.out.println("Switched to Month view successfully.");
        } else {
            System.out.println("Failed to switch to Month view.");
        }


        

        WebElement calArea = driver.findElement(By.xpath("//div[@id='YPCqFe']"));
        calArea.click();

        Thread.sleep(3000);

        WebElement taskButton = driver.findElement(By.xpath("//div[@class='x5FT4e kkUTBb' and text()='Task']"));
        taskButton.click();

        WebElement addTask = driver.findElement(By.xpath("//input[@placeholder='Add title and time']"));
        addTask.sendKeys("Crio INTV Task Automation");

        WebElement description = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        description.sendKeys("Crio INTV Calendar Task Automation");

        WebElement savElement = driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 pEVtpe']"));
        savElement.click();
       
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Task created']"))); // Example selector, adjust as needed

        // Verify the popup message
        String expectedMessage = "Task created";
        String actualMessage = popupMessage.getText();
        if (expectedMessage.equals(actualMessage)) {
            System.out.println("Popup message verified successfully: " + actualMessage);
        } else {
            System.out.println("Popup message verification failed. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
       
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://calendar.google.com/");

        Thread.sleep(5000);
    
        // Wait for the calendar area to be clickable and click it
        WebElement addedTask = driver.findElement(By.xpath("//span[text()='Crio INTV Task Automation']"));
        addedTask.click();

        Thread.sleep(4000);
        //Verify task Title 

        
    
        WebElement editTask = driver.findElement(By.xpath("//button[@aria-label='Edit task']"));
        editTask.click();

        Thread.sleep(5000);
    
        // Find the description field
        WebElement description = driver.findElement(By.xpath("//textarea[text()='Crio INTV Calendar Task Automation']"));
        description.clear();
        // Clear the description field using JavaScript
        //js.executeScript("arguments[0].value='';", description);
    
        // Update the description field
        description.sendKeys( "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
    
        // Save the task using the Save button
        WebElement savElement = driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7']"));
        savElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Task updated']"))); // Example selector, adjust as needed

        // Verify the popup message
        String expectedMessage = "Task updated";
        String actualMessage = popupMessage.getText();
        if (expectedMessage.equals(actualMessage)) {
            System.out.println("Popup message verified successfully: " + actualMessage);
        } else {
            System.out.println("Popup message verification failed. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
        Thread.sleep(2000);
    
        // WebElement taskTab = driver.findElement(By.xpath("//div[@aria-label='Tasks']"));
        // taskTab.click();

        WebElement addedTask1 = driver.findElement(By.xpath("//span[text()='Crio INTV Task Automation']"));
        addedTask1.click();
    // addedTask.click();
         WebElement updatedDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='xDetDlgDesc']")));
         if((updatedDescription.getText().equals("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"))){
            System.out.println("Updated Description Successfully");
         } 
         else{
            System.out.println("Updation Verification Failed");
         }       
        System.out.println("End Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        driver.get("https://calendar.google.com/");

        Thread.sleep(5000);

        WebElement addedTask = driver.findElement(By.xpath("//span[text()='Crio INTV Task Automation']"));
        addedTask.click();

        Thread.sleep(4000);

        WebElement titleOfTask = driver.findElement(By.xpath("//span[@id='rAECCd']"));
        String title=  titleOfTask.getText();

        if(title.equals("Crio INTV Task Automation")){
            System.out.println("Title Verified");
        }
        else{
            System.out.println("Title can't be verified");
        }

        WebElement deleteButton= driver.findElement(By.xpath("//button[@aria-label='Delete task']"));
        deleteButton.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Task deleted']"))); // Example selector, adjust as needed

        // Verify the popup message
        String expectedMessage = "Task deleted";
        String actualMessage = popupMessage.getText();
        if (expectedMessage.equals(actualMessage)) {
            System.out.println("Popup message verified successfully: " + actualMessage);
        } else {
            System.out.println("Popup message verification failed. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
        Thread.sleep(2000);


    }

       
}
