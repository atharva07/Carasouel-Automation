package Tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {

    protected WebDriver driver;
    // settting up the driver - here we will be using chrome driver
    @BeforeTest
    public void setDriver() {
        // driver setup
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    public WebDriver setChromeLocalDriver() {
        // driver setup
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        return this.driver;
    }

    public WebDriver setFirefoxLocalDriver() {
        // driver setup
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
        return this.driver;
    }

    // quit driver
    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
