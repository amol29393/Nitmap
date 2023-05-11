package Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import POM.add_customer;
import POM.loginpage;
import Utility.utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	protected WebDriver driver;
	protected loginpage p1;
	protected add_customer p2;
	@BeforeClass
	public void openbrowser() throws IOException
	{
	   if(utility.get_browser_name("Browser").equalsIgnoreCase("chrome"))
	   {
	   ChromeOptions co=new ChromeOptions();
	   co.addArguments("--remote-allow-origins=*");
	   WebDriverManager.chromedriver().setup();
	   //System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/browser/chromedriver.exe");
	   driver=new ChromeDriver(co);
	   }
	   else if (utility.get_browser_name("Browser").equalsIgnoreCase("firefox"))
	   {
		   WebDriverManager.firefoxdriver().setup();
		   driver=new FirefoxDriver();
	   }
	   else if (utility.get_browser_name("Browser").equalsIgnoreCase("edge"))
	   {
		   WebDriverManager.edgedriver().setup();
		   driver=new EdgeDriver();
	   }
	   driver.get(utility.get_URL("url"));
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void initializeOBJ()
	{
		p1=new loginpage(driver);
		p2=new add_customer(driver);
	}
	
}
