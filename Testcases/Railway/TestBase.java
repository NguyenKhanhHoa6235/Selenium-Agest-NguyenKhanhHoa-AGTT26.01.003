package Railway;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Class.BookTicket;
import Class.User;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
import Guerillamai.GuerrillaMailPage;

public class TestBase {
	
	@Parameters("browser")
	@BeforeMethod
    public void beforeMethod(@Optional("chrome") String Browser) {
        System.out.println("Pre-condition");   
        String runBrowser = System.getProperty("browser", Browser);
        
        if("chrome".equalsIgnoreCase(runBrowser)) {
        		Constant.WEBDRIVER = new ChromeDriver();
        }
        else if("firefox".equalsIgnoreCase(runBrowser)) {
        		Constant.WEBDRIVER = new FirefoxDriver();
        }
        else {
        		throw new RuntimeException("Unsupported browser: " + runBrowser);
        }
        
        Constant.WEBDRIVER.manage().window().maximize();      
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
	
    public String registerWithEmailGuerrilla(String password, String pid) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return date.format(formatter);
    }
    
    public static String getToday() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return date.format(formatter);
    }
    
    public static String getAddDaysToDate(String date, int daysToAdd, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDate newDate = localDate.plusDays(daysToAdd);
        return newDate.format(formatter);
    }
    
    public static void handleLogin(User userAccount) {
    		HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
    }
    
    public static TicketBookedPage handleBookTicket(User userAccount, BookTicket bookTicket) {  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        BookTicketPage bookTicketPage = homePage.gotoPage(MenuItem.BOOK_TICKET, BookTicketPage.class);

        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(
        		bookTicket.getDepartDate(), 
        		bookTicket.getDepartStation(), 
        		bookTicket.getArrive(), 
        		bookTicket.getSeatType(), 
        		bookTicket.getTicketAmount()
        	);
        
        return ticketBookedPage;
    }

}
