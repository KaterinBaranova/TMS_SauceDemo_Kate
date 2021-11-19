package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ProductsPage extends BasePage {
    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By ADD_TO_CART_BUTTON = By.cssSelector("button[id^=add-to-cart-]");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public abstract Object isPageOpened();

    @Override
    public void selectOption(String s) {

    }

    @Override
    public List<String> getProductDetails() {
        return null;
    }

    public void openProductDetails(String productName) {

    }

    public String getProductPrice(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRICE_LOCATOR).getText();
    }

    public void clickAddToCartButton(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        itemContainer.findElement(ADD_TO_CART_BUTTON).click();
    }

    public String getProductDescription(String productName) {
        return null;

    }

    public int getProductsCount() {
        return 1;
    }

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='inventory_item']"));
    }

    public abstract List<String> getProductName();
}
