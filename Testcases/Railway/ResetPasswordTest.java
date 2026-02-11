package Railway;

import java.time.Duration;

//import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.MenuItem;
import Guerillamai.GuerrillaMailPage;

public class ResetPasswordTest extends TestBase {
    @Test
    public void TC010() {
        System.out.println("TC010 - Reset password shows error if the new password is same as current");
        String CurrentPassword = "12345678";
         
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
	    	//tag 2: GuerrillaMail + get email
	    String guerrillaTag = Utilities.openNewTab();
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    Utilities.removeAdsIframes();
	    mailPage.setEmailName(fullEmailAdrress);
	    
	    	System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");  	
	    	System.out.println("7. Click on reset link");
	    	mailPage.openFirstMail();	    	
	    	
	    	System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();
	    PasswordResetPage passwordResetPage = new PasswordResetPage();
	    Assert.assertTrue(passwordResetPage.isAtPasswordResetPage(),"The Password Resetn Page is not displaying.");
	    
	    	System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
	    	System.out.println("9. Click Reset PassSword");
	    	
	    passwordResetPage.resetPassword(CurrentPassword, CurrentPassword);
	    
	    
	    	System.out.println("Verify: Message \"The new password cannot be the same with the current password\" is shown");     
//        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");
//        
//        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }

    
    @Test
    public void TC011() {
        System.out.println("TC011 - Reset password shows error if the new password and confirm password doesn't match");
        String currentPassword = "12345678";
        String newPasssword = "123456789";
        	String confirmNewPasssword = "012345678";
        
	    	System.out.println("Pre-condition: an actived account is existing");
	    	String fullEmailAdrress = registerWithEmailGuerrilla(currentPassword, currentPassword);
	    	Constant.WEBDRIVER.close();    
	    	
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
	    	//tag 2: GuerrillaMail + get email
	    String guerrillaTag = Utilities.openNewTab();
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    mailPage.setEmailName(fullEmailAdrress);
	    
	    	System.out.println("6. Open email with subject contaning \"Please reset your password\" and the email of the account at step 3");  	
	    	System.out.println("7. Click on reset link");
	    	mailPage.openFirstMail();	    	
	    	
	    	System.out.println("Verify: Redirect to Railways page and Form \"Password Change Form\" is shown with the reset password token");
	    WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(Constant.TIMEOUT));
	    wait.until(ExpectedConditions.numberOfWindowsToBe(3)); 
	    Utilities.switchToLastTag();
	    PasswordResetPage passwordResetPage = new PasswordResetPage();
	    Assert.assertTrue(passwordResetPage.isAtPasswordResetPage(),"The Password Resetn Page is not displaying.");
	    
	    	System.out.println("8. Input same password into 2 fields  \"new password\" and \"confirm password\"");
	    	System.out.println("9. Click Reset PassSword");   	
	    passwordResetPage.resetPassword(newPasssword, confirmNewPasssword);
	    
	    
	    	System.out.println("Verify: Message \"The new password cannot be the same with the current password\" is shown");     
//        Assert.assertTrue(homePageOut.isAtHomePage(),"The homepage is not displaying.");
//        
//        Assert.assertFalse(homePageOut.isTagLogOutDisplayed(),"Logout tab should not exist");       
    }
}
