package Railway;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TimetablePage extends GeneralPage{
	//Variables
	private static final String CHECKPRICE_LINK_XPATH =
	        "//table//tr[" +
	        "td[normalize-space()='%s']" +
	        "/following-sibling::td[normalize-space()='%s']" +
	        "]//a[normalize-space()='check price']";
	
	private static final String BOOKTICKET_LINK_XPATH =
	        "//table//tr[" +
	        "td[normalize-space()='%s']" +
	        "/following-sibling::td[normalize-space()='%s']" +
	        "]//a[normalize-space()='book ticket']";
	
	// Locators 
    private final By _lblTimetableTitle = By.xpath("//div[@id='content']//h1[@align='center']");
    private final By _ticketTable = By.xpath("//table[@class='MyTable WideTable']");
    private final By _tableHeaders = By.xpath("//table[@class='MyTable WideTable']//th");
    private final By _tableRows = By.xpath("//table[@class='MyTable WideTable']//tbody//tr");
    
    // Elements
    public List<WebElement> getHeaders() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_tableHeaders));
    }

    public List<WebElement> getRows() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_tableRows));
    }
    
    public WebElement getLblTicketBookedSuccessfullyMgs() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblTimetableTitle));
    }
    
    private WebElement getCheckPriceElement(String departStation, String arriveStation) {
    		By _linkCheckPrice = By.xpath(String.format(CHECKPRICE_LINK_XPATH, departStation, arriveStation));
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_linkCheckPrice));
    }
    
    private WebElement getBookTicketElement(String departStation, String arriveStation) {
		By _linkBookTicket = By.xpath(String.format(BOOKTICKET_LINK_XPATH, departStation, arriveStation));
		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_linkBookTicket));
    }
    
    // Methods
    public String getMessageTicketBookedSuccessfully() {
		return getLblTicketBookedSuccessfullyMgs().getText();
	}
    
    public List<Map<String, String>> getTableTimetable(){
		Utilities.waitForVisible(_lblTimetableTitle);
		Utilities.waitForVisible(_ticketTable);	
		return Utilities.convertRowsToMap(getRows(), getHeaders());
    }
    
    public TicketPrice clickCheckPrice(String departStation, String arriveStation){
	    	Utilities.scrollToBottomPage();
    		WebElement linkCheckPrice = getCheckPriceElement(departStation, arriveStation);
    		linkCheckPrice.click();  		
    		return new TicketPrice();
    }
    
    public BookTicketPage clickBookTicket(String departStation, String arriveStation) {
    		Utilities.scrollToBottomPage();
		WebElement linkBookTicket = getBookTicketElement(departStation, arriveStation);
		linkBookTicket.click();		
		return new BookTicketPage();
    }
}
