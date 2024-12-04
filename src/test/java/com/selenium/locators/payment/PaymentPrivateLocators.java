package com.selenium.locators.payment;

import org.openqa.selenium.By;

public class PaymentPrivateLocators {
    public static By selectPackageButton(String packageName) {
        return By.xpath("//p[contains(@class,'tx-pkg-name') and contains(text(),'"+packageName+"')]/following-sibling::div/a[@class='btn-choose-pkg']");
    }
    public static By packagePriceLabel(String packageName) {
        return By.xpath("//p[contains(@class,'tx-pkg-name') and contains(text(),'"+packageName+"')]/following-sibling::div/span/p[@class='tx-price']");
    }
    public static By selectPaymentMethodButton(String paymentMethod) {
        return By.xpath("//div[@class='payment-choice']/p[@class='tx-payment-method' and contains(text(),'"+paymentMethod+"')]");
    }
    public static By createAccountRedirectLink = By.xpath("//span[contains(@class, 'register-link') and contains(text(), 'Buat Akun di Sini')]");
    public static By createAccountNameField = By.xpath("//input[@formcontrolname='user_fullname']");
    public static By createAccountPhoneField = By.xpath("//input[@formcontrolname='user_phone']");
    public static By createAccountEmailField = By.xpath("//input[@formcontrolname='user_email']");
    public static By createAccountPasswordField = By.xpath("//input[@formcontrolname='user_password']");
    public static By NextButton = By.xpath("//button[contains(@class, 'button-submit')]");
    public static By loginAccountEmailField = By.xpath("//input[@formcontrolname='email']");
    public static By loginAccountPasswordField = By.xpath("//input[@formcontrolname='password']");
    public static By tncCheckbox = By.xpath("//div[@class='form-check-tnc']/label/span[@class='large-checkmark']");
    public static By payNowButton = By.xpath("//button[contains(text(),'Bayar Sekarang')]");
    public static By finalPriceLabel = By.xpath("//div[contains(@class,'total-payment')]/div/div/div[contains(@class,'value-total')]");
    public static By awaitingPaymentHeader = By.xpath("//div[@class='app-header']/p[text()='Menunggu Pembayaran']");
    public static By awaitingVirtualAccountNumberLabel = By.xpath("//p[@class='tx-price']");
    public static By awaitingPaymentAmountLabel = By.xpath("//p[@class='tx-amt-price']");
    public static By applyVoucherInput = By.xpath("//div[contains(@class,'voucher')]/div[contains(@class,'voucher-hover')]");
    public static By applyVoucherList = By.xpath("//div[contains(@class,'voucher-list-label')]");
    public static By applyVoucherButton = By.xpath("//button[@type='submit' and text()='Terapkan']");

}
