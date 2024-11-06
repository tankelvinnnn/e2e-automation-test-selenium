package com.selenium.scripts.admin;

import org.testng.annotations.Test;

import com.selenium.helpers.admin.AdminLoginHelper;

public class AdminLoginTest extends AdminLoginHelper{

    static String validEmail = "kelvin@cakap.com";
    static String validPassword = "Kelvin@Cakap2424";
    static String invalidEmail = "kelvin@cakap.co.id";
    static String invalidPassword = "kelvin@cakap";

    @Test(priority = 1)
    public void checkingLoginUsingValidAccount() throws Throwable {
        try {
            //TestEngine.testDescription.put(HtmlReportSupport.tc_name, "TC-01: Login Using Valid Account");
            accessAdminDashboard();
            clickOnGoogleBtn();
            enterGmailLoginDetails(validEmail, validPassword);
            checkOnCakapLogo();
            clickOnAdminProfile();
            clickOnAdminLogout();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test(priority = 2)
    // public void checkingLoginUsingInvalidAccount() throws Throwable {
    //     try {
    //         // TestEngine.testDescription.put(HtmlReportSupport.tc_name, "TC-01: Login Using Valid Account");
    //         accessAdminDashboard();
    //         clickOnGoogleBtn();
    //         enterGmailLoginDetails(invalidEmail, invalidPassword);
    //         checkAlertMessage();
    //     }
    //     catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     finally {
    //     }
    // }
    
    // @DataProvider
    // public static Object[][] getTestData () {return CommonDataProvider.getData("AdminDashboard");}
    
    // @BeforeMethod
    // public static void browserClear() throws Throwable {
    //     clearCookies();
    //     System.out.println("Cookies has been cleared");
        
    // }

    // @AfterMethod 
    // public static void clearBrowserStorage() throws Throwable {
    //     waitForMilliseconds(2000);
    //     navigateTo("chrome://settings/clearBrowserData");
    //     waitForMilliseconds(2000);
    //     sendKeyAction("tab");
    //     sendKeyAction("enter");
    // }
}
