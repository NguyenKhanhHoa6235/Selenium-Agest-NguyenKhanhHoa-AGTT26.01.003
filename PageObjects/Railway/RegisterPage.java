package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Class.User;
import Constant.Constant;

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
    		this.getTxtEmail().sendKeys(email);
    		this.getTxtPassword().sendKeys(password);
    		this.getTxtConfirmPassword().sendKeys(password);
    		this.getTxtPidOrPassportNumber().sendKeys(pid);
    		
    		this.getBtnRegister().click();
    }
    
    public void register(User user)
    {
    		this.getTxtEmail().sendKeys(user.getUsername());
    		this.getTxtPassword().sendKeys(user.getPassword());
    		this.getTxtConfirmPassword().sendKeys(user.getPassword());
    		this.getTxtPidOrPassportNumber().sendKeys("");
    		
    		this.getBtnRegister().click();
    }
    
	public String getRegisterErrorMsg()
	{

	    return this.getlblRegisterErrorMsg().getText();
	}
}
