package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Class.User;
import Constant.Constant;


public class RegisterTest {
	User userAccount = new User();
	
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");     
        
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window();
        
        
    }
    
    @Test
    public void TC07() {
        System.out.println("TC07 - User can't create account with an already in-use email");
        
        userAccount.setUsername(Constant.USERNAME);
        userAccount.setPassword(Constant.PASSWORD);
        
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage register = homePage.gotoRegisterPage();
        
        register.register(userAccount);
        
        String actualMsg = register.getRegisterErrorMsg();
        String expectedMsg = "This email address is already in use.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
       
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
    }
}
