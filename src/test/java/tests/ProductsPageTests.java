package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import pages.ProductsPage;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class ProductsPageTests extends BaseTest {

    private final static String BACKPACK_ITEM_NAME = "Sauce Labs Backpack";
    private final static String BACKPACK_ITEM_PRICE = "$29.99";
    private final static String BACKPACK_ITEM_DESCRIPTION = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    protected ProductsPage productsPage;
    protected ShoppingCartPage shoppingcartPage;

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
            public Object isPageOpened() {
                return null;
            }

            @Override
            public void selectOption(String s) {

            }

            @Override
            public List<String> getProductName() {
                return null;
            }
        };
        shoppingcartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    @Test
    public void addingItemToShoppingCartTest() {
        productsPage.openProductDetails(BACKPACK_ITEM_NAME);
        productsPage.clickAddToCartButton(BACKPACK_ITEM_NAME);
        shoppingcartPage.openShoppingCart();
        Assert.assertEquals(shoppingcartPage.getProductPrice(BACKPACK_ITEM_NAME), BACKPACK_ITEM_PRICE);
        Assert.assertEquals(shoppingcartPage.getProductDescription(BACKPACK_ITEM_NAME), BACKPACK_ITEM_DESCRIPTION);
    }



    public boolean waitForElementToDisappear(By element){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        try {
            wait.until((ExpectedConditions.invisibilityOfElementLocated(element)));
            return true;
        }catch (Throwable t){
            return false;
        }
    }
    @Test
    public void RemovingItemFromShoppingCart() {
        productsPage.openProductDetails(BACKPACK_ITEM_NAME);
        productsPage.clickAddToCartButton(BACKPACK_ITEM_NAME);
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeBtn.click();
        shoppingcartPage.openShoppingCart();
        By itemInShoppingCartBy = By.className("cart_item");
        Assert.assertTrue(waitForElementToDisappear(itemInShoppingCartBy));
    }

    @Test
    public void sortDescOrderNameZToA() {
        productsPage.selectOption("Name (Z to A)");
        List<String> names = productsPage.getProductName();
        assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "Sorting is not correctly performed");
        assertEquals(names.get(5), "Sauce Labs Backpack", "Sorting is not correctly performed");
    }

    @Test
    public void sortAscOrderNameAToZ() {
        productsPage.selectOption("Name (A to Z)");
        List<String> names = productsPage.getProductName();
        assertEquals(names.get(0), "Sauce Labs Backpack");
        assertEquals(names.get(5), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test
    public void sortAscOrderPriceLowToHigh() {
        productsPage.selectOption("Price (low to high)");
        List<String> names = productsPage.getProductName();
        assertEquals(names.get(0), "Sauce Labs Onesie");
        assertEquals(names.get(5), "Sauce Labs Fleece Jacket");
    }

    @Test
    public void sortDescOrderPriceHighToLow() {
        productsPage.selectOption("Price (high to low)");
        List<String> names = productsPage.getProductName();
        assertEquals(names.get(0), "Sauce Labs Fleece Jacket");
        assertEquals(names.get(5), "Sauce Labs Onesie");
    }
}


