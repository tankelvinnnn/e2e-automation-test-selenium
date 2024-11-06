package com.selenium.helpers.admin;

import com.selenium.common.ActionEngine;
import static com.selenium.locators.admin.AdminLoginLocators.CakapLogo;
import static com.selenium.locators.admin.AdminLoginLocators.alertMessage;
import static com.selenium.locators.admin.AdminLoginLocators.emailField;
import static com.selenium.locators.admin.AdminLoginLocators.googleButton;
import static com.selenium.locators.admin.AdminLoginLocators.logoutBtn;
import static com.selenium.locators.admin.AdminLoginLocators.passwordField;
import static com.selenium.locators.admin.AdminLoginLocators.profileBtn;
import static com.selenium.locators.admin.AdminLoginLocators.yesBtn;

public class AdminLoginHelper extends ActionEngine {

    public static void accessAdminDashboard() throws Throwable {
        navigateTo(baseUrl);
    }

    public static void clickOnGoogleBtn() throws Throwable {
        waitForMilliseconds(5000);
        click(googleButton, "Google Button");
    }

    public static void enterGmailLoginDetails(String emailId, String pass) throws Throwable {
        switchToNewWindow();
        waitForMilliseconds(1000);
        slowType(emailField, emailId, "Email Field");
        sendKeyAction("enter");
        slowType(passwordField, pass, "Password Field");
        sendKeyAction("enter");
        switchToMainWindow();
    }

    public static void checkOnCakapLogo() throws Throwable {
        waitForMilliseconds(3000);
        waitForElementPresent(CakapLogo, "Cakap Logo");
    }

    public static void checkAlertMessage() throws Throwable {
        waitForElementToBeVisible(alertMessage, "Error message");
    }

    public static void clickOnAdminProfile() throws Throwable {
        click(profileBtn, "profileBtn");
    }

    public static void clickOnAdminLogout() throws Throwable {
        click(logoutBtn, "logoutBtn");
        click(yesBtn, "yesBtn");
    }
}
