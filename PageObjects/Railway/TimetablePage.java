package Railway;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TimetablePage extends GeneralPage{
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
	    	By _linkCheckPrice = By.xpath(
	    		    "//table//tr[" +
	    		    "td[normalize-space()='" + departStation + "']" +
	    		    "/following-sibling::td[normalize-space()='" + arriveStation + "']" +
	    		"]//a[normalize-space()='check price']"

	    		);
	    	Utilities.scrollToBottomPage();
    		WebElement checkPrice = Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_linkCheckPrice));
    		checkPrice.click();
    		
    		return new TicketPrice();
    }
    
    public BookTicketPage clickBookTicket(String departStation, String arriveStation){
    	By _linkCheckPrice = By.xpath(
    			"//table//tr[" +
    			"td[normalize-space()='" + departStation + "']" +
    			"/following-sibling::td[normalize-space()='" + arriveStation + "']" +
    			"]//a[normalize-space()='book ticket']"

    		);
    		Utilities.scrollToBottomPage();
		WebElement checkPrice = Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_linkCheckPrice));
		checkPrice.click();
		
		return new BookTicketPage();
}

}
