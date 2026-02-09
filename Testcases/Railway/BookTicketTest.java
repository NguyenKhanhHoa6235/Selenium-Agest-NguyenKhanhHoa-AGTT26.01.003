package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Class.User;
import Constant.Constant;
import Constant.MenuItem;

public class BookTicketTest extends TestBase {
	User userAccount = new User();
	
    @Test
    public void TC012() {
    		//Data
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        String departDate = getDatePlusDays(2);
        String departStation = "Nha Trang";
        String arrive = "Huế";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";
        
        System.out.println("TC06 - User can book 1 ticket at a time");
        
        System.out.println("Pre-condition: an actived account is existing");
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
        
        System.out.println("3. Click on \"Book ticket\" tab");
        homePage.gotoPage(MenuItem.BOOK_TICKET);
      
        System.out.println("4. Select the next 2 days from \"Depart date\"");     
        System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
        System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
        System.out.println("7. Select \"1\" for \"Ticket amount\"");
        System.out.println("8. Click on \"Book ticket\" button");
        
        BookTicketPage bookTicketPage = new BookTicketPage();
        bookTicketPage.bookTicket(departDate, departStation, arrive, seatType, ticketAmount);
        
//        System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        
        

        
//        Utilities.openToAndSwitchTag(Constant.RAIWAY_URL);

//        
//        FAQPage FAQPage = homePage.gotoFAQPage();
//        HomePage homePageOut = homePage.gotoLogoutPage();
//        
//        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");
//        
//        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }
}
