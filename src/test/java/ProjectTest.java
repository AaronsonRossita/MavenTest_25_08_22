import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectTest {

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
    }

    // opens browser, closes browser
    @Test
    public static void testOne(){
//        System.setProperty(Helper.CHROMEKEY,Helper.CHROMEPATH);
        WebDriver driver = new ChromeDriver();
        driver.close();
    }

    // opens browser, goes to google, waits 3 sec, closes browser
    @Test
    public static void testTwo(){
        WebDriver driver = new ChromeDriver();
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



}
