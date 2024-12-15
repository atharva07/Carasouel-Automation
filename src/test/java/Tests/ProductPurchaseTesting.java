package Tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductsPage;

public class ProductPurchaseTesting extends AbstractTest {

    @Test
    public void searchProductName() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.goTo("https://www.noon.com/uae-en/");
        this.driver.manage().window().maximize();
        Assert.assertTrue(homepage.isAt());
        homepage.searchProductBar();
    }

    @Test(dependsOnMethods = "searchProductName")
    @Parameters("productName")
    public void selectProductApple(String productName) throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.selectProduct(productName);
    }

    @Test(dependsOnMethods = "selectProductApple")
    public void getProductDetails() throws InterruptedException {
        ProductsPage product = new ProductsPage(driver);
        product.getProductDetails();
    }

    @Test(dependsOnMethods = "getProductDetails")
    @Parameters({"internalMemory","version"})
    public void selectSpecificationTest(String internalMemory, String version) throws InterruptedException {
        ProductsPage product = new ProductsPage(driver);
        product.selectSpecification(internalMemory, version);
    }

    @Test(dependsOnMethods = "selectSpecificationTest")
    public void processCheckoutPage() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.productTransaction();
    }
}