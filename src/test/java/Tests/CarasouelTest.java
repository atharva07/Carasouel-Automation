package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class CarasouelTest extends AbstractTest {

    @Test
    public void runCarasouelTests() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.goTo("https://www.noon.com/uae-en/");
        this.driver.manage().window().maximize();
        Assert.assertTrue(homepage.isAt());

        homepage.printProducts();
    }
}
