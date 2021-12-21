package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ProductsPageTest extends BaseTest {

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

    @Test (description="SauceDemo sorting items from Z to A test")
    public void sortDescOrderNameZToA() {
        List<WebElement> beforeFilterName = driver.findElements(By.className("inventory_item_name"));
        List<String> beforeFilterNameList = new ArrayList<>();
        for(WebElement p : beforeFilterName) {
            beforeFilterNameList.add(String.valueOf(p.getText()));
        }
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Name (Z to A)");
        List<WebElement> afterFilterName = driver.findElements(By.className("inventory_item_name"));
        List<String> afterFilterNameList = new ArrayList<>();
        for(WebElement p : afterFilterName) {
            afterFilterNameList.add(String.valueOf(p.getText()));
        }
        Collections.sort(beforeFilterNameList);
        Collections.reverse(beforeFilterNameList);
        Assert.assertEquals(beforeFilterNameList, afterFilterNameList);
    }

    @Test (description="SauceDemo sorting items from A to Z test")
    public void sortAscOrderNameAToZ() {
        List<WebElement> beforeFilterName = driver.findElements(By.className("inventory_item_name"));
        List<String> beforeFilterNameList = new ArrayList<>();
        for(WebElement p : beforeFilterName) {
            beforeFilterNameList.add(String.valueOf(p.getText()));
        }
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Name (A to Z)");
        List<WebElement> afterFilterName = driver.findElements(By.className("inventory_item_name"));
        List<String> afterFilterNameList = new ArrayList<>();
        for(WebElement p : afterFilterName) {
            afterFilterNameList.add(String.valueOf(p.getText()));
        }
        Collections.sort(beforeFilterNameList);
        Assert.assertEquals(beforeFilterNameList, afterFilterNameList);
    }

    @Test (description="SauceDemo sorting items from Low to High test")
    public void sortAscOrderPriceLowToHigh() {
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for(WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Price (low to high)");
        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for(WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);
    }

    @Test (description="SauceDemo sorting items from High to Low test")
    public void sortDescOrderPriceHighToLow() {
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for(WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Price (high to low)");
        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for(WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);
        Collections.reverse(beforeFilterPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);
    }
}


