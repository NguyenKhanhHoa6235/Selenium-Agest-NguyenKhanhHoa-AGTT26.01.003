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
import Constant.SeaTypeTicket;
import Constant.StationTicket;

public class BookTicketTest extends TestBase {	
    @Test
    public void TC012() {
        System.out.println("TC012 - User can book 1 ticket at a time");
        
    		//Data
    		User userAccount = new User();    	
        userAccount.setPassword("12345678");
        
        BookTicket bookTicket = new BookTicket();
        bookTicket.setDepartStation(StationTicket.NHATRANG.getDisplayText());
        bookTicket.setArrive(StationTicket.HUE.getDisplayText());
        bookTicket.setSeatType(SeaTypeTicket.SOFT_BED_WITH_AIR_CONDITIONER.getDisplayText());
        bookTicket.setTicketAmount("1");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
    		String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(), userAccount.getPassword());
    		userAccount.setUsername(fullEmailAdrress);
    		
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Book ticket\" tab");
        BookTicketPage bookTicketPage = homePage.gotoPage(MenuItem.BOOK_TICKET,BookTicketPage.class);
      
        System.out.println("4. Select the next 2 days from \"Depart date\"");     
        System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
        System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
        System.out.println("7. Select \"1\" for \"Ticket amount\"");
        System.out.println("8. Click on \"Book ticket\" button");  
        String departDateCurrent = bookTicketPage.getDepartDateFirstOption();
        String datePlusTwo = getAddDaysToDate(departDateCurrent, 2, "M/d/yyyy"); 
        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(
        		datePlusTwo, 
        		bookTicket.getDepartStation(), 
        		bookTicket.getArrive(), 
        		bookTicket.getSeatType(), 
        		bookTicket.getTicketAmount()
        	);
        
        //Verify
        System.out.println("Verify: Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
        String expectedMsg = "Ticket booked successfully!";
        
        Utilities.scrollToBottomPage();
        
        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
//        Utilities.printTable(tableTicketBooked); 
        Assert.assertFalse(tableTicketBooked.isEmpty(),"Ticket list is empty after booking");
        
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Depart Date", datePlusTwo);
        expected.put("Depart Station", bookTicket.getDepartStation());
        expected.put("Arrive Station", bookTicket.getArrive());
        expected.put("Seat Type", bookTicket.getSeatType());
        expected.put("Amount", bookTicket.getTicketAmount());
        
        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
        assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expected), "Ticket information does not match expected data");
    }
    
    @Test
    public void TC013() {
        System.out.println("TC013 - User can book many tickets at a time");
        
    		//Data
    		User userAccount = new User();  
        userAccount.setPassword("14785236");
        
        BookTicket bookTicket = new BookTicket();
        bookTicket.setDepartStation(StationTicket.NHATRANG.getDisplayText());
        bookTicket.setArrive(StationTicket.SAIGON.getDisplayText());
        bookTicket.setSeatType(SeaTypeTicket.SOFT_SEAT_WITH_AIR_CONDITIONER.getDisplayText());
        bookTicket.setTicketAmount("5");                  
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
    	 	String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(), userAccount.getPassword());
		userAccount.setUsername(fullEmailAdrress);
          
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Book ticket\" tab");
        BookTicketPage bookTicketPage = homePage.gotoPage(MenuItem.BOOK_TICKET, BookTicketPage.class);
      
        System.out.println("4. Select the next 25 days from \"Depart date\"");     
        System.out.println("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\".");
        System.out.println("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
        System.out.println("7. Select \"5\" for \"Ticket amount\"");
        System.out.println("8. Click on \"Book ticket\" button");  
        String departDateCurrent = bookTicketPage.getDepartDateFirstOption();
        String datePlus = getAddDaysToDate(departDateCurrent, 25, "M/d/yyyy"); 
        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(
        		datePlus, 
        		bookTicket.getDepartStation(), 
        		bookTicket.getArrive(), 
        		bookTicket.getSeatType(), 
        		bookTicket.getTicketAmount()
        	);
        
        //Verify
        System.out.println("Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
        String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
        String expectedMsg = "Ticket booked successfully!";
        
        Utilities.scrollToBottomPage();
        
        List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
//        Utilities.printTable(tableTicketBooked); 
        Assert.assertFalse(tableTicketBooked.isEmpty(),"Ticket list is empty after booking");
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("Depart Date", datePlus);
        expected.put("Depart Station", bookTicket.getDepartStation());
        expected.put("Arrive Station", bookTicket.getArrive());
        expected.put("Seat Type", bookTicket.getSeatType());
        expected.put("Amount", bookTicket.getTicketAmount());
        
        Assert.assertEquals(actualMsg, expectedMsg, "Verify fail:Message is not displayed as expected");
        assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expected), "Verify fail: Ticket information does not match expected data");
    }
    
    @Test
    public void TC014() {
        System.out.println("TC014 - User can book many tickets at a time");
        
    		//Data
    		User userAccount = new User();
        userAccount.setPassword("123456788");
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
 		String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(), userAccount.getPassword());
		userAccount.setUsername(fullEmailAdrress);
		
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Timetable\" tab");
        TimetablePage timetablePage = homePage.gotoPage(MenuItem.TIMETABLE, TimetablePage.class);
        
        System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
        TicketPrice ticketPrice = timetablePage.clickCheckPrice(StationTicket.DANANG.getDisplayText(), StationTicket.SAIGON.getDisplayText());
        
        //Verify
        System.out.println("\"Ticket Price\" page is loaded.\r\n"
        		+ "Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\".\r\n"
        		+ "Price for each seat displays correctly\r\n"
        		+ "HS = 310000, SS = 335000, SSC = 360000, HB = 410000, SB = 460000, SBC = 510000");
        Map<String, String> actualPriceMap = ticketPrice.getActualPriceMap();
        Map<String, String> expectedPriceMap = Map.of(
			"HS", "310000",
			"SS", "335000",
			"SSC", "360000",
			"HB", "410000",
			"SB", "460000",
			"SBC", "510000"
        );
        Assert.assertTrue(ticketPrice.isAtTicketPricePage(), "Verify fail: Expected to navigate to Ticket Price page, but it did not happen.");
        Assert.assertEquals(actualPriceMap, expectedPriceMap, "Verify fail: Ticket Price Table information does not match expected data");
    }
    
    @Test
    public void TC015() {
        System.out.println("TC015 - User can book ticket from Timetable");
        
    		//Data
    		User userAccount = new User();
        userAccount.setPassword("12345678");
        
        BookTicket bookTicket = new BookTicket();
        bookTicket.setDepartStation(null);
        bookTicket.setArrive(null);
        bookTicket.setSeatType(null);
        bookTicket.setTicketAmount("5");
        
        String expectedDepartStation = StationTicket.QUANGNGAI.getDisplayText();
        String expectedArrive = StationTicket.HUE.getDisplayText();
        
        //Step
        System.out.println("Pre-condition: an actived account is existing");
 		String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(), userAccount.getPassword());
		userAccount.setUsername(fullEmailAdrress);
		
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with a valid account ");  
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        System.out.println("3. Click on \"Timetable\" tab");
        TimetablePage timetablePage = homePage.gotoPage(MenuItem.TIMETABLE, TimetablePage.class);
        
        System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
        BookTicketPage bookTicketPage = timetablePage.clickBookTicket(expectedDepartStation, expectedArrive);
        
        //Verify 1:
        System.out.println("Verify 1: Book ticket form is shown with the corrected \"depart from\" and \"Arrive at\"");
        String actualDepartStation = bookTicketPage.getDepartStationSelectedOption();
        String actualArriveStation = bookTicketPage.getArriveStationSelectedOption();
        Assert.assertEquals(actualDepartStation, expectedDepartStation, "Verify 1 fail: Book ticket form shows incorrect Depart Station"); 
        Assert.assertEquals(actualArriveStation, expectedArrive, "Verify 1 fail: Book ticket form shows incorrect Arrive Station"); 
        
        //step
        System.out.println("5. Select Depart date = tomorrow");     
        System.out.println("6. Select Ticket amount = 5");
        System.out.println("7. Click on \"Book ticket\" button");
        String dateTomorrow = getDatePlusDays(1); 
        Utilities.scrollToBottomPage();
        TicketBookedPage ticketBookedPage = bookTicketPage.bookTicket(
        		dateTomorrow, 
        		bookTicket.getDepartStation(), 
        		bookTicket.getArrive(), 
        		bookTicket.getSeatType(), 
        		bookTicket.getTicketAmount()
        	);
        
        //Verify 2:
        System.out.println("Message \"Ticket booked successfully!\" displays. Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		String actualMsg = ticketBookedPage.getMessageTicketBookedSuccessfully();
		String expectedMsg = "Ticket booked successfully!";
		Utilities.scrollToBottomPage();
      
		List<Map<String, String>> tableTicketBooked = ticketBookedPage.getTableTicketBooked();
		Utilities.printTable(tableTicketBooked); 
	    Assert.assertFalse(tableTicketBooked.isEmpty(),"Ticket list is empty after booking");
	    Map<String, String> expectedTableTicketBooked = new LinkedHashMap<>();
	    expectedTableTicketBooked.put("Depart Date", dateTomorrow);
	    expectedTableTicketBooked.put("Depart Station", expectedDepartStation);
	    expectedTableTicketBooked.put("Arrive Station", expectedArrive);
	    expectedTableTicketBooked.put("Seat Type", "Hard seat");
	    expectedTableTicketBooked.put("Amount", bookTicket.getTicketAmount());
      
	    Assert.assertEquals(actualMsg, expectedMsg, "Verify 2 fail: Message Ticket Booked Page is not displayed as expected");
	    assertTrue(Utilities.isRowExistInTable(tableTicketBooked, expectedTableTicketBooked), "Verify 2 fail: Ticket information does not match expected data");
    }
}
