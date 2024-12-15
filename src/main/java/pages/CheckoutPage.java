package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import resources.AbstractPage;

public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = "//h1[@class='sc-7958a0d7-23 NqEiX']")
    private WebElement productName;

    @FindBy(xpath = "//span[@class='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "(//div[contains(text(),'Total')]/following::div)[1]")
    private WebElement totalPrice;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void productTransaction() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='sc-7958a0d7-23 NqEiX']")));
        String productname = this.productName.getText();
        System.out.println("Checkout page product name = " + productname);
        String totalAmount = this.totalPrice.getText();
        String[] total = totalAmount.split(" ");
        System.out.println("Checkout page total Amount = " + total[1]);
    }
}