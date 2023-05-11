package Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import net.bytebuddy.utility.RandomString;

public class utility {
	
	static Properties prop;
	static FileInputStream file;
		
	public static void load_file() throws IOException
	{
		 prop=new Properties();
		
		 file=new FileInputStream(System.getProperty("user.dir")+"/properties/url.properties");
		 prop.load(file);
	}
	public static String get_URL(String url) throws IOException 
	{
			
		   load_file();
			
			String Url = prop.getProperty("url");
		
			return Url;
			
	}
	public static String get_browser_name(String browser) throws IOException 
	{

			load_file();
			String Browser=prop.getProperty("browser");
			
			return Browser;
	}
	public static String get_email(String Email) throws IOException 
	{
		
			load_file();
			String email=prop.getProperty("Email");
			
			return email;
	}
		public static String get_pass(String Pass) throws IOException
	{
			
			load_file();
			String pass=prop.getProperty("Pass");
			
			return pass;
	}
		
		public static String Get_Customer_Name(String Name) throws IOException
		{
				
				load_file();
				String name=prop.getProperty("Name");
				
				return name;
		}	
		
//		public static void capture_screenshot(WebDriver d,int TCID) throws IOException
//	{
//			
//			File source = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
//			
//			String random=RandomString.make(2);
//			
//			File dest=new File(System.getProperty("user.dir")+"/SCREENSHOTS/scrnshot"+random+".PNG");
//			
//			FileHandler.copy(source, dest);
//	}
		

}