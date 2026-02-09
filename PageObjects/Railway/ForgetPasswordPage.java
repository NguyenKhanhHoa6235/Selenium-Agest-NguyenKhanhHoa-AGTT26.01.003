package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class ForgetPasswordPage {
    // Locators 
    private final By _inputEmail = By.xpath("//input[@id='email']");
    private final By _btnSendInstructions = By.xpath("//input[@value='Send Instructions']");
    
    // Elements
    public WebElement getInputEmail() {
        return Constant.WEBDRIVER.findElement(_inputEmail);
    }
    
    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }
	
    // Methods
	public void inputEmail(String email) {
		getInputEmail().sendKeys(email);
	}
	
	public void clickButtonSendInstructions() {
		getBtnSendInstructions().click();
	}

}
