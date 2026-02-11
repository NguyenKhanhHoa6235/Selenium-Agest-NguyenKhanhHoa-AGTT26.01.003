package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Class.BookTicket;
import Class.User;
import Constant.MenuItem;

public class CancelBookingTest extends TestBase{
	User userAccount = new User();
	BookTicket bookTicket = new BookTicket();
	
	@Test
    public void TC016() {
		//Data
        userAccount.setUsername("nguyenvan@sharklasers.com");
        userAccount.setPassword("14725836");
        
        bookTicket.setDepartStation(null);
        bookTicket.setDepartStation("Nha Trang");
        bookTicket.setArrive("Huế");
        bookTicket.setSeatType("Soft bed with air conditioner");
        bookTicket.setTicketAmount("1");
		
        System.out.println("TC016 - User can cancel a ticket");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        System.out.println("3. Book a ticket");
        TicketBookedPage ticketBookedPage = handleBookTicket(userAccount, bookTicket);
        
        System.out.println("4. Click on \"My ticket\" tab");
        ticketBookedPage.gotoPage(MenuItem.MY_TICKET);
        
        System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
        System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
        System.out.println("Verify: The canceled ticket is disappeared.");
        
    }

}
