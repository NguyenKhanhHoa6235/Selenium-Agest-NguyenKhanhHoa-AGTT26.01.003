package Railway;

import org.testng.annotations.Test;
import Class.User;

import java.awt.Menu;

import org.testng.Assert;
import Constant.Constant;
import Constant.MenuItem;

public class LoginTest extends TestBase {
	User userAccount = new User();
    
    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);       
        
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
      
        System.out.println("2. Click on \"Login\" tab");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        
        System.out.println("3. Enter valid Email and Password");   
        System.out.println("4. Click on \"Login\" button");
        String actualMsg = loginPage.login(userAccount).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        //Verify
        System.out.println("Verify: User is logged into Railway. Welcome user message is displayed.");
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    
    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot login with blank \"Username\" textbox");
        
        userAccount.setUsername("");
        userAccount.setPassword(Constant.PASSWORD);
               
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
     
        System.out.println("2. Click on \"Login\" tab");
        System.out.println("3. User doesn't type any words into \"Username\" textbox but enter valid information into \"Password\" textbox ");   
        System.out.println("4. Click on \"Login\" button");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
        
        //Verify
        System.out.println("Verify: User can't login and message \"There was a problem with your login and/or errors exist in your form. \" appears.");
        String actualMsg = loginPage.getLoginErrorMsg();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");        
        Assert.assertTrue(loginPage.isAtLoginPage());
    }
    
    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword("122546546");
        
        
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
        
        System.out.println("2. Click on \"Login\" tab");
        System.out.println("3. Enter valid Email and invalid Password");   
        System.out.println("4. Click on \"Login\" button");
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
        loginPage.login(userAccount);
           
        //Verify
        System.out.println("Verify: Error message \"There was a problem with your login and/or errors exist in your form.\" is displayed");
        String actualMsg = loginPage.getLoginErrorMsg();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected"); 
        Assert.assertTrue(loginPage.isAtLoginPage());
    }
    
    
    @Test
    public void TC04() {
        System.out.println("TC03 - System shows message when user enters wrong password many times");
        
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword("122546546");
        
        String expectedNormalMsg = "Invalid username or password. Please try again";
        String expectedLimitMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";      
        
        System.out.println("1. Navigate to QA Railway Website");
        System.out.println("2. Click on \"Login\" tab");
        
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
              
        System.out.println("3. Enter valid information into \"Username\" textbox except \"Password\" textbox.");   
        System.out.println("4. Click on \"Login\" button");
        System.out.println("5. Repeat step 3 and 4 three more times.");
        System.out.println("Verify 1: \"Invalid username or password. Please try again\" is shown");
        System.out.println("Verify 2: \"User can't login and message \"You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.\" appears.");
        
        String actualMsg;
        for(int i = 1; i <= 4; i++) 
        {
        		loginPage.login(userAccount);

        		if(i < 4)
        		{
            		actualMsg = loginPage.getLoginErrorMsg();
            		Assert.assertEquals(actualMsg, expectedNormalMsg, "Error message at attempt " + i + " is incorrect");       		
        		}
        		else {
        			actualMsg = loginPage.getLoginErrorMsg();
            		Assert.assertEquals(actualMsg, expectedLimitMsg, "Login attempt limit message is incorrect");
        		}
        		
        		Assert.assertTrue(loginPage.isAtLoginPage());
        }      
    }
    
    
  @Test
  public void TC05() {
      System.out.println("TC05 - User can't login with an account hasn't been activated");
      
      userAccount.setUsername("nguyenkhanhhoa6235@gmail.com");
      userAccount.setPassword("12345678");
      
      System.out.println("1. Navigate to QA Railway Website");
      System.out.println("2. Click on \"Login\" tab");
      HomePage homePage = new HomePage();
      homePage.open();

      LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
           
      System.out.println("3. Enter username and password of account hasn't been activated.");
      System.out.println("4. Click on \"Login\" button");
      loginPage.login(userAccount);
          
      //Verify
      System.out.println("Verify: User can't login and message \"Invalid username or password. Please try again.\" appears.");
      String actualMsg = loginPage.getLoginErrorMsg();
      String expectedMsg = "Invalid username or password. Please try again.";
      
      Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected"); 
      Assert.assertTrue(loginPage.isAtLoginPage(), "User should stay at Login page");
  }
  
}
