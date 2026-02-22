package Railway;

import java.time.Duration;

//import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Class.User;
import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
import Guerillamai.GuerrillaMailPage;

public class ResetPasswordTest extends TestBase {
    @Test
    public void TC010() {
        System.out.println("TC010 - Reset password shows error if the new password is same as current");
        
        //Data
        String CurrentPassword = "12345678";
        User userAccount = new User();    
        userAccount.setPassword(CurrentPassword);
        
        //Step
	    	System.out.println("Pre-condition: an actived account is existing");
	    	String fullEmailAdrress = registerWithEmailGuerrilla(CurrentPassword, CurrentPassword); 
	    	
	    	System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
	         
	    	System.out.println("2. Click on \"Forgot Password page\" link");
	    	loginPage.clickForgotPassword();
	    	
	    	System.out.println("3. Enter the email address of the activated account");
	    	ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage();
	    	forgetPasswordPage.inputEmail(fullEmailAdrress);
    	
	    	System.out.println("4. Click on \"Send Instructions\" button");
	    	forgetPasswordPage.clickButtonSendInstructions();
	    	
	    	System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    closeAdsIfPresent();
	    mailPage.setEmailName(fullEmailAdrress);
	    
	    	System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");  	
	    	System.out.println("7. Click on reset link");
	    	mailPage.openEmailRest();	    	
	    	
	    	System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();
	    PasswordResetPage passwordResetPage = new PasswordResetPage();
//	    Assert.assertTrue(passwordResetPage.isAtPasswordResetPage(),"The Password Reset Page is not displaying.");
	    
	    	System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
	    	System.out.println("9. Click Reset PassSword");	    	
	    passwordResetPage.resetPassword(CurrentPassword, CurrentPassword);
   
	    	System.out.println("Verify: Message \"The new password cannot be the same with the current password\" is shown");     
	    	String actualMsg = passwordResetPage.getErrorMgs();
	    String expectedMsg = "The new password cannot be the same with the current password";
	    	
	    Assert.assertEquals(actualMsg, expectedMsg, "Message expected");    
    }
    
    @Test
    public void TC011() {
        System.out.println("TC011 - Reset password shows error if the new password and confirm password doesn't match");
        
        //Data
        String currentPassword = "12345678";
        String newPasssword = "123456789";
        	String confirmNewPasssword = "012345678";
        
        	//Step
	    	System.out.println("Pre-condition: an actived account is existing");
	    	String fullEmailAdrress = registerWithEmailGuerrilla(currentPassword, currentPassword); 
	    	
	    	System.out.println("1. Navigate to QA Railway Login page");
		HomePage homePage = new HomePage();
		homePage.open();
		LoginPage loginPage = homePage.gotoPage(MenuItem.LOGIN, LoginPage.class);
	         
	    	System.out.println("2. Click on \"Forgot Password page\" link");
	    	loginPage.clickForgotPassword();
	    	
	    	System.out.println("3. Enter the email address of the activated account");
	    	ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage();
	    	forgetPasswordPage.inputEmail(fullEmailAdrress);
    	
	    	System.out.println("4. Click on \"Send Instructions\" button");
	    	forgetPasswordPage.clickButtonSendInstructions();
	    	
	    	System.out.println("5. Login to the mailbox (the same mailbox when creating account) ");
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    closeAdsIfPresent();
	    mailPage.setEmailName(fullEmailAdrress);
	    
	    	System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");  	
	    	System.out.println("7. Click on reset link");
	    	mailPage.openEmailRest();	    	
	    	
	    	System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();
	    PasswordResetPage passwordResetPage = new PasswordResetPage();
//	    Assert.assertTrue(passwordResetPage.isAtPasswordResetPage(),"The Password Reset Page is not displaying.");
	      	
	    	System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
	    	System.out.println("9. Click Reset PassSword");   	
	    passwordResetPage.resetPassword(newPasssword, confirmNewPasssword); 
	    
	    	System.out.println(
	    			"Veryfi: Error message \"Could not reset password. Please correct the errors and try again.\" displays above the form.\r\n"
	    			+ "\r\n"
	    			+ "Error message \"The password confirmation did not match the new password.\" displays next to the confirm password field."
	    			);     
	    	String actualErorMsg = passwordResetPage.getErrorMgs();
	    String expecteErrordMsg = "Could not reset password. Please correct the errors and try again.";
	    String actualPasswordErorMsg = passwordResetPage.getErrorPasswordMgs();
	    String expectedPasswordMsg = "The password confirmation did not match the new password.";
 	
	    Assert.assertEquals(actualErorMsg, expecteErrordMsg, "Message expected");     
	    Assert.assertEquals(actualPasswordErorMsg, expectedPasswordMsg, "Message Password error expected"); 
    }
}
