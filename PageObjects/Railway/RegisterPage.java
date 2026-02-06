package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Class.User;
import Common.Utilities;
import Constant.Constant;
import Guerillamai.GuerrillaMailPage;

public class RegisterPage {
    // Locators 
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPidOrPassportNumber = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");

    
    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }

    
    public WebElement getTxtPidOrPassportNumber() {
        return Constant.WEBDRIVER.findElement(_txtPidOrPassportNumber);
    }
    
    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }

    public WebElement getlblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMsg);
    }

    
    
    //Method
    public void register(String email, String password, String pid)
    {
    		getTxtEmail().sendKeys(email);
    		getTxtPassword().sendKeys(password);
    		getTxtConfirmPassword().sendKeys(password);
    		getTxtPidOrPassportNumber().sendKeys(pid);
    		
    		Utilities.scrollAndClick(getBtnRegister());   		
    }
    
    public String registerWithEmailGuerrilla(String password, String pid)
    {
    		//tag 1: Railway
		HomePage homePage = new HomePage();
	    homePage.open();
	    String railwayTab = Constant.WEBDRIVER.getWindowHandle();
	    
	    //tag 2: GuerrillaMail + get email
	    String guerrillaTag = Utilities.openNewTab();
	    GuerrillaMailPage mailPage = new GuerrillaMailPage();
	    mailPage.open();
	    String emailName = Utilities.generateTimestampEmail();
	    
	    mailPage.setEmailName(emailName);
	    String fullEmailAdrress = mailPage.getCreatedEmail();
	    
	    System.out.println(fullEmailAdrress);
	    
	    // Back Railway + register
	    Utilities.switchToWindow(railwayTab);
	
	    RegisterPage registerPage = homePage.gotoRegisterPage();
	    registerPage.register(fullEmailAdrress, password, pid);
	
	    // back GuerrillaMail + confirmation email
	    Utilities.switchToWindow(guerrillaTag);
	    mailPage.setEmailName(emailName);
	    mailPage.openFirstMail();
	    
	    return fullEmailAdrress;	    
    }
    
	public String getRegisterErrorMsg()
	{

	    return this.getlblRegisterErrorMsg().getText();
	}
}
