package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class PasswordResetPage extends GeneralPage{
	// Locators 
    private final By _txtNewPassword = By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPassword = By.xpath("//input[@id='@confirmPassword']");
    private final By _txtResetToken = By.xpath("//input[id='resetToken']");
    private final By _btnResetPassword = By.xpath("//input[@type='submit']");
    
    
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
	
    
    //Method
	public void resetPassword(String newPassword, String confirmPassword) {
		Utilities.waitAndSenkey(_txtNewPassword, newPassword);
		Utilities.waitAndSenkey(_txtConfirmPassword, confirmPassword);
		Utilities.waitAndClick(_btnResetPassword);
	}
	
	public String getValueResetToken() {
		return Constant.WEBDRIVER.findElement(_txtResetToken).getAttribute("value");
	}
	
	public boolean isAtPasswordResetPage() {
		return Constant.WEBDRIVER.getTitle().contains("Password Reset");
	}
	
}
