package Railway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class TicketPrice {
	// Locators 
    private final By _lblTableHeaderMsg = By.xpath("//table//tr[@class='TableSmallHeader']//th");
    private final By _ticketPriceTable = By.xpath("//table[@class='MyTable MedTable']");
    private final By _rowSeatTypes = By.xpath("//table//tr[th[normalize-space()='Seat type']]/td");
    private final By _rowPrices = By.xpath("//table//tr[th[normalize-space()='Price (VND)']]/td");
  
    // Elements
    public WebElement getTicketPriceTable() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_ticketPriceTable));
    }
    
    public List<WebElement> getRowSeatTypes() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_rowSeatTypes));
    }

    public List<WebElement> getRowPrices() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_rowPrices));
    }
    
    public WebElement getLblTableHeaderMsg() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblTableHeaderMsg));
    }
    
    // Methods
    public String getMessageTicketBookedSuccessfully() {
    		Utilities.scrollToElement(_lblTableHeaderMsg);
		return getLblTableHeaderMsg().getText();
	}
    
    public Map<String, String> getActualPriceMap() {
    		Utilities.scrollToElement(_lblTableHeaderMsg);
        Map<String, String> actualPriceMap = new HashMap<>();

        for (int i = 0; i < getRowSeatTypes().size(); i++) {
            actualPriceMap.put(getRowSeatTypes().get(i).getText().trim(), getRowPrices().get(i).getText().trim());
        }
        return actualPriceMap;
    }
    
	public boolean isAtTicketPricePage() {
		return Constant.WEBDRIVER.getTitle().contains("Ticket Price");
	}
    
}
