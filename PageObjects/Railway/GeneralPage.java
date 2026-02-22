package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Constant.Constant;
import Constant.MenuItem;

public abstract class GeneralPage {
	//Variables
	private static final String MENU_ITEM_XPATH ="//div[@id='menu']//span[normalize-space()='%s']";

	// Locators
	private final By _tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By _tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By _tabFAQ = By.xpath("//div[@id='menu']//a[@href=\"/Page/FAQ.cshtml\"]");
	private final By _tabRegister = By.xpath("//div[@id='menu']//a[@href=\"/Account/Register.cshtml\"]");
	private final By _tagBookTicket = By.xpath("//div[@id='menu']//span[text()='Book ticket']");
	private final By _lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	
	// Elements
	protected WebElement getTabLogin() {
	    return Constant.WEBDRIVER.findElement(_tabLogin);
	}

	protected WebElement getTabLogout() {
	    return Constant.WEBDRIVER.findElement(_tabLogout);
	}

	protected WebElement getLblWelcomeMessage() {
	    return Constant.WEBDRIVER.findElement(_lblWelcomeMessage);
	}
	
	protected WebElement getTagFAQ() {
	    return Constant.WEBDRIVER.findElement(_tabFAQ);
	}
	
	protected WebElement getTagRegister() {
	    return Constant.WEBDRIVER.findElement(_tabRegister);
	}
	
	protected WebElement getTagBookTicket() {
	    return Constant.WEBDRIVER.findElement(_tagBookTicket);
	}
	
	// Methods
	public String getWelcomeMessage() {

	    return this.getLblWelcomeMessage().getText();
	}

    protected By getMenuItemLocator(MenuItem menu) {
    		return By.xpath(String.format(MENU_ITEM_XPATH, menu.getDisplayText()));
    }
    
    protected <T> T gotoPage(MenuItem menu, Class<T> pageClass) {

        Constant.WEBDRIVER.findElement(getMenuItemLocator(menu)).click();

        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot create page instance: " + pageClass.getSimpleName(), e);
        }
    }
    
	public boolean isTagLogOutDisplayed() {
		return Constant.WEBDRIVER.findElements(_tabLogout).size() > 0;
	}
}