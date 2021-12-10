package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import java.util.List;

public abstract class CheckoutPage extends BasePage {
    
    private static final By FIRSTNAME = By.id("first-name");
    private static final By LASTNAME = By.id("last-name");
    private static final By ZIPCODE = By.cssSelector("[data-test='postalCode']");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By FINISH_BUTTON = By.id("finish");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public void confirmOrder(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME).sendKeys("firstName");
        driver.findElement(LASTNAME).sendKeys("lastName");
        driver.findElement(ZIPCODE).sendKeys("zipCode");
        driver.findElement(CONTINUE_BUTTON).click();
        driver.findElement(FINISH_BUTTON).click();
    }

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
    public List<String> getProductDetails() {
        return null;
    }

    @Override
    public List<String> getProductName() {
        return null;
    }

    public abstract void confirmOrder(String firstname);
}
