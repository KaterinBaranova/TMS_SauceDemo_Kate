package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

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
    }

    @Test
    public void loginPositiveTest() {
        loginPage.login(USERNAME, PASSWORD);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentPageUrl(), expectedUrl);
    }

    @Test
    public void loginWithEmptyUsernameTest() {
        String expected_error_message = "Epic sadface: Username is required";
        loginPage.login("", PASSWORD);
        Assert.assertEquals(loginPage.getCurrentPageUrl(), LOGIN_PAGE_URL);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);
    }

    @Test
    public void loginWithEmptyPasswordTest() {
        String expected_error_message = "Epic sadface: Password is required";
        String currentPageUrl = loginPage.getCurrentPageUrl();
        loginPage.login(USERNAME, "");
        Assert.assertEquals(loginPage.getCurrentPageUrl(), currentPageUrl);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);
    }

    @Test
    public void loginWithInvalidFieldsTest() {
        String expected_error_message = "Epic sadface: Username and password do not match any user in this service";
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertEquals(loginPage.getCurrentPageUrl(), LOGIN_PAGE_URL);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expected_error_message);
    }
}