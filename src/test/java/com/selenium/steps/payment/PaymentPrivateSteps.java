package com.selenium.steps.payment;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.selenium.common.APIClient;
import com.selenium.common.ActionEngine;
import com.selenium.common.DatabaseConnection;
import com.selenium.common.JsonDataReader;
import static com.selenium.locators.payment.PaymentPrivateLocators.NextButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.applyVoucherButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.applyVoucherInput;
import static com.selenium.locators.payment.PaymentPrivateLocators.applyVoucherList;
import static com.selenium.locators.payment.PaymentPrivateLocators.awaitingPaymentAmountLabel;
import static com.selenium.locators.payment.PaymentPrivateLocators.awaitingPaymentHeader;
import static com.selenium.locators.payment.PaymentPrivateLocators.awaitingVirtualAccountNumberLabel;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountEmailField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountNameField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountPasswordField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountPhoneField;
import static com.selenium.locators.payment.PaymentPrivateLocators.createAccountRedirectLink;
import static com.selenium.locators.payment.PaymentPrivateLocators.finalPriceLabel;
import static com.selenium.locators.payment.PaymentPrivateLocators.loginAccountEmailField;
import static com.selenium.locators.payment.PaymentPrivateLocators.loginAccountPasswordField;
import static com.selenium.locators.payment.PaymentPrivateLocators.packagePriceLabel;
import static com.selenium.locators.payment.PaymentPrivateLocators.payNowButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.selectPackageButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.selectPaymentMethodButton;
import static com.selenium.locators.payment.PaymentPrivateLocators.tncCheckbox;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class PaymentPrivateSteps extends ActionEngine {
    
    JsonDataReader jsonReader;
    static String packagePrice;
    static String finalPrice;

    @Given("access private campaign link")
    public static void accessPrivateCampaign() throws Throwable {
        navigateTo("https://pay-staging.cakap.com/course?id=5921");
        listeningBrowserNetwork("https://api-staging.cakap.com/v2/auth/login", "access_token");
    }

    @When("student select the {string} package")
    public static void selectPackage(String packageName) throws Throwable {
        click(selectPackageButton(packageName),"selectPackageButton");
        String packagePriceText = driver.findElement(packagePriceLabel(packageName)).getText();
        packagePrice = packagePriceText.replaceAll("[^\\d]", "");
        System.out.println("package price: "+packagePrice);
    }
    
    @When("create new student account")
    public void createStudentAccount() throws Throwable {
        click(createAccountRedirectLink,"createAccountRedrirectButton");

        jsonReader = new JsonDataReader("src/test/java/com/selenium/data/paymentTestData.json");
        String userName = jsonReader.getData("studentNewAccount", "userName");
        String userPhone = jsonReader.getData("studentNewAccount", "userPhone");
        String userEmail = jsonReader.getData("studentNewAccount", "userEmail");
        String userPassword = jsonReader.getData("studentNewAccount", "userPassword");

        type(createAccountNameField,userName,"nameField");
        type(createAccountPhoneField,userPhone,"phoneField");
        type(createAccountEmailField,userEmail,"emailField");
        type(createAccountPasswordField,userPassword,"passwordField");
        click(NextButton,"nextButton");
        waitForMilliseconds(3000);
    }

    @And("wait for {int} milliseconds")
    public void waiting(int milis) throws Throwable{
        waitForMilliseconds(milis);
    }

    @When("student select payment method {string}")
    public static void selectPaymentMethod(String paymentMethod) throws Throwable {
        click(selectPaymentMethodButton(paymentMethod),"selectPaymentMethodButton");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlContains("https://pay-staging.cakap.com/payment?id=5921"));
    }

    @And("check the tnc and click pay now button")
    public void tncAndPayNotButton() throws Throwable{
        click(tncCheckbox,"TermAndConditionCheckbox");
        click(payNowButton,"Paynow button");
    }

    @Then("final price should be same as package price")
    public void checkFinalPrice() throws Throwable{
        waitForMilliseconds(3000);
        String finalPriceText = driver.findElement(finalPriceLabel).getText();
        finalPrice = finalPriceText.replaceAll("[^\\d]", "");
        System.out.println("final price: "+finalPrice);
        Assert.assertEquals(finalPrice, packagePrice, "The price do not match!");
    }

    @Then("student should redirected to awaiting payment page")
    public void redirectToAwaitingPayment() throws Throwable{
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlContains("https://pay-staging.cakap.com/payment/pending"));
        String awaitingPageTitle = driver.findElement(awaitingPaymentHeader).getText();
        String awaitingVirtualAccountNumber = driver.findElement(awaitingVirtualAccountNumberLabel).getText();
        String awaitingPaymentAmount = driver.findElement(awaitingPaymentAmountLabel).getText();
        String paymentAmount = awaitingPaymentAmount.replaceAll("[^\\d]", ""); // Hasil: "200000"
        Assert.assertEquals(awaitingPageTitle, "Menunggu Pembayaran");
        Assert.assertNotNull(awaitingVirtualAccountNumber);
        Assert.assertEquals(paymentAmount, finalPrice,"The price do not match!");
    }

    public void login() throws Throwable {
        type(loginAccountEmailField,"kelvin@cakap.com","emailField");
        type(loginAccountPasswordField,"Cakap@456","passwordField");
        click(NextButton,"nextButton");
    }

    @When("student apply the voucher")
    public void applyVoucher() throws Throwable{
        click(applyVoucherInput, "ApplyVoucherSection");
        click(applyVoucherList, "SelectVoucherList");
        click(applyVoucherButton, "ApplyVoucherButtom");
        
    }

    @AfterTest
    public void deleteAccount() throws Throwable{
        String accessToken = extractedData;
        System.out.println("Access Token: " + accessToken);

        String apiEndpoint = "https://api-staging.cakap.com/v2/students/delete";
        String requestBody = "{" +
                                "\"deleteReasonId\": 6," +
                                "\"otherReason\": \"QA Testing Data\"," +
                                "\"platform\": \"QA automation web\"" +
                            "}";

        Response response = APIClient.sendRequestRestAssured("DELETE",apiEndpoint,requestBody,accessToken);
        APIClient.displayResponse(response);
        System.out.println("account has been deleted");
    }

    @AfterTest
    public void clearSession() throws Throwable{
        driver.manage().deleteAllCookies();
        System.out.println("session cleared");
    }

    @BeforeTest
    public void extendVoucherExpired() throws SQLException{
        DatabaseConnection.openConnection();
        DatabaseConnection.executeQuery("UPDATE `squline-dashboard`.tbl_voucher SET active_until = DATE_ADD(NOW(), INTERVAL 1 DAY) WHERE voucher_code='KELVINVOUCHER';");
        DatabaseConnection.closeConnection();
    }
}
