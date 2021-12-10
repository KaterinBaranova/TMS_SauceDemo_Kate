package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ProductsPage;
import pages.ShoppingCartPage;

import java.util.List;

public class CheckoutTest extends BaseTest {

    private final static String BACKPACK_ITEM_NAME = "Sauce Labs Backpack";
    private final static String FIRSTNAME = "Kate";
    private final static String LASTNAME = "Smith";
    private final static String ZIPCODE = "12345";

    protected ProductsPage productsPage;
    private ShoppingCartPage shoppingcartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void navigate() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage = new ProductsPage(driver) {
            @Override
            public boolean isPageOpened() {
                return Boolean.parseBoolean(null);
            }

            @Override
            public List<String> getProductName() {
                return null;
            }

            @Override
            public ShoppingCartPage open() {
                return null;
            }

            @Override
            public List<String> getSelectOption() {
                return null;
            }

            @Override
            public void clickBackToProductsPage() {

            }

            @Override
            public void clickBackToProducts() {

            }
        };
        shoppingcartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver) {
            @Override
            public ShoppingCartPage open() {
                return null;
            }

            @Override
            public void confirmOrder(String Kate, String Smith, String zipCode) {
                super.confirmOrder(Kate, Smith, "100200");
            }

            @Override
            public void confirmOrder(String firstname) {

            }
        };

    }


    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    @Test (enabled = false)
    public void checkOutTest() {
        productsPage.openProductDetails(BACKPACK_ITEM_NAME);
        productsPage.clickAddToCartButton(BACKPACK_ITEM_NAME);
        shoppingcartPage.openShoppingCart();
        shoppingcartPage.clickCheckoutButton();
        /*checkoutPage.confirmOrder(FIRSTNAME);
        checkoutPage.confirmOrder(LASTNAME);
        checkoutPage.confirmOrder(ZIPCODE);*/ //не срабвтывет
        WebElement firstName = driver.findElement(By.cssSelector("#first-name"));
        firstName.sendKeys("Kate");
        WebElement lastName = driver.findElement(By.cssSelector("[data-test=lastName]"));
        lastName.sendKeys("Smith");
        WebElement postalCode = driver.findElement(By.cssSelector("#postal-code"));
        postalCode.sendKeys("100200");
        WebElement continueButton = driver.findElement(By.cssSelector("#continue"));
        continueButton.click();
        WebElement finishButton = driver.findElement(By.cssSelector("#finish"));
        finishButton.click();
        WebElement thanksMessage = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
        Assert.assertTrue(thanksMessage.isDisplayed());

    }
}
