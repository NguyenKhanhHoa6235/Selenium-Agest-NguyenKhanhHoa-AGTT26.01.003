package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Class.User;

import org.testng.Assert;

import Constant.Constant;

public class LogoutTest {
	User userAccount = new User();
	
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");     
        
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window();
    }
    
    @Test
    public void TC06() {
        System.out.println("TC06 - User is redirected to Home page after logging out");
        
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
        FAQPage FAQPage = homePage.gotoFAQPage();
        HomePage homePageOut = homePage.gotoLogoutPage();
        
        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");
        
        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    

}
