package Railway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    //GuerrillaMail + get email
    GuerrillaMailPage mailPage = new GuerrillaMailPage();
    mailPage.open();
    closeAdsIfPresent();
    String emailName = Utilities.generateTimestampEmail();
    
    mailPage.setEmailName(emailName);
    String fullEmailAdrress = mailPage.getCreatedEmail();
    
    System.out.println(fullEmailAdrress);
    
	//Railway + click on link "create an account" + register
	HomePage homePage = new HomePage();
	homePage.open();	
	RegisterPage registerPage = homePage.ClicklinkCreateAccount();
    registerPage.register(fullEmailAdrress, password, pid);

    // back GuerrillaMail + confirmation email
    mailPage.open();
    closeAdsIfPresent();
    mailPage.setEmailName(emailName);
    mailPage.openFirstMail();

    //Switch to Railway
    	Utilities.switchToNewWindow();
 
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
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
    }
    
    public static TicketBookedPage handleLoginAndBookTicket(User userAccount, BookTicket bookTicket) {  
        HomePage homePage = new HomePage();
        homePage.open();
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
    
    public static TicketBookedPage handleBookTicket(BookTicket bookTicket) {  
        HomePage homePage = new HomePage();      
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
    
    public static void closeAdsIfPresent() {
        Constant.WEBDRIVER.switchTo().defaultContent();
        List<WebElement> iframes = Constant.WEBDRIVER.findElements(By.tagName("iframe"));

        for (WebElement frame : iframes) {
            try {
                Constant.WEBDRIVER.switchTo().frame(frame);
                List<WebElement> closeButtons = Constant.WEBDRIVER.findElements(By.xpath("//*[contains(@id,'dismiss') or contains(@class,'close')]"));
                if (!closeButtons.isEmpty()) {
                    closeButtons.get(0).click();
                    break;
                }
                
                Constant.WEBDRIVER.switchTo().defaultContent();

            } catch (Exception e) {
                Constant.WEBDRIVER.switchTo().defaultContent();
            }
        }
    }

}
