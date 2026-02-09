package Common;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class Utilities {

    public static String generateTimestampEmail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp;
    }
    
    //OPEN AND RELOAD PAGE
    public static void open(String URL) {
    		Constant.WEBDRIVER.navigate().to(URL);
    }
    
    public static void reload() {
		Constant.WEBDRIVER.navigate().refresh();
    }
    
    
    //OPEN TAG and SWITCH TAG 
    public static String openNewTab() {
        Constant.WEBDRIVER.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        return Constant.WEBDRIVER.getWindowHandle();
    }

    public static void switchToWindow(String windowHandle) {
        Constant.WEBDRIVER.switchTo().window(windowHandle);
    }
    
    public static void openToAndSwitchTag(String url) {
	    	Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);
	    	Constant.WEBDRIVER.get(url);
    }
    
    public static void switchToLastTag() {
	    	Set<String> windows = Constant.WEBDRIVER.getWindowHandles();
	    	String lastWindown = windows.toArray(new String[0])[windows.size() - 1];
	    	Constant.WEBDRIVER.switchTo().window(lastWindown);
    }
    
    public static void switchToFirstTab() {
        Set<String> windows = Constant.WEBDRIVER.getWindowHandles();
        String firstWindow = windows.toArray(new String[0])[0];
        Constant.WEBDRIVER.switchTo().window(firstWindow);
    }

    
    //WAIT
    public static By waitForVisible(By locator, int timeout){
    		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(timeout));
    		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    		return locator;
    }
    
    public static By waitForVisible(By locator){
		return waitForVisible(locator, Constant.TIMEOUT);
    }
    
    //WAIT TO CLICK
    public static void waitAndClick(By locator) {
    		WebDriverWait wait = new WebDriverWait(
    	        Constant.WEBDRIVER,
    	        Duration.ofSeconds(Constant.TIMEOUT)
    	    );

    	    WebElement element = wait.until(
    	        ExpectedConditions.elementToBeClickable(locator)
    	    );

    	    element.click();
    }
    
    //WAIT TO SENDKEY 
    public static void waitAndSenkey(By locator, String input){		
    		WebElement element = Constant.WEBDRIVER.findElement(waitForVisible(locator, Constant.TIMEOUT));
    		element.clear();
    		element.sendKeys(input);
    }
    
    
    //ALERT 
    public static void waitAlertAndAccept(By locator) {
    		WebDriverWait wait = new WebDriverWait(
    	        Constant.WEBDRIVER,
    	        Duration.ofSeconds(Constant.TIMEOUT)
    	    );

	    	wait.until(ExpectedConditions.alertIsPresent());
	
	    	Constant.WEBDRIVER.switchTo().alert().accept();
    }
    
    public static void waitAlertAndDismiss(By locator) {
    		WebDriverWait wait = new WebDriverWait(
    	        Constant.WEBDRIVER,
    	        Duration.ofSeconds(Constant.TIMEOUT)
    	    );

	    	wait.until(ExpectedConditions.alertIsPresent());
	
	    	Constant.WEBDRIVER.switchTo().alert().dismiss();
    }

    
    //SCROLL
    public static void scrollToElement(By locator){
		WebDriverWait wait = new WebDriverWait( Constant.WEBDRIVER, Duration.ofSeconds(30) ); 
		WebElement element = wait.until( ExpectedConditions.presenceOfElementLocated(locator) ); 
		
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER; 
		js.executeScript( "arguments[0].scrollIntoView({block:'center'});", element );
		
    }
    
    public static void scrollToElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
    
    //SCROLL AND CLICK
    public static void scrollAndClick(By locotor){
		scrollToElement(locotor);
		Constant.WEBDRIVER.findElement(locotor).click();
    }
    
    public static void scrollAndClick(WebElement element){
		scrollToElement(element);
		element.click();
    }
    
    //SELECT
    public static void selectByVisibleText(WebElement element, String text){
		scrollToElement(element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
    }
    
    public static void selectByVisibleText(By locator, String text){
		scrollToElement(locator);
		Select select = new Select(findElement(locator));
		select.selectByVisibleText(text);
    }
    
    public static void selectByIndex(By locator, int index){
		scrollToElement(locator);
		Select select = new Select(findElement(locator));
		select.selectByIndex(index);
    }

    
    public static String getSelectFirstOption(WebElement element) {
	    	Select select = new Select(element);
	    	return select.getFirstSelectedOption().getText();
	}
    
    public static void waitForSelectOptionsChange(By selectLocator) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));

        Select select = new Select(Constant.WEBDRIVER.findElement(selectLocator));
        List<String> oldOptions = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();

        wait.until(driver -> {
            Select newSelect = new Select(driver.findElement(selectLocator));
            List<String> newOptions = newSelect.getOptions()
                    .stream()
                    .map(WebElement::getText)
                    .toList();
            return !newOptions.equals(oldOptions);
        });
    }

    
    //
    public static void jsClick(By locator) {
        WebElement element = Constant.WEBDRIVER.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", element);
    }
    
    public static void removeAdsIframes() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
            js.executeScript(
                    "document.querySelectorAll(" +
                    "'iframe[id^=\"aswift\"], " +
                    "iframe[src*=\"doubleclick\"], " +
                    "div[id^=\"aswift\"]'" +
                    "div[id=\"ad_position_box\"]'" +
                    ").forEach(e => e.remove());"
                );
        } catch (Exception e) {
            System.out.println("Not fount Ads");
        }
    }
    
    public static void dismissAlertIfPresent() {
        try {
            Alert alert = Constant.WEBDRIVER.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present. Continue test...");
        }
    }
    
    //Find element
    public static WebElement findElement(By locator) {
        return Constant.WEBDRIVER.findElement(locator);
    }
}