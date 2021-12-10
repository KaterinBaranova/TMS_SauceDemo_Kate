package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import pages.ProductsPage;

import java.util.List;

public class ShoppingCartTest extends BaseTest {

    private final static String CART_PAGE_URL = "https://www.saucedemo.com/cart.html";
    private final static String BACKPACK_ITEM_NAME = "Sauce Labs Backpack";
    private final static String FLEECE_JACKET_ITEM_NAME = "Sauce Labs Fleece Jacket";
    protected ProductsPage productsPage;
    protected ShoppingCartPage shoppingCartPage;


    @BeforeMethod
    public void navigate() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage = new ProductsPage(driver) {
            @Override
            public void clickBackToProductsPage() {

            }

            @Override
            public void clickBackToProducts() {

            }

            @Override
            public boolean isPageOpened() {
                return Boolean.parseBoolean(null);
            }

            @Override
            public void selectOption(String s) {

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
        };
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
    }


    @Test (description="SauceDemo positive cart test",groups = {"Smoke"})
    public void shoppingCartPositiveTest() {
        productsPage.clickAddToCartButton(BACKPACK_ITEM_NAME);
        shoppingCartPage.openShoppingCart();
        shoppingCartPage.clickContinueShoppingButton();
        productsPage.clickAddToCartButton(FLEECE_JACKET_ITEM_NAME);
        shoppingCartPage.openShoppingCart();
        shoppingCartPage.clickCheckoutButton();
        Assert.assertNotEquals(productsPage.getCurrentPageUrl(), CART_PAGE_URL);
    }
}


