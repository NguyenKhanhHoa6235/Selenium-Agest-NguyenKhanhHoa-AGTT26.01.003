package Railway;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Class.User;

import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;

public class LoginTest {
	User userAccount = new User();
	
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        
//        System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() 
//                + "\\Executables\\chromedriver.exe");
//        
        Constant.WEBDRIVER = new ChromeDriver();
//        Constant.WEBDRIVER.manage().window().maximize();
        Constant.WEBDRIVER.manage().window();
       
    }
    
    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(userAccount).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
//        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getCenterWelcomeMessage();     
//        String expectedMsg = "Welcome to Safe Railway";
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    
    @Test
    public void TC02() {
        System.out.println("TC02 - User cannot login with blank \"Username\" textbox");
        
        userAccount.setUsername("");
        userAccount.setPassword(Constant.PASSWORD);
        
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
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
        
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(userAccount);
        
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
        
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        
        String expectedNormalMsg = "Invalid username or password. Please try again";
        String expectedLimitMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
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
      
      HomePage homePage = new HomePage();
      homePage.open();

      LoginPage loginPage = homePage.gotoLoginPage();
      loginPage.login(userAccount);
      
      String actualMsg = loginPage.getLoginErrorMsg();
      String expectedMsg = "Invalid username or password. Please try again.";
      Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
      
      Assert.assertTrue(loginPage.isAtLoginPage(), "User should stay at Login page");

  }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    

}
