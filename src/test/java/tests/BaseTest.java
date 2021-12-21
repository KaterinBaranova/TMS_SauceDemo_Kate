package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import pages.LoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest {
    final static String USERNAME = "standard_user";
    final static String PASSWORD = "secret_sauce";

    protected WebDriver driver;
    protected LoginPage loginPage;


    @Parameters({"browser"})
    @BeforeClass(description = "Open browser", alwaysRun = true)
    @Step("Opening browser")
    public void setUp(ITestContext testContext, @Optional("chrome") String browser) {
        System.getProperty("browser");
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
        };
    }

    @AfterClass(description = "Close browser", alwaysRun = true)
    @Step("Closing browser")
    public void tearDown() {
        driver.quit();
    }
}