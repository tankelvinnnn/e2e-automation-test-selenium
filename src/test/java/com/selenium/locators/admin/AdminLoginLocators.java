package com.selenium.locators.admin;

import org.openqa.selenium.By;

public class AdminLoginLocators {
    public static By googleButton = By.xpath("//span[@id='login-normal-google']");
    public static By emailField = By.xpath("//input[@type='email' and @name='identifier']");
    public static By passwordField = By.xpath("//input[@type='password' and @name='Passwd']");
    public static By CakapLogo = By.xpath("//a[@class='navbar-brand text-lt']//span[@id='squline-logo-text']");
    public static By alertMessage = By.xpath("//div[@class='toast-error']//div[@role='alert' and contains(text(),'Account is not registered')]");
    public static By profileBtn = By.xpath("//a[@class='dropdown-toggle']//span[@class='thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm']");
    public static By logoutBtn = By.xpath("//li/a[contains(text(),'Logout')]");
    public static By yesBtn = By.xpath("//div[@class='btn-wrapper']//button[contains(text(),' YES ')]");
    // public static By nextBtn = By.xpath("//span[contains(text(),'Next')]/parent::button");
    // public static By nextBtn1 = By.xpath("//*[@id='passwordNext']/div/button/span");
    public static By loginLogo = By.xpath("//div[@class='login_logo']");
}
