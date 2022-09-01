
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProjectTest {

    public static WebDriver driver = null;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
    }

    // opens browser, closes browser
    @Test
    public static void testOne(){
//        System.setProperty(Helper.CHROMEKEY,Helper.CHROMEPATH);
        WebDriver driver = Helper.setProperty();
        driver.close();
    }

    // opens browser, goes to google, waits 3 sec, closes browser
    @Test
    public static void testTwo(){
        WebDriver driver = new ChromeDriver();
//        driver = new ChromeDriver();
        driver.get(Helper.GOOGLE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("sleep failed");
        }
        driver.close();
    }

    // navigate
    @Test
    public static void testThree() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.GOOGLE);
        Thread.sleep(3000);
        driver.navigate().to(Helper.GITHUB);
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().forward();
        Thread.sleep(3000);
        driver.navigate().refresh();
        driver.close();
    }

    @Test
    public static void testFour() throws InterruptedException {
        WebDriver driver  = new ChromeDriver();
        driver.get(Helper.GITHUB);
        driver.manage().window().fullscreen();
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.manage().window().minimize();
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.close();
//        driver.quit();
    }

    @Test
    public static void testFive(){
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.SAUCE);
        WebElement loginField = driver.findElement(By.id(Helper.USERNAMEFIELD));
        loginField.sendKeys("hey");
        loginField.clear();
        loginField.sendKeys("haha");
        WebElement passwordField = driver.findElement(By.id(Helper.PASSWORDFIELD));
        passwordField.sendKeys("1234");
        WebElement loginBtn = driver.findElement(By.id(Helper.LOGINBTN));
        loginBtn.click();
        driver.close();
    }

    @Test
    public static void testSix() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.SAUCE);
        // searching for elements
        WebElement loginField = driver.findElement(By.id(Helper.USERNAMEFIELD));
        WebElement passwordField = driver.findElement(By.id(Helper.PASSWORDFIELD));
        WebElement loginBtn = driver.findElement(By.id(Helper.LOGINBTN));
        // interacting with the elements
        loginField.sendKeys(Helper.STANDART);
        passwordField.sendKeys(Helper.PASSWORD);
        loginBtn.click();

        File file1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File file2 = new File(Helper.FOLDER + "1.jpg");
        FileUtils.copyFile(file1,file2);

        driver.get(Helper.SAUCE);
        loginField = driver.findElement(By.id(Helper.USERNAMEFIELD));
        passwordField = driver.findElement(By.id(Helper.PASSWORDFIELD));
        loginBtn = driver.findElement(By.id(Helper.LOGINBTN));
        loginField.sendKeys(Helper.LOCKED);
        passwordField.sendKeys(Helper.PASSWORD);
        loginBtn.click();
        //driver.close();


    }

    @Test
    public static void testSeven() throws IOException {
        WebDriver driver = new ChromeDriver();
        for (int i = 0; i < Helper.USERS.length; i++) {
            driver.get(Helper.SAUCE);
            WebElement usernameField = driver.findElement(By.id(Helper.USERNAMEFIELD));
            WebElement passwordField = driver.findElement(By.id(Helper.PASSWORDFIELD));
            WebElement loginBtn = driver.findElement(By.id(Helper.LOGINBTN));
            usernameField.sendKeys(Helper.USERS[i]);
            passwordField.sendKeys(Helper.PASSWORD);
            loginBtn.click();
            File path = new File(Helper.FOLDER1 + (i+1) + Helper.JPG);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,path);
        }
        driver.quit();
    }

    @Test
    public static void testEight(){
        WebDriver driver = Helper.setProperty();
        driver.get(Helper.SAUCE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernameField =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.USERNAMEFIELD)));
        usernameField.sendKeys("found it");
        driver.quit();
    }
}
