package Railway;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Class.BookTicket;
import Common.Utilities;
import Constant.Constant;

public class MyTicketPage {
	// Locators 
    private final By _lblManageTicketMgs = By.xpath("//div[@id='content']//h1[@align='center']");
    private final By _ticketTable = By.xpath("//table[@class='MyTable']");
    private final By _tableHeaders = By.xpath("//table[@class='MyTable']//th");
    private final By _tableRows = By.xpath("//table[@class='MyTable']//tbody//tr");
    
    // Elements
    public List<WebElement> getHeaders() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_tableHeaders));
    }

    public List<WebElement> getRows() {
        return Constant.WEBDRIVER.findElements(Utilities.waitForVisible(_tableRows));
    }
    
    public WebElement getLblManageTicketMgs() {
        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblManageTicketMgs));
    }
    
    // Methods
    public String getManageTicketMgs() {
		return getLblManageTicketMgs().getText();
	}
    
    public void clickCancelButton(String bookDate, String departStation, String arriveStation, String seatType, String ticketAmount) {
        By _btnCancel = By.xpath(
            "//table//tr[" +
                "td[normalize-space()='" + departStation + "']" +
                "/following-sibling::td[normalize-space()='" + arriveStation + "']" +
                " and td[normalize-space()='" + seatType + "']" +
                " and td[normalize-space()='" + bookDate + "']" +
                " and td[normalize-space()='" + ticketAmount + "']" +
            "]//input[@value='Cancel']"
        );

        Utilities.scrollToElement(_btnCancel);
        WebElement cancelButton = Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_btnCancel));

        cancelButton.click();
    }
    
    public void clickCancelButton(String today, BookTicket bookTicket) {
        String bookDate = today;
        String departStation = bookTicket.getDepartStation();
        String arriveStation = bookTicket.getArrive();
        String seatType = bookTicket.getSeatType();
        String ticketAmount = bookTicket.getTicketAmount();
        
        By _btnCancel = By.xpath(
            "//table//tr[" +
                "td[normalize-space()='" + departStation + "']" +
                "/following-sibling::td[normalize-space()='" + arriveStation + "']" +
                " and td[normalize-space()='" + seatType + "']" +
                " and td[normalize-space()='" + bookDate + "']" +
                " and td[normalize-space()='" + ticketAmount + "']" +
            "]//input[@value='Cancel']"
        );

        Utilities.scrollToElement(_btnCancel);
        WebElement cancelButton = Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_btnCancel));

        cancelButton.click();
    }
    
    public List<Map<String, String>> getTableManageTicket(){
		Utilities.waitForVisible(_lblManageTicketMgs);
		Utilities.waitForVisible(_ticketTable);	
		return Utilities.convertRowsToMap(getRows(), getHeaders());
    }

}
