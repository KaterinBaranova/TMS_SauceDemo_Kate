package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pages.LoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    final static String USERNAME = "standard_user";
    final static String PASSWORD = "secret_sauce";

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver) {
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
            public List<String> getProductDetails() {
                return null;
            }

            @Override
            public List<String> getProductName() {
                return null;
            }
        };
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}