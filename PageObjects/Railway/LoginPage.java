package Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Class.User;
import Constant.Constant;

public class LoginPage extends GeneralPage {
    // Locators 
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _lblForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
    
    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }
    
    public WebElement getLblForgotPassword () {
        return Constant.WEBDRIVER.findElement(_lblForgotPassword);
    }
    
	
 // Methods
    public HomePage login(String username, String password) {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new HomePage();
    }
    
    public HomePage login(User user) {     
        return login(user.getUsername(), user.getPassword());
    }
    
   
	public String getLoginErrorMsg()
	{

	    return this.getLblLoginErrorMsg().getText();
	}
	
	public void clickForgotPassword()
	{
	    this.getLblForgotPassword().click();
	}
	
	public boolean isAtLoginPage() 
	{
		return Constant.WEBDRIVER.getTitle().contains("Login");
	}
}