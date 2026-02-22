package Railway;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Class.BookTicket;
import Class.User;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
import Constant.SeaTypeTicket;
import Constant.StationTicket;

public class CancelBookingTest extends TestBase{
	@Test
    public void TC016() {
		Constant.WEBDRIVER.manage().window().maximize();
		//Data
        User userAccount = new User();    
        userAccount.setPassword("74185296");
        
        BookTicket bookTicket = new BookTicket();
        bookTicket.setDepartStation(StationTicket.NHATRANG.getDisplayText());
        bookTicket.setArrive(StationTicket.HUE.getDisplayText());
        bookTicket.setSeatType(SeaTypeTicket.SOFT_SEAT_WITH_AIR_CONDITIONER.getDisplayText());
        bookTicket.setTicketAmount("1"); 
		
        System.out.println("TC016 - User can cancel a ticket");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
    		String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(),userAccount.getPassword());
    		userAccount.setUsername(fullEmailAdrress);
    		
    		System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");
        System.out.println("3. Book a ticket");
        TicketBookedPage ticketBookedPage = handleLoginAndBookTicket(userAccount, bookTicket);
        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
        Assert.assertFalse(tableTicketBooked.isEmpty(),"Ticket list is empty after booking");

        Map<String, String> actualTicket = tableTicketBooked.get(0);
        bookTicket.setDepartDate(actualTicket.get("Depart Date"));
        bookTicket.setDepartStation(actualTicket.get("Depart Station"));
        bookTicket.setArrive(actualTicket.get("Arrive Station"));
        bookTicket.setSeatType(actualTicket.get("Seat Type"));
        bookTicket.setTicketAmount(actualTicket.get("Amount"));
 
        System.out.println("4. Click on \"My ticket\" tab");
        MyTicketPage myTicketPage = ticketBookedPage.gotoPage(MenuItem.MY_TICKET, MyTicketPage.class);
        
        List<Map<String, String>> tableManageTicket = myTicketPage.getTableManageTicket();
        Utilities.printTable(tableManageTicket);
	    Assert.assertFalse(tableManageTicket.isEmpty(),"Ticket list is empty after booking");
        
        System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
        String today = getToday();
        myTicketPage.clickCancelButton(today, bookTicket);
        
        System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
        Utilities.waitAlertAndAccept();
        Utilities.reload();
        
        System.out.println("Verify: The canceled ticket is disappeared.");
	    Map<String, String> expected = new LinkedHashMap<>();
	    expected.put("Book Date", today);
	    expected.put("Depart Station", bookTicket.getDepartStation());
	    expected.put("Arrive Station", bookTicket.getArrive());
	    expected.put("Seat Type", bookTicket.getSeatType());
	    expected.put("Amount", bookTicket.getTicketAmount());
	      
	    boolean isExist = isMapExistInList(tableManageTicket, expected);

	    Assert.assertTrue(isExist, "Expected ticket is not found in table");
//	    assertTrue(Utilities.isRowExistInTable(tableManageTicket, expected), "Verify fail: Ticket information does not match expected data");        
	}
}
