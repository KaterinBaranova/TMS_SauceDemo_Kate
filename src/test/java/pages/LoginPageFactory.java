package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class LoginPageFactory extends BasePage {

    private final static String URL = "https://www.saucedemo.com/";

    @FindBy (id="user-name")
    private WebElement usernameInput;

    @FindBy (id="password")
    private WebElement passwordInput;

    @FindBy(id="login-button")
    private WebElement loginButton;


    @FindBy(css="[data-test=error]")
    private WebElement errorMessage;

    public LoginPageFactory(WebDriver driver){
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }


    public String getErrorMessageText() {
        return driver.findElement((By) errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement((By) errorMessage).isDisplayed();
    }

    public void login(String username, String password) {
        driver.findElement((By) usernameInput).sendKeys(username);
        driver.findElement((By) passwordInput).sendKeys(password);
        driver.findElement((By) loginButton).click();
    }

    public abstract void clickBackToProductsPage();

    public abstract void clickBackToProducts();

/*
    @Override
    public boolean isPageOpened();{
        return false;
    }
*/

    public abstract List<String> getProductName();
}


