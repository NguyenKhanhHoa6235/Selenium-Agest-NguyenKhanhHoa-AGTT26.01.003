package Railway;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public abstract class GeneralPage {
	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	private final By tabFAQ = By.xpath("//div[@id='menu']//a[@href=\"/Page/FAQ.cshtml\"]");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href=\"/Account/Register.cshtml\"]");
		
	// Elements
	protected WebElement getTabLogin() {
	    return Constant.WEBDRIVER.findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
	    return Constant.WEBDRIVER.findElement(tabLogout);
	}

	protected WebElement getLblWelcomeMessage() {
	    return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	
	protected WebElement getTagFAQ() {
	    return Constant.WEBDRIVER.findElement(tabFAQ);
	}
	
	protected WebElement getTagRegister() {
	    return Constant.WEBDRIVER.findElement(tabRegister);
	}
	
	// Methods
	public String getWelcomeMessage()
	{

	    return this.getLblWelcomeMessage().getText();
	}
	

	public LoginPage gotoLoginPage()
	{
	    this.getTabLogin().click();
	    return new LoginPage();
	}
	
	public HomePage gotoLogoutPage()
	{
	    this.getTabLogout().click();
	    return new HomePage();
	}
	
	public boolean isTagLogOutDisplayed()
	{
		return Constant.WEBDRIVER.findElements(tabLogout).size() > 0;
	}
	
	public FAQPage gotoFAQPage()
	{
	    this.getTagFAQ().click();
	    return new FAQPage();
	}
	
	public RegisterPage gotoRegisterPage()
	{
	    this.getTagRegister().click();
	    return new RegisterPage();
	}
}
