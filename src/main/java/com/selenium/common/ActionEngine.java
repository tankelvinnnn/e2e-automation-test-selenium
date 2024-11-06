package com.selenium.common;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ActionEngine extends TestEngine {
	
	static WebElement element = null;
	public static WebDriverWait wait = null;
    public static boolean isSuccess = false;
	public static String mainWindowHandle;
	public static String extractedData;

	public static WebElement waitForElementToBeVisible(By locator, String locatorName) throws Throwable {
		boolean isLocated = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if(element != null && element.isDisplayed()){
				isLocated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isLocated, "Failed to locate element: " +  locator.toString());
		}
		return element;
	}

	public static WebElement waitForElementToBeClickable(By locator, String locatorName) throws Throwable {
		boolean isClickable = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			if(element != null && element.isEnabled()){
				isClickable = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isClickable, "Element" +  locator.toString() + "can't be clicked");
		}
		return element;
	}

	public static void waitForElementPresent(By locator, String locatorName)throws Throwable {
		isSuccess = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (element!=null) {
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "waitForElementPresent : Falied to locate element " + locator);
		}
	}

	public static void navigateTo(String url) throws Throwable {
		driver.navigate().to(url);
		System.out.println("Navigated to URL: " + url);
	}

    public static boolean click(By locator, String locatorName) throws Throwable{
		isSuccess = false;
		try {
			element = waitForElementToBeClickable(locator, locatorName);
			if(element != null){
				element.click();
            	isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Failed to click on " + locatorName);
		}
		return isSuccess;
    }

	public static boolean type(By locator, String data, String locatorName) throws Throwable {
		isSuccess = false;
		try {
			element = waitForElementToBeVisible(locator, locatorName);
			// element = driver.findElement(locator);
			if(element != null){
				element.clear();
				element.sendKeys(data);
            	isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess,"Data typing action is not perform on " + locatorName);
		}
		return isSuccess;
	}

	public static boolean slowType(By locator, String data, String locatorName) throws Throwable {
		isSuccess = false;
		Random random = new Random();
		try {
			// element = driver.findElement(locator);
			element = waitForElementToBeVisible(locator, locatorName);
			if(element != null){
				element.clear();
				for (char character : data.toCharArray()) {
					element.sendKeys(String.valueOf(character)); // Send each character to the element
					int delayMillis = 50 + random.nextInt(101); // 50 + [0, 100]
					Thread.sleep(delayMillis); // Sleep for a random delay
				}
            	isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Data typing action is not perform on " + locatorName);
		}
		return isSuccess;
	}

	public static void switchWindowByTitle(String windowTitle, int count) throws Throwable {
		isSuccess = false;
		try {
			Set<String> allWindowsHandles = driver.getWindowHandles();
			if (allWindowsHandles.size() > 1) {
				driver.switchTo().window(windowTitle);
				isSuccess = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Failed to swicthed window with title: " + windowTitle);
		}
	}

	public static void switchToNewWindow() {
		isSuccess = false;
		try {
			mainWindowHandle = driver.getWindowHandle();
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String ChildWindow : allWindowHandles) {
				if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
					driver.switchTo().window(ChildWindow);
					isSuccess = true;
                    break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess,"Failed to switch to new windows");
		}
        
    }

	public static void switchToMainWindow() throws Throwable {
		isSuccess = false;
		try {
			driver.switchTo().window(mainWindowHandle);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Failed to swicth to main window");
		}
	}

	public static boolean selectOptionByValue(By locator, String value, String locatorName) throws Throwable {
		isSuccess = false;
		try {
			Select select = new Select(driver.findElement(locator));
			select.selectByValue(value);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess,"Failed to select option with value: " + value + " in " + locatorName);
		}
		return isSuccess;
	}

	public static void refreshPage() throws Throwable {
		driver.navigate().refresh();
	}

	public static void clearBrowserData(){
		switch (browserName.toLowerCase()) {
			case "chrome" -> {
                            driver.get("chrome://settings/clearBrowserData");
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            String script = "return document.querySelector('settings-ui').shadowRoot.querySelector('settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot.querySelector('settings-section > settings-privacy-page').shadowRoot.querySelector('settings-clear-browsing-data-dialog').shadowRoot.querySelector('#clearBrowsingDataDialog').querySelector('div[slot=\"button-container\"]').querySelector('cr-button#clearButton')";
                            WebElement clearData = (WebElement)js.executeScript(script);
                            clearData.click();
                }
			case "firefox" -> {
                            driver.get("about:preferences#privacy");
                            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clearSiteDataButton")));
                            driver.findElement(By.id("clearSiteDataButton")).click();
                }
			case "edge" -> {
                            driver.get("edge://settings/clearBrowserData");
                            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//settings-ui//settings-clear-browsing-data-dialog")));
                            driver.findElement(By.xpath("//settings-clear-browsing-data-dialog//cr-button[@id='clearBrowsingDataConfirm']")).click();
                }
		}
	}

	public static void clearBrowserDataFirefox() throws Throwable{
		driver.get("about:preferences#privacy");
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clearSiteDataButton")));
		driver.findElement(By.id("clearSiteDataButton")).click();
		Thread.sleep(5000);
	}

	public static void clearBrowserDataEdge() throws Throwable{
		driver.get("edge://settings/clearBrowserData");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//settings-ui//settings-clear-browsing-data-dialog")));
		driver.findElement(By.xpath("//settings-clear-browsing-data-dialog//cr-button[@id='clearBrowsingDataConfirm']")).click();
		Thread.sleep(5000);
	}

	public static void sendKeyAction(String action) throws Throwable {
		Actions actions = new Actions(driver);
		try{
			switch (action.toLowerCase()) {
				case "enter":
					actions.sendKeys(Keys.ENTER).perform();
					isSuccess = true;
					break;
	
				case "tab":
					actions.sendKeys(Keys.TAB).perform();
					isSuccess = true;
					break;
	
				case "space":
					actions.sendKeys(Keys.SPACE).perform();
					isSuccess = true;
					break;
	
				case "backspace":
					actions.sendKeys(Keys.BACK_SPACE).perform();
					isSuccess = true;
					break;
	
				case "esc":
				case "escape":
					actions.sendKeys(Keys.ESCAPE).perform();
					isSuccess = true;
					break;
	
				case "arrow_up":
					actions.sendKeys(Keys.ARROW_UP).perform();
					isSuccess = true;
					break;
	
				case "arrow_down":
					actions.sendKeys(Keys.ARROW_DOWN).perform();
					isSuccess = true;
					break;
	
				case "arrow_left":
					actions.sendKeys(Keys.ARROW_LEFT).perform();
					isSuccess = true;
					break;
	
				case "arrow_right":
					actions.sendKeys(Keys.ARROW_RIGHT).perform();
					isSuccess = true;
					break;
	
				default:
					isSuccess = false;
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Failed to send action key "+ action);
		}
	}

    public static void clearCookies() {
		driver.manage().deleteAllCookies();
	}

	public static void clearPageData() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Clear local storage data for the page
        js.executeScript("window.localStorage.clear();");
        // Clear session storage data for the page
        js.executeScript("window.sessionStorage.clear();");
        // Refresh the page to apply changes (optional)
        driver.navigate().refresh();
    }

	public static void waitForMilliseconds(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void screenShot(String fileName) throws Throwable {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(fileName));
			isSuccess =  true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Assert.assertTrue(isSuccess, "Unable to get screenshot");
		}
	}

	public static String listeningNetwork(String endpoint, String jsonObject){

		switch(browserName.toLowerCase()){
			case "chrome" ->{
				DevTools devTools = ((ChromeDriver)driver).getDevTools();
				devTools.createSession();

				devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
				
				String apiUrl = endpoint;

				devTools.addListener(Network.responseReceived(), response -> {
					Response res = response.getResponse();
					String url = res.getUrl();
					
					// Jika URL cocok dengan API yang dicari, cetak response
					if (url.equals(apiUrl)) {
						// System.out.println(url);
						// System.out.println("Intercepted API Response from: " + url);
						// System.out.println("Status Code: " + res.getStatus());

						Network.GetResponseBodyResponse bodyResponse = devTools.send(Network.getResponseBody(response.getRequestId()));
						String responseBody = bodyResponse.getBody();

						// System.out.println("Response Body: " + responseBody);

						JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);
						// System.out.println("Parsed JSON: " + json);

						extractedData = json.getAsJsonObject("data").get(jsonObject).getAsString();
						// System.out.println("Extracted Data: "+extractedData);
					}
				});
			}
			case "edge" -> {
				DevTools devTools = ((EdgeDriver)driver).getDevTools();
				devTools.createSession();

				devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
				
				String apiUrl = endpoint;

				devTools.addListener(Network.responseReceived(), response -> {
					Response res = response.getResponse();
					String url = res.getUrl();
		
					// Jika URL cocok dengan API yang dicari, cetak response
					if (url.equals(apiUrl)) {
						// System.out.println(url);
						// System.out.println("Intercepted API Response from: " + url);
						// System.out.println("Status Code: " + res.getStatus());

						Network.GetResponseBodyResponse bodyResponse = devTools.send(Network.getResponseBody(response.getRequestId()));
						String responseBody = bodyResponse.getBody();

						// System.out.println("Response Body: " + responseBody);

						JsonObject json = new Gson().fromJson(responseBody, JsonObject.class);
						// System.out.println("Parsed JSON: " + json);

						extractedData = json.getAsJsonObject("data").get(jsonObject).getAsString();
						// System.out.println("Extracted Data: "+extractedData);
					}
				});
			}
			default -> throw new IllegalArgumentException("Invalid browser: " + browserName+". Currently only support for Chrome and Edge.");
		}		
		return extractedData;
   }
}