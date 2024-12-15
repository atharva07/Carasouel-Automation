package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.AbstractPage;
import java.time.Duration;

public class ProductsPage extends AbstractPage {

    @FindBy(xpath = "//h1[@class='sc-f35b8612-22 jBencA']")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='modelNumber']")
    private WebElement modelName;

    @FindBy(xpath = "//div[@class='priceNow']")
    private WebElement productPrice;

    @FindBy(xpath = "(//div[@class='loaderCtr inactiveLoading'])[2]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[@id='checkout-btn']")
    private WebElement checkoutButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

    public void getProductDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='sc-f35b8612-22 jBencA']")));
        String productname = this.productName.getText();
        System.out.println(productname);
        String modelname = this.modelName.getText();
        String[] model = modelname.split(":");
        System.out.println(model[1].trim());
        String price = this.productPrice.getText();
        String[] priceArr = price.split(" ");
        System.out.println(priceArr[1]);
    }

    public void selectSpecification(String internalMemory, String version) throws InterruptedException {
        //String internalMemory = "1 TB";
        //String version = "International Version";
        driver.findElement(By.xpath("//button[contains(text(),'"+internalMemory+"')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(text(),'"+version+"')]")).click();
        Thread.sleep(3000);
        this.addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkout-btn']")));
        this.checkoutButton.click();
    }
}