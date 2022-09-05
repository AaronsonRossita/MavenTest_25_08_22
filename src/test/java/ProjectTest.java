
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
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
//        WebElement usernameField =
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.USERNAMEFIELD)));
//        WebElement usernameField = driver.findElement(By.cssSelector("#user-name"));
//        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
//        WebElement usernameField =
                driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div[1]/div/form/div[1]/input"));

        WebElement usernameField = driver.findElement(By.name("user-name"));
//        WebElement usernameField = driver.findElement(By.);
        usernameField.sendKeys("found it");
        //driver.quit();
    }

    @Test
    public static void testNine() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.SAUCE);
        String firstWindow = driver.getWindowHandle();
//        System.out.println(firstWindow);
//        WebDriver driverTwo = new ChromeDriver();
//        driverTwo.get(Helper.GOOGLE);
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(Helper.GOOGLE);
        String secondWindow = driver.getWindowHandle();
        Thread.sleep(3000);
        driver.switchTo().window(firstWindow);
        driver.get(Helper.GOOGLE);
        driver.close();
    }

    @Test
    public static void testTen() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.HERO);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Checkboxes"))).click();
        if (driver.getCurrentUrl().equals(Helper.HEROCHECKBOXES)){
            System.out.println("----------right URL-----------");
            WebElement checkbox1 =
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"checkboxes\"]/input[1]")));
            WebElement checkbox2 =
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"checkboxes\"]/input[2]")));
            Thread.sleep(3000);
            checkbox1.click();
            checkbox2.click();
            Thread.sleep(3000);
            checkbox1.click();
            checkbox2.click();
            System.out.println("checkbox1 is selected = " + checkbox1.isSelected());
            System.out.println("checkbox2 is selected = " + checkbox2.isSelected());
        }else{
            System.out.println("wrong URL");
        }
    }

    @Test
    public static void testEleven(){
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.SAUCE);

        System.out.println(driver.getTitle());

        WebElement acceptedText = driver.findElement(By.xpath("//*[@id=\"login_credentials\"]/h4"));
        String text = acceptedText.getText();
        System.out.println(text);

        System.out.println(driver.findElement(By.xpath("//*[@id=\"login_credentials\"]/h4")).getText());
    }

    @Test
    public static void testTwelve() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(Helper.BASICCALC);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.NUMBER1FIELD))).sendKeys("3");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.NUMBER2FIELD))).sendKeys("2");
        Select dropdown = new Select(driver.findElement(By.id(Helper.DROPDOWN)));
        dropdown.selectByValue("0");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.CAlCBTN))).click();
        WebElement resultField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.RESULTFIELD)));
        System.out.println("this is the result: " + resultField.getAttribute("value"));
    }



}
