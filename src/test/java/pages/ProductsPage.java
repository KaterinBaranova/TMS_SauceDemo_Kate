package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ProductsPage extends BasePage {

    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By ADD_TO_CART_BUTTON = By.cssSelector("button[id^=add-to-cart-]");
    private final static By PRODUCT_NAME_LOCATOR = By.cssSelector(".inventory_item_name");
    private final static By DESCRIPTION_LOCATOR = By.cssSelector(".inventory_item_desc");
    private final static By BACK_TO_PRODUCTS_BUTTON = By.cssSelector("button[id=back-to-products]");
    private final static By SHOPPING_CART = By.cssSelector(".shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickBackToProductsPage() {

    }

    @Override
    @Step("Product page is opened")
    public boolean isPageOpened() {
        return isElementPresent(SHOPPING_CART);
    }

    @Override
    public void selectOption(String s) {

    }

    @Override
    public List<String> getProductDetails() {
        return null;
    }

    @Override
    public List<String> getProductName() {
        return null;
    }

 /*   @Override
    @Step("Opening Product page")
    public ShoppingCartPage open() {
        waitUntilElementVisible(By.cssSelector(".peek"));
        return this;
    }*/

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='inventory_item']"));
    }

    @Step("Opening product details page")
    public void openProductDetails(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        itemContainer.findElement(PRODUCT_NAME_LOCATOR).click();
    }

    @Step("Getting product price")
    public String getProductPrice(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRICE_LOCATOR).getText();
    }

    @Step("Getting product description")
    public String getProductDescription(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(DESCRIPTION_LOCATOR).getText();
    }

    @Step("Clicking 'Add to cart' button")
    public ProductsPage clickAddToCartButton(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        itemContainer.findElement(ADD_TO_CART_BUTTON).click();
        return this;
    }

    @Step("Clicking Shopping Cart link")
    public ShoppingCartPage openShoppingCart() {
        driver.findElement(SHOPPING_CART).click();
        return new ShoppingCartPage(driver);
    }

    @Step("Clicking 'Back to products' button")
    public void clickBackToProducts() {
        driver.findElement(BACK_TO_PRODUCTS_BUTTON).click();
    }

    public abstract List<String> getSelectOption();
}
