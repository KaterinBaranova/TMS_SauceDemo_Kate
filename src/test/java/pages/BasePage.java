package pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class BasePage {
    WebDriver driver;

public BasePage(WebDriver driver){
        this.driver = driver;
    }
    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();

    }

    public abstract void clickBackToProductsPage();

    public abstract void clickBackToProducts();

    public abstract Object isPageOpened();

    public abstract void selectOption(String s);


    public abstract List<String> getProductDetails();

    public abstract List<String> getProductName();
}
