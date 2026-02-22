package Railway;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Class.BookTicket;
import Common.Utilities;
import Constant.Constant;

public class MyTicketPage {
    // Variables
    private static final String CANCEL_BUTTON_XPATH =
            "//table//tr[" +
            "td[normalize-space()='%s']" +
            "/following-sibling::td[normalize-space()='%s']" +
            " and td[normalize-space()='%s']" +
            " and td[normalize-space()='%s']" +
            " and td[normalize-space()='%s']" +
            "]//input[@value='Cancel']";

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

    private WebElement getCancelButtonElement(String bookDate,String departStation,String arriveStation,String seatType,String ticketAmount) {
        By _btnCancel = By.xpath(String.format(
                CANCEL_BUTTON_XPATH,
                departStation,
                arriveStation,
                seatType,
                bookDate,
                ticketAmount
        ));

        return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_btnCancel));
    }


    // Methods
    public String getManageTicketMgs() {
        return getLblManageTicketMgs().getText();
    }

    public void clickCancelButton(String bookDate,String departStation,String arriveStation,String seatType,String ticketAmount) {
        Utilities.scrollToBottomPage();
        getCancelButtonElement(bookDate, departStation, arriveStation, seatType, ticketAmount).click();
    }

    public void clickCancelButton(String today, BookTicket bookTicket) {

        clickCancelButton(
                today,
                bookTicket.getDepartStation(),
                bookTicket.getArrive(),
                bookTicket.getSeatType(),
                bookTicket.getTicketAmount()
        );
    }

    public List<Map<String, String>> getTableManageTicket() {
        Utilities.waitForVisible(_lblManageTicketMgs);
        Utilities.waitForVisible(_ticketTable);
        return Utilities.convertRowsToMap(getRows(), getHeaders());
    }
}