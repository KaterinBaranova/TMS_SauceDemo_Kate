package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private final static String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private final static String INVALID_USERNAME = "user_name";
    private final static String INVALID_PASSWORD = "1234";

    @BeforeMethod
    public void navigate() {
        loginPage.open();
    }

    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void dependsOn() {
        throw new NullPointerException();
    }
    @Test(description = "SauceDemo login positive test", groups = {"Smoke"},retryAnalyzer = RetryAnalyzer.class)
    @Description(value = "Login positive test")
    public void loginPositiveTest() {
        loginPage.login(USERNAME, PASSWORD);
/*        File file = (TakesScreenshot) driver.getScreenShotAt(OutputType.FILE);
        disFile = file*/   // todo add screnshot//
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentPageUrl(), expectedUrl);
    }

    @Test(description = "SauceDemo login negative test",  groups = {"Negative"}, dataProvider = "Negative Login Test Data")
    public void loginWithEmptyUsernameTest() {
        String expected_error_message = "Epic sadface: Username is required";
        loginPage.login("", PASSWORD);
        Assert.assertEquals(loginPage.getCurrentPageUrl(), LOGIN_PAGE_URL);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);
    }

    @DataProvider(name = "Negative Login Test Data")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
        };
    }


    @Test (description = "SauceDemo login with empty fields test", invocationCount = 4, threadPoolSize = 2)
    public void loginWithEmptyPasswordTest() {
        String expected_error_message = "Epic sadface: Password is required";
        String currentPageUrl = loginPage.getCurrentPageUrl();
        loginPage.login(USERNAME, "");
        Assert.assertEquals(loginPage.getCurrentPageUrl(), currentPageUrl);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);
    }

    @Test (description = "SauceDemo login with invalid fields test")
    public void loginWithInvalidFieldsTest() {
        String expected_error_message = "Epic sadface: Username and password do not match any user in this service";
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertEquals(loginPage.getCurrentPageUrl(), LOGIN_PAGE_URL);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);

    }
}

