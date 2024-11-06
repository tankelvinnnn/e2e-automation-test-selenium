package com.selenium.locators.payment;

import org.openqa.selenium.By;

public class PaymentPrivateLocators {
    public static By selectPackageButton = By.xpath("//p[contains(@class,'tx-pkg-name') and text()='English Discovery']/following-sibling::div//a[@class='btn-choose-pkg']");
    public static By createAccountRedirectLink = By.xpath("//span[contains(@class, 'register-link') and contains(text(), 'Buat Akun di Sini')]");
    public static By createAccountNameField = By.xpath("//input[@formcontrolname='user_fullname']");
    public static By createAccountPhoneField = By.xpath("//input[@formcontrolname='user_phone']");
    public static By createAccountEmailField = By.xpath("//input[@formcontrolname='user_email']");
    public static By createAccountPasswordField = By.xpath("//input[@formcontrolname='user_password']");
    public static By NextButton = By.xpath("//button[contains(@class, 'button-submit')]");
    public static By loginAccountEmailField = By.xpath("//input[@formcontrolname='email']");
    public static By loginAccountPasswordField = By.xpath("//input[@formcontrolname='password']");
}
