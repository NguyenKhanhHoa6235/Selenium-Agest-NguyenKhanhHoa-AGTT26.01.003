package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class PasswordResetPage extends GeneralPage{
	// Locators 
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@title='Confirm new password']");
    private final By _txtResetToken = By.xpath("//input[id='resetToken']");
    private final By _btnResetPassword = By.xpath("//input[@type='submit']");  
    private final By _lblErrorMgs = By.xpath("//div[@id='content']//p");
    private final By _lblErrorPasswordMgs = By.xpath("//li[@class='confirm-password']//label[@class='validation-error']");
    
    // Elements
    public WebElement getTxtNewPassword() {
        return Constant.WEBDRIVER.findElement(_txtNewPassword);
    }
    
    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_txtConfirmPassword);
    }
    
    public WebElement getTxtResetToken() {
        return Constant.WEBDRIVER.findElement(_txtResetToken);
    }
    
    public WebElement getBtnResetPassword() {
        return Constant.WEBDRIVER.findElement(_btnResetPassword);
    }	
    
    public WebElement getLblErrorMgs() {
        return Constant.WEBDRIVER.findElement(_lblErrorMgs);
    }	
    
    public WebElement getLblErrorPaswordMgs() {
        return Constant.WEBDRIVER.findElement(_lblErrorPasswordMgs);
    }	
    
    //Method
	public void resetPassword(String newPassword, String confirmPassword) {
		Utilities.scrollToElement(_txtNewPassword);
		Utilities.waitAndSenkey(_txtNewPassword, newPassword);
		Utilities.scrollToElement(_txtConfirmPassword);
		Utilities.waitAndSenkey(_txtConfirmPassword, confirmPassword);
		Utilities.scrollToElement(_btnResetPassword);
		Utilities.waitAndClick(_btnResetPassword);
	}
	
	public String getValueResetToken() {
		return Constant.WEBDRIVER.findElement(_txtResetToken).getAttribute("value");
	}
	
	public boolean isAtPasswordResetPage() {
		return Constant.WEBDRIVER.getTitle().contains("Password Reset");
	}
	
	public String getErrorMgs() {
		Utilities.waitForVisible(_lblErrorMgs);
		Utilities.scrollToElement(_lblErrorMgs);
		return getLblErrorMgs().getText();
	}
	
	public String getErrorPasswordMgs() {
		Utilities.waitForPresence(_lblErrorPasswordMgs);
		Utilities.scrollToElement(_lblErrorPasswordMgs);
		return getLblErrorPaswordMgs().getText();
	}
	
}
