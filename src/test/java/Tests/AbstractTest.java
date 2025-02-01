package Tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {

    protected WebDriver driver;
    // setting up the driver - here we will be using chrome driver
    public WebDriver initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    // quit driver
    public void quitDriver() {
        this.driver.quit();
    }
}
