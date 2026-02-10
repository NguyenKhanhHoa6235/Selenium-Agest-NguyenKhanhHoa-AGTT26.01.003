package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.Utilities;
import Constant.Constant;

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
        return Constant.WEBDRIVER.findElement(_dropdownDepartDate);
    }

    public WebElement getDropdownDepartStation () {
        return Constant.WEBDRIVER.findElement(_dropdownDepartStation);
    }

    public WebElement getDropdownArriveStation() {
        return Constant.WEBDRIVER.findElement(_dropdownArriveStation);
    }

    public WebElement getDropdownSeatType() {
        return Constant.WEBDRIVER.findElement(_dropdownSeatType);
    }

    public WebElement getDropdownTicketAmount() {
        return Constant.WEBDRIVER.findElement(_dropdownTicketAmount);
    }
    
    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }
    
    // Methods
    public TicketBookedPage bookTicket(String departDate, String departStation, String arrive, String seatType, String ticketAmount) {
    		Utilities.selectByVisibleText(getDropdownDepartDate(), departDate);
    		Utilities.selectByVisibleText(getDropdownDepartStation(), departStation);
    		Utilities.waitForPresence(_dropdownArriveStation);
    		Utilities.waitForSelectOptionsChange(_dropdownArriveStation);
    		Utilities.selectByVisibleText(getDropdownArriveStation(), arrive);
    		Utilities.selectByVisibleText(getDropdownSeatType(), seatType);
        Utilities.selectByVisibleText(getDropdownTicketAmount(), ticketAmount);
        
        Utilities.scrollAndClick(getBtnBookTicket());    
        
        return new TicketBookedPage();
    }
    
    public String getDepartDateFirstOption() {
	    	return Utilities.getSelectFirstOption(getDropdownDepartDate());
    }

}
