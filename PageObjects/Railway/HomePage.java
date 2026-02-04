package Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;

public class HomePage extends GeneralPage {
	// Locators
	private final By centerWelcomeMessage = By.xpath("//*[@id=\"content\"]/h1");
	
	// Elements
	protected WebElement getLCenterWelcomeMessage() {
	    return Constant.WEBDRIVER.findElement(centerWelcomeMessage);
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
	
	public boolean isAtHomePage() {
		return Constant.WEBDRIVER.findElement(centerWelcomeMessage).isDisplayed();
	}
}
