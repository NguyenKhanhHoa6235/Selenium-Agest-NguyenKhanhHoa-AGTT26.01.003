package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;
import Guerillamai.GuerrillaMailPage;

public class RegisterPage extends GeneralPage{
    // Locators 
    private final By _txtEmail = By.xpath("//input[@id='email']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private final By _txtPidOrPassportNumber = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");
    private final By _lblThanksRegisterAccountMsg = By.xpath("//div[@id='content']//h1");
    private final By _lblRegistrationConfirmedMsg = By.xpath("//div[@id='content']/p");
    private final By _lblRegisterErrorMsg = By.xpath("//p[@class='message error']");  
    private final By _lblRegisterErrorPwdMsg = By.xpath("//input[@id='password']//following-sibling::label");
    private final By _lblRegisterErrorPidMsg = By.xpath("//input[@id='pid']//following-sibling::label");
    
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
    
    public WebElement getlblThanksRegisterAccountMsg() {
        return Constant.WEBDRIVER.findElement(_lblThanksRegisterAccountMsg);
    }

    public WebElement getlblRegisterErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorMsg);
    }
    
    public WebElement getlblRegisterErrorPwdMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorPwdMsg);
    }
    
    public WebElement getlblRegisterErrorPidMsg() {
        return Constant.WEBDRIVER.findElement(_lblRegisterErrorPidMsg);
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
    
    
	public String getThanksRegisterAccountMsg() {
	    return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblThanksRegisterAccountMsg)).getText();
	}
	
	public String getRegistrationConfirmedMsg(){
	    return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblRegistrationConfirmedMsg)).getText();
	}
	    
	public String getRegisterErrorMsg() {

	    return this.getlblRegisterErrorMsg().getText();
	}
	
	public String getRegisterErrorPwdMsg() {

	    return this.getlblRegisterErrorPwdMsg().getText();
	}

	
	public String getRegisterErrorPidMsg() {

	    return this.getlblRegisterErrorPidMsg().getText();
	}
	

	public boolean isAtFormRegister() {
		return Constant.WEBDRIVER.getTitle().contains("Register an Account");
	}
	
	public boolean isAtRegistrationConfirmedPage() {
		return Constant.WEBDRIVER.getTitle().contains("Registration Confirmation Page");
	}
	
}