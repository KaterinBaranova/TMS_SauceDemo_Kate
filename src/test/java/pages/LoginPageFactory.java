package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.google.gson.internal.bind.TypeAdapters.URL;

public class LoginPageFactory extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(css = "[data-test=er ror]")
    private WebElement errorMessage;


    public LoginPageFactory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void clickBackToProductsPage() {

    }

    @Override
    public void clickBackToProducts() {

    }

    @Override
    public boolean isPageOpened() {
        return false;
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

    @Override
    public ShoppingCartPage open() {
        return null;
    }

/*    @Override
    public ShoppingCartPage open() {
        driver.get(String.valueOf(URL));
        return new LoginPage(driver) {
            @Override
            public void clickBackToProductsPage() {

            }

            @Override
            public void clickBackToProducts() {

            }

            @Override
            public boolean isPageOpened() {
                return false;
            }

            @Override
            public List<String> getProductName() {
                return null;
            }

            @Override
            public void selectOption(String s) {

            }

            @Override
            public List<String> getProductDetails() {
                return null;
            }
        };
    }*/

    @Step("Login to Saucedemo.com with username {username} and password {password}")
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        waitForPasswordInputClickable();
        passwordInput.sendKeys(password);
        waitUntilElementClickable(loginButton);
        loginButton.click();
    }

    public void waitForPasswordInputClickable() {
        waitUntilElementClickable(passwordInput);
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

}