package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Class.BookTicket;
import Class.User;
import Constant.MenuItem;
import Constant.SeaTypeTicket;
import Constant.StationTicket;

public class CancelBookingTest extends TestBase{
	User userAccount = new User();
	BookTicket bookTicket = new BookTicket();
	
	@Test
    public void TC016() {
		//Data
        userAccount.setUsername("nguyenvan@sharklasers.com");
        userAccount.setPassword("14725836");
        
        bookTicket.setDepartStation(StationTicket.NHATRANG.getDisplayText());
        bookTicket.setArrive(StationTicket.HUE.getDisplayText());
        bookTicket.setSeatType(SeaTypeTicket.SOFT_SEAT_WITH_AIR_CONDITIONER.getDisplayText());
        bookTicket.setTicketAmount("1"); 
		
        System.out.println("TC016 - User can cancel a ticket");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        System.out.println("3. Book a ticket");
        TicketBookedPage ticketBookedPage = handleBookTicket(userAccount, bookTicket);
        
        System.out.println("4. Click on \"My ticket\" tab");
        MyTicketPage myTicketPage = ticketBookedPage.gotoPage(MenuItem.MY_TICKET, MyTicketPage.class);
        
        System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
        System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
        System.out.println("Verify: The canceled ticket is disappeared.");
        
    }

}
