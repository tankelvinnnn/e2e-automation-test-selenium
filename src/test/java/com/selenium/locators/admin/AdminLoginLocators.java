package com.selenium.locators.admin;

import org.openqa.selenium.By;

public class AdminLoginLocators {
    public static By googleButton = By.xpath("//span[@id='login-normal-google']");
    public static By googleEmailField = By.xpath("//input[@type='email' and @name='identifier']");
    public static By googlePasswordField = By.xpath("//input[@type='password' and @name='Passwd']");
    public static By CakapLogo = By.xpath("//header['#header']//img[@src='./assets/img/squline-logo.png']");
    public static By alertMessage = By.xpath("//div[contains(@class,'toast-error')]/div[@role='alert' and text()=' Account is not registered ']");
}
