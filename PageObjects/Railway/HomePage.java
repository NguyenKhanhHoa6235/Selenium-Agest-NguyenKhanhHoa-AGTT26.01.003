package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Constant.Constant;

public class HomePage extends GeneralPage {
	// Locators
	private final By _lblWelcomeMessage = By.xpath("//*[@id=\"content\"]/h1");
	private final By _linkCreateAccount = By.xpath("//div[@id='content']//a[text()='create an account']");
	
	// Elements
	protected WebElement getLCenterWelcomeMessage() {
	    return Constant.WEBDRIVER.findElement(_lblWelcomeMessage);
	}
	
	protected WebElement getlinkCreateAccount() {
	    return Constant.WEBDRIVER.findElement(_linkCreateAccount);
	}
	
	//Method
	public HomePage open() {
		Constant.WEBDRIVER.navigate().to(Constant.RAIWAY_URL);
		return this;
	}
	
	public String getCenterWelcomeMessage()
	{

	    return this.getLCenterWelcomeMessage().getText();
	}
	
	public RegisterPage ClicklinkCreateAccount()
	{
		Utilities.scrollAndClick(getlinkCreateAccount());
		return new RegisterPage();
	}
	
	public boolean isAtHomePage() {
		return Constant.WEBDRIVER.findElement(_lblWelcomeMessage).isDisplayed();
	}
	
	public boolean isLinkCreateAccountDisplayed() {
		Utilities.scrollToElement(getLCenterWelcomeMessage());
	    return getlinkCreateAccount().isDisplayed();
	}
	
	public String getHrefLinkCreateAccount() {
		return getlinkCreateAccount().getAttribute("href");
	}

}