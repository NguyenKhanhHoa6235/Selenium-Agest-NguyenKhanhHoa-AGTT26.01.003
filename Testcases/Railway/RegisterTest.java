package Railway;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Class.User;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
import Guerillamai.GuerrillaMailPage;


public class RegisterTest extends TestBase{
    @Test
    public void TC07() {   
    		//Data
		//Data
        User userAccount = new User();    
        userAccount.setPassword("74185296");
        
        System.out.println("TC07 - User can't create account with an already in-use email");
        
    		System.out.println("Pre-condition: an actived account is existing");
    		
    		RegisterPage register = new RegisterPage();
    		String fullEmailAdrress = registerWithEmailGuerrilla(userAccount.getPassword(),userAccount.getPassword());
    		
    		//data
    		String accountAlreadyExists = fullEmailAdrress;
        String password = "12345678";
        String pid = "12345678";
        
        //Steps:
        System.out.println("1. Navigate to QA Railway Website");      
        System.out.println("2. Click onRegister tab");
        HomePage homePage = new HomePage(); 
        homePage.open();
        RegisterPage registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);
               
        System.out.println("3.Create account with an already in-use email");
        System.out.println("4.Click on Register button");
        
        registerPage.register(accountAlreadyExists, password, pid);
              
        //Verify
        String actualMsg = registerPage.getRegisterErrorMsg(); 
        System.out.println("actualMsg: "+actualMsg);
        String expectedMsg = "This email address is already in use.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
       
    @Test
    public void TC08() {
        System.out.println("TC08 - User can't create account while password and PID fields are empty");
        
        //data:
        String fullEmailAdrress = setAndGetEmailGuerrillaMail();
        String password = "";
        String pid = "";
   		System.out.println("nguyenkhanh hoa: "+ fullEmailAdrress);
   		
        //Steps:
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
        
        System.out.println("2. Click on Register tab");
        RegisterPage registerPage = homePage.gotoPage(MenuItem.REGISTER, RegisterPage.class);;
      
        System.out.println("3. Enter valid email, leave other fields empty");
        System.out.println("4.Click on Register button");
        registerPage.register(fullEmailAdrress, password, pid);
    	     
        //Verify
        System.out.println("Message \"There're errors in the form. Please correct the errors and try again.\" appears above the form.\r\n"
        		+ "\r\n"
        		+ "Next to password fields, error message \"Invalid password length.\" displays\r\n"
        		+ "\r\n"
        		+ "Next to PID field, error message \"Invalid ID length.\" displays");
        Assert.assertTrue(registerPage.isAtFormRegister());
        
        String actualErrorMsg = registerPage.getRegisterErrorMsg();
        String actualErrorPwdMsg = registerPage.getRegisterErrorPwdMsg();
        String actualErrorPidMsg = registerPage.getRegisterErrorPidMsg();       

        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";
        String expectedErrorPwdMsg = "Invalid password length.";
        String expectedErrorPidMsg = "Invalid ID length.";   
        
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
        Assert.assertEquals(actualErrorPwdMsg, expectedErrorPwdMsg, "Error password message is not displayed as expected");
        Assert.assertEquals(actualErrorMsg, expectedErrorPidMsg, "Error ID message is not displayed as expected");
    }
       
    @Test
    public void TC09() {
        System.out.println("TC09 - User create and activate account");
        
        //data:
        String password = "12345678";
        String pid = "12345678";
   		
        //Steps:
        //GuerrillaMail + get email
        GuerrillaMailPage mailPage = new GuerrillaMailPage();
        mailPage.open();
        closeAdsIfPresent();
        String emailName = Utilities.generateTimestampEmail();
        
        mailPage.setEmailName(emailName);
        String fullEmailAdrress = mailPage.getCreatedEmail();
        
        System.out.println(fullEmailAdrress);
        
	    	//Railway + click on link "create an account" + register
        System.out.println("1. Navigate to QA Railway Website");
	    	HomePage homePage = new HomePage();
	    	homePage.open();	
	    	//verify step 1: 
	    	System.out.println("verify: Home page is shown with guide containing href \"create an account\" to \"Register\" page");
	    	String href = homePage.getHrefLinkCreateAccount();
        Assert.assertTrue(homePage.isAtHomePage());
        Assert.assertTrue(href.contains("/Account/Register.cshtml"),"Create an account link does not navigate to Register page");
	    	
        System.out.println("2. Click on \"Create an account\"");
        System.out.println("3. Enter valid information into all fields");
        System.out.println("4. Click on \"Register\" button");
        
        RegisterPage registerPage = homePage.ClicklinkCreateAccount();
	    registerPage.register(fullEmailAdrress, password, pid);

        // back GuerrillaMail + confirmation email
        System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
        System.out.println("6. Login to the mailbox");
        System.out.println("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
        System.out.println("8. Click on the activate link");   
        mailPage.open();
        closeAdsIfPresent();
        mailPage.setEmailName(emailName);
        mailPage.openFirstMail();

        //Switch to Railway
        	Utilities.switchToNewWindow();
  
	    //verify: Redirect to Railways page and message "Registration Confirmed! You can now log in to the site" is shown
        System.out.println("Redirect to Railways page and message \"Registration Confirmed! You can now log in to the site\" is shown");
	    String actualRegisterSuccessMsg = registerPage.getRegistrationConfirmedMsg();
        String expectedRegisterSuccessMsg = "Registration Confirmed! You can now log in to the site";
        
        Assert.assertTrue(registerPage.isAtRegistrationConfirmedPage(),"No at RegistrationConfirmedPage");
        Assert.assertEquals(actualRegisterSuccessMsg, expectedRegisterSuccessMsg, "Registration Confirmed message is not displayed as expected");
    }
}
