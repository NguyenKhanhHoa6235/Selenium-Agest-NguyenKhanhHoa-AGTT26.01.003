package Railway;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Common.Utilities;
import Constant.Constant;
import Guerillamai.GuerrillaMailPage;

public class TestBase {
	@BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");      
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window();      
    }
	
	@AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
    }
	
	public String setAndGetEmailGuerrillaMail() {
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    String emailName = Utilities.generateTimestampEmail();
	    
	    mailPage.setEmailName(emailName);
	    String fullEmailAdrress = mailPage.getCreatedEmail();	
	    
	    return fullEmailAdrress;
	}
	
	
    public String registerWithEmailGuerrilla(String password, String pid)
    {
    		//tag 1: Railway + click on link "create an account"
		HomePage homePage = new HomePage();
	    homePage.open();
	    String railwayTab = Constant.WEBDRIVER.getWindowHandle();	
	    RegisterPage registerPage = homePage.ClicklinkCreateAccount();
	    
	    //tag 2: GuerrillaMail + get email
	    String guerrillaTag = Utilities.openNewTab();
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    Utilities.removeAdsIframes();
	    String emailName = Utilities.generateTimestampEmail();
	    
	    mailPage.setEmailName(emailName);
	    String fullEmailAdrress = mailPage.getCreatedEmail();
	    
	    System.out.println(fullEmailAdrress);
	    
	    // Back Railway + register
	    Utilities.switchToWindow(railwayTab);
	    registerPage.register(fullEmailAdrress, password, pid);
	
	    // back GuerrillaMail + confirmation email
	    Utilities.switchToWindow(guerrillaTag);
	    Utilities.removeAdsIframes();
	    mailPage.setEmailName(emailName);
	    mailPage.openFirstMail();
	    
	    //Switch to register Confirm
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();
	    
	    return fullEmailAdrress;	    
    }
    
	    public static String getDatePlusDays(int days) {
	        LocalDate date = LocalDate.now().plusDays(days);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	        return date.format(formatter);
	    }
	    
	    public static String getAddDaysToDate(String date, int daysToAdd, String pattern) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        LocalDate localDate = LocalDate.parse(date, formatter);
	        LocalDate newDate = localDate.plusDays(daysToAdd);
	        return newDate.format(formatter);
	    }
}
