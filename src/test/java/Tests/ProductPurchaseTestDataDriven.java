package Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductsPage;
import resources.ExcelUtils;
import java.io.IOException;

public class ProductPurchaseTestDataDriven extends AbstractTest {

    @Test(dataProvider = "productData")
    public void productPurchaseTest(String testCaseNo, String browser, String searchProduct, String productName, String internalMemory, String version) throws InterruptedException {
        driver = initializeDriver(browser);
        this.driver.manage().window().maximize();

        HomePage homepage = new HomePage(driver);
        homepage.goTo("https://www.noon.com/uae-en/");
        Assert.assertTrue(homepage.isAt());
        homepage.searchProductBar(searchProduct);
        homepage.selectProduct(productName);

        ProductsPage product = new ProductsPage(driver);
        product.getProductDetails();
        product.selectSpecification(internalMemory, version);

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

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}
