package Railway;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Class.User;
import Common.Utilities;
import Constant.Constant;
import Guerillamai.GuerrillaMailPage;


public class RegisterTest extends TestBase{
	User userAccount = new User();
	
	public String setAndGetEmailGuerrillaMail() {
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    String emailName = Utilities.generateTimestampEmail();
	    
	    mailPage.setEmailName(emailName);
	    String fullEmailAdrress = mailPage.getCreatedEmail();	
	    
	    return fullEmailAdrress;
	}

	
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
        
        //Steps:
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
        RegisterPage registerPage = homePage.gotoRegisterPage();

        
        System.out.println("3. Enter valid email, leave other fields empty");
        System.out.println("4.Click on Register button");
        registerPage.register(fullEmailAdrress, password, pid);
    	
        
        //Verify
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
   		
   		//tag windown 1: open Railway + click on link "create an account"
        System.out.println("1. Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();
        String railwayTab = Constant.WEBDRIVER.getWindowHandle();	
        
        //verify step 1: Home page is shown with guide containing href "create an account" to "Register" page
        String href = homePage.getHrefLinkCreateAccount();
        Assert.assertTrue(homePage.isAtHomePage());
        Assert.assertTrue(href.contains("/Account/Register.cshtml"),"Create an account link does not navigate to Register page"
        );
        
        
        System.out.println("2. Click on \"Create an account\"");
        RegisterPage registerPage = homePage.ClicklinkCreateAccount();

        //Verify: Register page is shown
        Assert.assertTrue(registerPage.isAtFormRegister());

        
        //tag windown 2: GuerrillaMail + get email
        String guerrillaTag = Utilities.openNewTab();
        GuerrillaMailPage mailPage = new GuerrillaMailPage();
        mailPage.open();
        String emailName = Utilities.generateTimestampEmail();
        
        mailPage.setEmailName(emailName);
        String fullEmailAdrress = mailPage.getCreatedEmail();
              
        
        // Back Railway + register
	    Utilities.switchToWindow(railwayTab);
        System.out.println("3. Enter valid information into all fields");
        System.out.println("4. Click on \"Register\" button");
        registerPage.register(fullEmailAdrress, password, pid);
        
        //verify: message "Thank you for registering your account" is shown
        String actualThanksRegisterMsg = registerPage.getThanksRegisterAccountMsg();
        String expectedThanksRegisterMsg = "Thank you for registering your account";
        
        Assert.assertEquals(actualThanksRegisterMsg, expectedThanksRegisterMsg, "Thanks register message is not displayed as expected");

        
	    // back GuerrillaMail + confirmation email
        System.out.println("5. Get email information (webmail address, mailbox and password) and navigate to that webmail");
        System.out.println("6. Login to the mailbox");
        System.out.println("7. Open email with subject containing \"Please confirm your account\"  and the email of the new account at step 3");
        System.out.println("8. Click on the activate link");
        

	    Utilities.switchToWindow(guerrillaTag);
	    mailPage.setEmailName(emailName);
	    mailPage.openFirstMail();
	    
	    //Switch to register Confirm
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();

	    //verify: Redirect to Railways page and message "Registration Confirmed! You can now log in to the site" is shown
        String actualRegisterSuccessMsg = registerPage.getRegistrationConfirmedMsg();
        String expectedRegisterSuccessMsg = "Registration Confirmed! You can now log in to the site";
        
        Assert.assertTrue(registerPage.isAtRegistrationConfirmedPage(),"No at RegistrationConfirmedPage");
        Assert.assertEquals(actualRegisterSuccessMsg, expectedRegisterSuccessMsg, "Registration Confirmed message is not displayed as expected");

    }

}
