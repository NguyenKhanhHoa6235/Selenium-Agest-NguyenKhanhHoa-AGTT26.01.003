package Railway;

import org.testng.annotations.Test;
import org.testng.Assert;

import Constant.Constant;
import Constant.MenuItem;
import Class.User;

public class LogoutTest extends TestBase {
	User userAccount = new User();
    
    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Login with valid Email and Password");
        System.out.println("3. Click on \"FAQ\" tab");
        System.out.println("4. Click on \"Log out\" tab");
        HomePage homePage = new HomePage();
        homePage.open();
        //Verify 1
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");

        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        FAQPage FaqPage = homePage.gotoPage(MenuItem.LOGOUT, FAQPage.class);
        HomePage homePageOut = homePage.gotoPage(MenuItem.LOGOUT, HomePage.class);
        
        //Verify 2
        System.out.println("Verify: Home page displays \"Log out\" tab is disappeared.");
        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");     
        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }

}
