import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;


public class SingUpTest {

    @Test
    public void zipCode5Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        boolean isSingUpPageOpened = driver.findElement(By.cssSelector("input[value=Register]")).isDisplayed();
        assertTrue(isSingUpPageOpened, "Страница регистрации не открылась");
        driver.close();

    }

    @Test
    public void zipCode4Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1111");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message show");
        driver.close();
    }

    @Test
    public void zipCode6Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111122");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=error_message]")).getText();
        assertEquals(actualError, "Oops, error on page. ZIP code should have 5 digits",
                "Wrong error message shown");
        driver.close();
    }

    @Test
    public void successfulSignUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("Name");
        driver.findElement(By.name("last_name")).sendKeys("Last");
        driver.findElement(By.name("email")).sendKeys("test@test.test");
        driver.findElement(By.name("password1")).sendKeys("12345678");
        driver.findElement(By.name("password2")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input[value=Register]")).click();
        String actualError = driver.findElement(By.cssSelector("span[class=confirmation_message]")).getText();
        assertEquals(actualError, "Account is created!",
                "User was not registered");
        driver.close();
    }


}
