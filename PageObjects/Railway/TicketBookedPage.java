package Railway;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TicketBookedPage extends GeneralPage{
	// Locators 
    private final By _lblTicketBookedSuccessfullyMgs = By.xpath("//div[@id='content']//h1[@align='center']");
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
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblTicketBookedSuccessfullyMgs));
    }
    
    // Methods
    public String getMessageTicketBookedSuccessfully() {
		return getLblTicketBookedSuccessfullyMgs().getText();
	}
    
    public List<Map<String, String>> getTableTicketBooked(){
    		Utilities.waitForVisible(_lblTicketBookedSuccessfullyMgs);
    		Utilities.waitForVisible(_ticketTable);	
    		return Utilities.convertRowsToMap(getRows(), getHeaders());
    }
}
