package com.selenium.helpers.payment;

import com.selenium.common.APIClient;
import com.selenium.common.ActionEngine;
import static com.selenium.locators.payment.PaymentPrivateLocators.NextButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountEmailField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountNameField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountPasswordField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountPhoneField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountRedirectLink;
import static com.selenium.locators.payment.PaymentPrivateLocators.loginAccountEmailField;
import static com.selenium.locators.payment.PaymentPrivateLocators.loginAccountPasswordField;
import static com.selenium.locators.payment.PaymentPrivateLocators.selectPackageButton;

import io.restassured.response.Response;

public class PaymentPrivateHelper extends ActionEngine {

    public static void accessPrivateCampaign() throws Throwable {
        navigateTo("https://pay-staging.cakap.com/course?id=5921");
        listeningNetwork("https://api-staging.cakap.com/v2/auth/login", "access_token");
    }

    public static void selectPackage() throws Throwable {
        click(selectPackageButton,"selectPackageButton");
    }

    public static void createStudentAccount() throws Throwable {
        click(createAccountRedirectLink,"createAccountRedrirectButton");
        type(createAccountNameField,"kelvin","nameField");
        type(createAccountPhoneField,"81234567890","phoneField");
        type(createAccountEmailField,"kelvin@cakap.com","emailField");
        type(createAccountPasswordField,"Cakap@456","passwordField");
        click(NextButton,"nextButton");
    }

    public static void login() throws Throwable {
        type(loginAccountEmailField,"kelvin@cakap.com","emailField");
        type(loginAccountPasswordField,"Cakap@456","passwordField");
        click(NextButton,"nextButton");
    }

    public static void selectPaymentMethod() throws Throwable {
        type(loginAccountEmailField,"kelvin@cakap.com","emailField");
        type(loginAccountPasswordField,"Cakap@456","passwordField");
        click(NextButton,"nextButton");
    }

    public static void deleteAccount() throws Throwable{
        String accessToken = extractedData;
        System.out.println("Access Token: " + accessToken);

        String apiEndpoint = "https://api-staging.cakap.com/v2/students/delete";
        String requestBody = "{" +
                                "\"deleteReasonId\": 6," +
                                "\"otherReason\": \"QA Testing Data\"," +
                                "\"platform\": \"QA automation web\"" +
                            "}";

        Response response = APIClient.sendRequestRest("DELETE",apiEndpoint,requestBody,accessToken);
        APIClient.displayResponse(response);
    }
}
