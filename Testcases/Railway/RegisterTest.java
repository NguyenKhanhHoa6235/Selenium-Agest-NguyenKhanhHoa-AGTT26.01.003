package Railway;


import org.testng.Assert;
import org.testng.annotations.Test;

import Class.User;
import Common.Utilities;
import Constant.Constant;
import Guerillamai.GuerrillaMailPage;


public class RegisterTest extends TestBase{
	User userAccount = new User();
	
    @Test
    public void TC07() {
        System.out.println("TC07 - User can't create account with an already in-use email");
        
    		System.out.println("Pre-condition: an actived account is existing");
    		
    		RegisterPage register = new RegisterPage();
    		String fullEmailAdrress = register.registerWithEmailGuerrilla("12345678", "12345678");
    		
    		System.out.println("nguyenkhanh hoa: "+ fullEmailAdrress);
    		
    		
    		//data
    		String accountAlreadyExists = fullEmailAdrress;
        String password = "12345678";
        String pid = "12345678";
        
        //Step:
        System.out.println("1. Navigate to QA Railway Website");
     
        Utilities.openToAndSwitchTag(Constant.RAIWAY_URL);
        HomePage homePage = new HomePage();
  
        
        
        System.out.println("2. Click onRegister tab");
        
        LoginPage loginPage = homePage.gotoLoginPage();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        
        
        System.out.println("3.Create account with an already in-use email");
        System.out.println("4.Click on Register button");
        
        registerPage.register(accountAlreadyExists, password, pid);
      
        
        //Verify
        String actualMsg = register.getRegisterErrorMsg(); 
        System.out.println("actualMsg: "+actualMsg);
        String expectedMsg = "This email address is already in use.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");

    }

}
