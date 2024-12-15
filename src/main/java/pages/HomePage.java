package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import resources.AbstractPage;

import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//h2[contains(text(),'Recommended for you')]")
    private WebElement recommeded;

    @FindBy(xpath = "//a[@id='btn_logo']")
    private WebElement logo;

    @FindBy(xpath = "(//h2[contains(text(),'Recommended for you')]/parent::div/parent::div/following::div/child::div)[1]/following::div")
    private WebElement recommendedSlider;

    @FindBy(xpath = "//input[@id='searchBar']")
    private WebElement searchBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.logo));
        return this.logo.isDisplayed();
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void printProducts() throws InterruptedException {
        Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)", "");
        Thread.sleep(5000);
        List<WebElement> recomProducts =
                driver.findElements(By.xpath("(//h2[contains(text(),'Recommended for you')]/parent::div/parent::div/following::div/child::div/child::div)[1]/child::div"));

        if (this.recommeded.getText().equals("Recommended for you")) {
            System.out.println("We are inside the recommeded loop");
            System.out.println(recomProducts.size());

            for (int i = 1; i <= recomProducts.size(); i++) {
                WebElement prductName = driver.findElement(By.xpath("((//h2[contains(text(),'Recommended for you')]/parent::div/parent::div/following::div/child::div/child::div)[1]/child::div)["+i+"]/child::div/child::div/child::a/child::div/child::div[1]/child::div[2]/child::div"));
                System.out.println(prductName.getText());
                System.out.println("-------------------------");

                if (i % 6 == 0) {
                    System.out.println("Pressing slider button...");
                    recommendedSlider.click();
                    Thread.sleep(2000);
                }
            }
        }
    }

    public void searchProductBar() throws InterruptedException {
        Thread.sleep(3000);
        this.searchBar.sendKeys("Iphone 16 pro max");
        this.searchBar.sendKeys(Keys.ENTER);
    }

    public void selectProduct(String product) throws InterruptedException {
        Thread.sleep(3000);
        //String product = "Apple iPhone 16 Pro Max 256GB Desert Titanium 5G With FaceTime - USA Version (e-SIM only) ";
        driver.findElement(By.xpath("//div[@title='"+product+"']")).click();
    }
}