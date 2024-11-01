package demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
   public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com");
        String currenturl = driver.getCurrentUrl();
        if(currenturl.contains("calendar")){
            System.out.println("Test Passed: The link contains the word 'calendar");
        }else{
            System.out.println("Test Failed : The link doesn't contain the word 'calendar"); 
        }

        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        System.out.println("Starting testCase02");
        //driver.get("https://calendar.google.com");


            Thread.sleep(4000);
        

        WebElement switchtotab = driver.findElement(By.xpath("//div[@class='VfPpkd-xl07Ob-XxIAqe-OWXEXe-oYxtQd Cd9hpd']"));
        switchtotab.click();

        Thread.sleep(2000);

        WebElement monthbutton = driver.findElement(By.xpath("//span[text()='Month']"));
        monthbutton.click();

        WebElement Tab = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[5]"));

       String text =  Tab.getText();
        if(text.contains("Month")){
            System.out.println("month is selected");
        }else{
            System.out.println("month is not selected");
        }
        System.out.println("Clicked on month");
        WebElement newcreate = driver.findElement(By.className("mr0WL"));
        newcreate.click();
        System.out.println("clicked on create Tab");

        try {
            // Pause execution for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement taskTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='Task'])[1]")));
        WebElement taskTab = driver.findElement(By.xpath("(//div[text()='Task'])[1]"));
        taskTab.click();
        System.out.println("Task tab clicked");
        
        try {
            // Pause execution for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
        WebElement Title = driver.findElement(By.xpath("(//input[@class='VfPpkd-fmcmS-wGMbrd '])[2]"));
        Title.click();
        System.out.println("add new button clicked");
        Title.sendKeys("Crio INTV Task Automation");


        WebElement descriptionBox = driver.findElement(By.xpath("//textarea[@class='VfPpkd-fmcmS-wGMbrd TaTzUd']"));
        descriptionBox.click();
        System.out.println("description box clicked");
        descriptionBox.sendKeys("Crio INTV Calendar Task Automation");


        try {
            // Pause execution for 5 seconds (5000 milliseconds)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }

        WebElement save = driver.findElement(By.xpath("//button[@data-idom-class='nCP5yc AjY5Oe DuMIQc LQeN7 pEVtpe']"));
        save.click();

        Thread.sleep(5000);
        

        WebElement reverifyBtn = driver.findElement(By.xpath("(//span[@class='WBi6vc'])[4]"));
        reverifyBtn.click();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Wait for the element to be visible
WebElement titleelem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='rAECCd']")));
Thread.sleep(2000);
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].value='';",titleelem );  // Modify the innerText of the span

// Check if it contains the desired text
System.out.println("Title text is: "+titleelem.getText());
if(titleelem.getText().contains("Crio INTV Task Automation")){
    System.out.println("contains TEXT");
} else {
    System.out.println("Doesn't contain text");
}
        
        WebElement descriptionelem = driver.findElement(By.xpath("//div[@id='xDetDlgDesc']"));
        js.executeScript("arguments[0].value='';", descriptionelem);
        System.out.println("Description Text is:"+descriptionelem.getText());
        if(descriptionelem.getText().contains("Crio INTV Calendar Task Automation")){
            System.out.println("contains TEXT");

        }else{
            System.out.println("Doesnt contain text");
        }
        Thread.sleep(2000);
    }
    
    public void testCase03() throws InterruptedException {
        System.out.println("Starting testCase03");
        //driver.get("https://calendar.google.com/");
    
        try {
            // Pause execution for 5 seconds (5000 milliseconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    
        // Find and click on the second task in the calendar
        WebElement selectedTask = driver.findElement(By.xpath("(//span[@class='nHqeVd'])[3]"));
        selectedTask.click();
         Thread.sleep(5000);
    
        // Click on the edit button
        WebElement editButton = driver.findElement(By.xpath("(//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe'])[1]"));
        editButton.click();
         Thread.sleep(4000);
    
        // Update the task description
        WebElement descriptionField = driver.findElement(By.xpath("//textarea[@class='VfPpkd-fmcmS-wGMbrd vRGQ0d']"));
        descriptionField.click();
        descriptionField.clear();
        descriptionField.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
    
        // Save the changes
        WebElement saveButton = driver.findElement(By.xpath("//span[text()='Save']"));
        saveButton.click();
    
        // Wait for 3 seconds
        Thread.sleep(3000);
    
        // Re-verify the updated description
        WebElement verifyTask = driver.findElement(By.xpath("(//span[@class='nHqeVd'])[3]"));
        Thread.sleep(5000);
        verifyTask.click();
    
        WebElement descriptionVerification = driver.findElement(By.id("xDetDlgDesc"));
        System.out.println("Verified Description is: "+descriptionVerification.getText());
        Thread.sleep(5000);
        if (descriptionVerification.getText().contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
            System.out.println("Description contains the correct text");
        } else {
            System.out.println("Description does not contain the correct text");
        }
    }
    public void testCase04() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        driver.get("https://calendar.google.com/calendar/u/0/r");

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
    
    