package Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductsPage;
import resources.ExcelUtils;

import java.io.IOException;

public class ProductPurchaseTesting extends AbstractTest {

    @Test
    @Parameters("searchProduct")
    public void searchProductName(String searchProduct) throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.goTo("https://www.noon.com/uae-en/");
        this.driver.manage().window().maximize();
        Assert.assertTrue(homepage.isAt());
        homepage.searchProductBar(searchProduct);
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

    @DataProvider(name = "productData")
    public String [][] getData() throws IOException {
        // Get the data from Excel
        String path = "src/main/java/resources/testData.xlsx";
        ExcelUtils excelutil = new ExcelUtils(path);
        int totalRows = excelutil.getRowCount("Sheet1");
        int totalColumns = excelutil.getCellCount("Sheet1",1);

        String productData[][] = new String[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                productData[i-1][j] = excelutil.getCellData("Sheet1",i,j);
            }
        }
        return productData;
    }
}