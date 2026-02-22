package Railway;

import org.testng.annotations.Test;
import org.testng.Assert;

import Constant.Constant;
import Constant.MenuItem;
import Class.User;

public class LogoutTest extends TestBase {   
    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");
        
        //Data
        User userAccount = new User(Constant.USERNAME, Constant.PASSWORD);   
        
        //Step
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
        
        //Verify 1
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");

        System.out.println("2. Login with valid Email and Password");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);      
        
        System.out.println("3. Click on \"FAQ\" tab");
        System.out.println("4. Click on \"Log out\" tab");
        FAQPage FaqPage = homePage.gotoPage(MenuItem.LOGOUT, FAQPage.class);
        HomePage homePageOut = homePage.gotoPage(MenuItem.LOGOUT, HomePage.class);
        
        //Verify 2
        System.out.println("Verify: Home page displays \"Log out\" tab is disappeared.");
        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");     
        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }

}
