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

import static pages.CheckoutPage.CONTINUE_BUTTON;
import static pages.CheckoutPage.FINISH_BUTTON;


public class CheckoutTests extends BaseTest {

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
            public Object isPageOpened() {
                return null;
            }

            @Override
            public List<String> getProductName() {
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


    }


    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    @Test
    public void checkOutTest() {
        productsPage.openProductDetails(BACKPACK_ITEM_NAME);
        productsPage.clickAddToCartButton(BACKPACK_ITEM_NAME);
        shoppingcartPage.openShoppingCart();
        checkoutPage.confirmOrder (FIRSTNAME);
        checkoutPage.confirmOrder (LASTNAME);
        checkoutPage.confirmOrder (ZIPCODE);
        CheckoutPage.clickconfirmOrder(CONTINUE_BUTTON);
        CheckoutPage.clickconfirmOrder(FINISH_BUTTON);
        WebElement thanksMessage = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
        Assert.assertTrue(thanksMessage.isDisplayed());
    }
}