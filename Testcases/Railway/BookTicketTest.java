package Railway;

import static org.testng.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Class.BookTicket;
import Class.User;
import Common.Utilities;
import Constant.MenuItem;

public class BookTicketTest extends TestBase {
	User userAccount = new User();
	BookTicket bookTicket = new BookTicket();
	
    @Test
    public void TC012() {
    		//Data
        userAccount.setUsername("nguyenvan@sharklasers.com");
        userAccount.setPassword("14725836");
        
        String departStation = "Nha Trang";
        String arrive = "Huế";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";
        
        System.out.println("TC012 - User can book 1 ticket at a time");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
//    		String fullEmailAdrress = registerWithEmailGuerrilla(CurrentPassword, CurrentPassword);
          
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
        String departDateCurrent = bookTicketPage.getDepartDateFirstOption();
        String datePlusTwo = getAddDaysToDate(departDateCurrent, 2, "M/d/yyyy"); 
        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(datePlusTwo, departStation, arrive, seatType, ticketAmount);
        
        //Verify
        System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
        String expectedMsg = "Ticket booked successfully!";
        
        Utilities.scrollToBottomPage();
        
        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
//        Utilities.printTable(tableTicketBooked); 
        Assert.assertFalse(
        		tableTicketBooked.isEmpty(),
        	    "Ticket list is empty after booking"
        	);
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Depart Date", datePlusTwo);
        expected.put("Depart Station", departStation);
        expected.put("Arrive Station", arrive);
        expected.put("Seat Type", seatType);
        expected.put("Amount", ticketAmount);
        
        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
        assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expected), "Ticket information does not match expected data");

    }
    
    @Test
    public void TC013() {
    		//Data
        userAccount.setUsername("nguyenvan@sharklasers.com");
        userAccount.setPassword("14725836");
        
        String departStation = "Nha Trang";
        String arrive = "Sài Gòn";
        String seatType = "Soft seat with air conditioner";
        String ticketAmount = "5";
        
        System.out.println("TC06 - User can book many tickets at a time");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
//    		String fullEmailAdrress = registerWithEmailGuerrilla(CurrentPassword, CurrentPassword);
          
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Book ticket\" tab");
        homePage.gotoPage(MenuItem.BOOK_TICKET);
      
        System.out.println("4. Select the next 25 days from \"Depart date\"");     
        System.out.println("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\".");
        System.out.println("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
        System.out.println("7. Select \"5\" for \"Ticket amount\"");
        System.out.println("8. Click on \"Book ticket\" button");  
        BookTicketPage bookTicketPage = new BookTicketPage();
        String departDateCurrent = bookTicketPage.getDepartDateFirstOption();
        String datePlusTwo = getAddDaysToDate(departDateCurrent, 25, "M/d/yyyy"); 
        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(datePlusTwo, departStation, arrive, seatType, ticketAmount);
        
        //Verify
        System.out.println("Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
        String expectedMsg = "Ticket booked successfully!";
        
        Utilities.scrollToBottomPage();
        
        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
//        Utilities.printTable(tableTicketBooked); 
        Assert.assertFalse(
        		tableTicketBooked.isEmpty(),
        	    "Ticket list is empty after booking"
        	);
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Depart Date", datePlusTwo);
        expected.put("Depart Station", departStation);
        expected.put("Arrive Station", arrive);
        expected.put("Seat Type", seatType);
        expected.put("Amount", ticketAmount);
        
        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
        assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expected), "Ticket information does not match expected data");

    }
    
    @Test
    public void TC014() {
    		//Data
        userAccount.setUsername("nguyenvan@sharklasers.com");
        userAccount.setPassword("14725836");
        
        String departStation = "Nha Trang";
        String arrive = "Sài Gòn";
        String seatType = "Soft seat with air conditioner";
        String ticketAmount = "5";
        
        System.out.println("TC014 - User can book many tickets at a time");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
//    		String fullEmailAdrress = registerWithEmailGuerrilla(CurrentPassword, CurrentPassword);
          
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Timetable\" tab");
        homePage.gotoPage(MenuItem.TIMETABLE);
        
        System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");     
        TimetablePage timetablePage =  new TimetablePage();
        List<Map<String, String>> tableTimetable = timetablePage.getTableTimetable();
        Utilities.printTable(tableTimetable);
//        BookTicketPage bookTicketPage = new BookTicketPage();
//        String departDateCurrent = bookTicketPage.getDepartDateFirstOption();
//        String datePlusTwo = getAddDaysToDate(departDateCurrent, 25, "M/d/yyyy"); 
//        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(datePlusTwo, departStation, arrive, seatType, ticketAmount);
//        
//        //Verify
//        System.out.println("Verify: \"Ticket Price\" page is loaded.\r\n"
//        		+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
//        		+ "Price for each seat displays correctly\r\n"
//        		+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
//        String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
//        String expectedMsg = "Ticket booked successfully!";
//        
//        Utilities.scrollToBottomPage();
//        
//        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
////        Utilities.printTable(tableTicketBooked); 
//        Assert.assertFalse(
//        		tableTicketBooked.isEmpty(),
//        	    "Ticket list is empty after booking"
//        	);
//        Map<String, String> expected = new LinkedHashMap<>();
//        expected.put("Depart Date", datePlusTwo);
//        expected.put("Depart Station", departStation);
//        expected.put("Arrive Station", arrive);
//        expected.put("Seat Type", seatType);
//        expected.put("Amount", ticketAmount);
//        
//        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
//        assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expected), "Ticket information does not match expected data");

    }
}
