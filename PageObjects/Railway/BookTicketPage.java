package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;
import Constant.StationTicket;

public class BookTicketPage extends GeneralPage{
	
	// Locators 
    private final By _dropdownDepartDate = By.xpath("//select[@name='Date']");
    private final By _dropdownDepartStation = By.xpath("//select[@name='DepartStation']");
    private final By _dropdownArriveStation = By.xpath("//select[@name='ArriveStation']");
    private final By _dropdownSeatType = By.xpath("//select[@name='SeatType']");
    private final By _dropdownTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By _btnBookTicket = By.xpath("//input[@type='submit']");
    
    // Elements
    public WebElement getDropdownDepartDate() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_dropdownDepartDate));
        
    }

    public WebElement getDropdownDepartStation () {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_dropdownDepartStation));
    }

    public WebElement getDropdownArriveStation() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_dropdownArriveStation));
    }

    public WebElement getDropdownSeatType() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_dropdownSeatType));
    }

    public WebElement getDropdownTicketAmount() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_dropdownTicketAmount));
    }
    
    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForPresence(_btnBookTicket));
    }
    
    // Methods
    public TicketBookedPage bookTicket(String departDate, String departStation, String arrive, String seatType, String ticketAmount) {
    		Utilities.selectByVisibleText(getDropdownDepartDate(), departDate);
    		Utilities.selectByVisibleText(getDropdownDepartStation(), departStation);
    		if(departStation != null) {
        		Utilities.waitForPresence(_dropdownArriveStation);
        		Utilities.waitForSelectOptionsChange(_dropdownArriveStation);
    		}
    		Utilities.selectByVisibleText(getDropdownArriveStation(), arrive);
    		Utilities.selectByVisibleText(getDropdownSeatType(), seatType);
        Utilities.selectByVisibleText(getDropdownTicketAmount(), ticketAmount);
        
        Utilities.scrollAndClick(getBtnBookTicket());    
        
        return new TicketBookedPage();
    }
    
    
    public String getDepartDateFirstOption() {
	    	return Utilities.getSelectFirstOption(getDropdownDepartDate());
    }
    
    public String getDepartStationSelectedOption() {
    		return Utilities.getSelectFirstOption(getDropdownDepartStation());
    }
    
    public String getArriveStationSelectedOption() {
    		return Utilities.getSelectFirstOption(getDropdownArriveStation());
    }

}
