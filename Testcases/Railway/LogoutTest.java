package Railway;

import org.testng.annotations.Test;
import org.testng.Assert;

import Constant.Constant;
import Class.User;

public class LogoutTest extends TestBase {
	User userAccount = new User();
    
    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        
//        Utilities.openToAndSwitchTag(Constant.RAIWAY_URL);
        HomePage homePage = new HomePage();
        homePage.open();
        Assert.assertTrue(homePage.isAtHomePage(),"The homepage is not displaying.");

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
        FAQPage FAQPage = homePage.gotoFAQPage();
        HomePage homePageOut = homePage.gotoLogoutPage();
        
        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");
        
        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }

}
