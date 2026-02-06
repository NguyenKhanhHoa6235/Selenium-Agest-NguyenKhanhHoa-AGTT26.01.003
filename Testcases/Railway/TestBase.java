package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Constant.Constant;

public class TestBase {
	@BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");      
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window();      
    }
	
	@AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
//        Constant.WEBDRIVER.quit();
    }
	
}
