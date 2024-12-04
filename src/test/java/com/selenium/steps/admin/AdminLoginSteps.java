package com.selenium.steps.admin;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.common.ActionEngine;
import static com.selenium.locators.admin.AdminLoginLocators.CakapLogo;
import static com.selenium.locators.admin.AdminLoginLocators.alertMessage;
import static com.selenium.locators.admin.AdminLoginLocators.googleButton;
import static com.selenium.locators.admin.AdminLoginLocators.googleEmailField;
import static com.selenium.locators.admin.AdminLoginLocators.googlePasswordField;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminLoginSteps extends ActionEngine {

    @Given("access admin dashboard page")
    public void accessAdminDashboard() throws Throwable {
        navigateTo("https://admin-staging.cakap.com/");
    }

    @When("user click on continue with google button")
    public void clickOnGoogleBtn() throws Throwable {
        waitForMilliseconds(3000);
        click(googleButton, "Google Button");
        waitForMilliseconds(3000);
        switchToNewWindow();
    }

    @And("user insert the admin credentials with {string} and {string}")
    public void enterGmailLoginDetails(String emailId, String password) throws Throwable {
        waitForMilliseconds(1000);
        slowType(googleEmailField, emailId, "Email Field");
        sendKeyAction("enter");
        slowType(googlePasswordField, password, "Password Field");
        sendKeyAction("enter");
        switchToMainWindow();
    }
    
    @Then("user should be able to see admin dashboard display")
    public void checkOnCakapLogo() throws Throwable {
        waitForMilliseconds(3000);
        WebElement cakapLogoElement = waitForElementToBeVisible(CakapLogo, "Cakap Logo");
        Assert.assertTrue(cakapLogoElement.isDisplayed(), "Cakap Logo is not displayed");

        String actualSrc = cakapLogoElement.getAttribute("src");
        Assert.assertTrue(actualSrc.endsWith("/assets/img/squline-logo.png"),"Logo src attribute does not match!");

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://admin-staging.cakap.com/profile");
    }
    
    @Then("user should be see Account not register message")
    public void checkAlertMessage() throws Throwable {
        waitForElementToBeVisible(alertMessage, "Error message");
    }

    @Before("@CakapAdmin and @AdminLogin")
    public void browserClear() throws Throwable {
        clearCookies();
    }

    // @After("@CakapAdmin and @AdminLogin")
    // public void clearBrowserStorage() throws Throwable {
    //     waitForMilliseconds(2000);
    //     navigateTo("chrome://settings/clearBrowserData");
    //     waitForMilliseconds(2000);
    //     sendKeyAction("tab");
    //     sendKeyAction("enter");
    //     System.out.println("Browser has been cleared");
    // }
}
