package Guerillamai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class GuerrillaMailPage {
	// Locators 
    private final By _lbnEmailInput = By.xpath("//span[@id='inbox-id']");
    private final By _inpEmailInput = By.xpath("//span[@id='inbox-id']//input");
    private final By _btnSetEmailInput = By.xpath("//span[@id='inbox-id']//button[@class='save button small']");
    private final By _lblEmailAddress = By.xpath("//span[@id='email-widget']");
    private final By _chkScrambleAddress = By.xpath("//input[@id='use-alias']");
    private final By _firstMail = By.xpath("//tbody[@id='email_list']//tr[1]//td[contains(text(),'thanhletraining03@gmail.com')]");
    private final By _confirmationEmail = By.xpath("//h3[@class='email_subject']//following-sibling::div[@class='email_body']//a");
    
    // Elements
    public WebElement getLbnEmailInput()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lbnEmailInput));
    }
    
    public WebElement getInpEmailInput()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_inpEmailInput));
    }
    
    public WebElement getBtnSetEmailInput()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_btnSetEmailInput));
    }
    
    public WebElement getLblEmailAddress()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_lblEmailAddress));
    }
    
    public WebElement getChkScrambleAddress()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_chkScrambleAddress));
    }
    
    public WebElement getFirstMail()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_firstMail));
    }
    
    public WebElement getConfirmationEmail()
    {
    		return Constant.WEBDRIVER.findElement(Utilities.waitForVisible(_confirmationEmail));
    }
    
    //Method
    
    public GuerrillaMailPage open() {
    		Constant.WEBDRIVER.navigate().to(Constant.GUERRILLA_URL);
		return this;
    }
 
    public void setEmailName(String email) {
    		this.getLbnEmailInput().click();
    		this.getInpEmailInput().clear();
    		this.getInpEmailInput().sendKeys(email);
    		this.getBtnSetEmailInput().click();
    }
    
    public String getCreatedEmail() {
    		this.getChkScrambleAddress().click();
    		return this.getLblEmailAddress().getText();
		
	}
    
    public void openFirstMail() {
	    	Utilities.scrollAndClick(Utilities.waitForVisible(_firstMail));
	    	Utilities.scrollToElement(_confirmationEmail);
	    	Utilities.jsClick(_confirmationEmail);
	    	System.out.println("hahaha");	
	}
}
















