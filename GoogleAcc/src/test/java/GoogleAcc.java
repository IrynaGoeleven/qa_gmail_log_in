import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GoogleAcc {

    private static final String BASE_URL = "https://accounts.google.com/";

    private WebDriver setupDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Programing\\QA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        return driver;
    }

    private String[] getCredentials() {
        String username = System.getenv("GMAIL_USERNAME");
        String password = System.getenv("GMAIL_PASSWORD");

        if (username == null || password == null) {
            throw new RuntimeException("Credentials are not set in environment variables");
        }

        return new String[]{username, password};
    }

    @Test
    public void gmailValidLogin() throws InterruptedException {
        WebDriver driver = setupDriver();
        String[] credentials = getCredentials();

        driver.findElement(By.id("identifierId")).sendKeys(credentials[0]);
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys(credentials[1]);

        driver.quit();
    }

    @Test
    public void gmailInvalidLogin() throws InterruptedException {
        WebDriver driver = setupDriver();

        driver.findElement(By.id("identifierId")).sendKeys("invalid_user");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("invalid_password");

        driver.quit();
    }
}
