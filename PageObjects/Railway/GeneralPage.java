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
	private final By centerWelcomeMessage = By.xpath("//*[@id=\"content\"]/h1");
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
	
	protected WebElement getLCenterWelcomeMessage() {
	    return Constant.WEBDRIVER.findElement(centerWelcomeMessage);
	}
	
	// Methods
	public String getWelcomeMessage()
	{

	    return this.getLblWelcomeMessage().getText();
	}
	
	public String getCenterWelcomeMessage()
	{

	    return this.getLCenterWelcomeMessage().getText();
	}

	public LoginPage gotoLoginPage()
	{
	    this.getTabLogin().click();
	    return new LoginPage();
	}
}
