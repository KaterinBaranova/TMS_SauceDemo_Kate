package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class LoginPage extends BasePage {

    private final static String URL = "https://www.saucedemo.com/";

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage open() {
        driver.get(URL);
        return null;
    }


    public String getErrorMessageText() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(ERROR_MESSAGE).isDisplayed();
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public abstract void clickBackToProductsPage();

    public abstract void clickBackToProducts();

    public abstract boolean isPageOpened();

    public abstract List<String> getProductName();
}

