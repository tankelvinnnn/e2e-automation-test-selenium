package com.selenium.common;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestEngine {
    
    public static String browserName = null;
    public static RemoteWebDriver driver = null;
    public static String baseUrl = null;
    
    public static void setupSuite(String browser){
        browserName = browser;

        switch (browserName.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(getEdgeOptions());
            }
            default -> {
                System.out.println("Please provide a valid browser name");
                throw new AssertionError();
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println("Launching Test Engine: "+ browserName+ "...................");
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadTimeout(Duration.ofSeconds(30));
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        // options.addArguments("--headless=new");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions(); 
        options.setPageLoadTimeout(Duration.ofSeconds(30));
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        options.setAcceptInsecureCerts(true);
        // options.addArguments("--headless=new");
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadTimeout(Duration.ofSeconds(30));
        options.setImplicitWaitTimeout(Duration.ofSeconds(30));
        options.setAcceptInsecureCerts(true);
        // options.addArguments("--headless=new");
        return options;
    }

    public static void clearBrowser(){
        driver.manage().deleteAllCookies();
        driver.quit();
        System.out.println("Test Engine has been stopped");
    }

    public static void getUrl(ITestContext context) {
        // Get base URL from ITestContext
        baseUrl = context.getCurrentXmlTest().getParameter("base_url");
        // System.out.println("Base url: "+baseUrl);
    }
}
