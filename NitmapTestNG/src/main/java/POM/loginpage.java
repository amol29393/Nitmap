package POM;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.utility;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException; 
public class loginpage {
       @FindBy(id="mat-input-0") private WebElement Email; 
       
       @FindBy(id="mat-input-1") private WebElement password;
       
       @FindBy(id="captcahCanvas") private WebElement captcha;
       
       @FindBy(xpath="id('captcahCanvas')/parent::div//input[@type=\"text\"] ") private WebElement captcha_box;
       
       @FindBy(id="kt_login_signin_submit") private WebElement signin;
     
     public  loginpage(WebDriver driver) {
    	 
       PageFactory.initElements(driver,this); 
	}
       
	public void Enter_email(String email) throws IOException
	{
		Email.sendKeys(utility.get_email(email));
	}
	
	public void Enter_pass(String pass) throws IOException
	{
		password.sendKeys(utility.get_email(pass));
	}
	
	
	public void Enter_Captcha() throws TesseractException 
	{
		try {
		File source = captcha.getScreenshotAs(OutputType.FILE);
	
		File image =new File (System.getProperty("user.dir")+"/screenshot/captcha.png");
		
		FileHandler.copy(source,image);
		
		BufferedImage captchaImage = ImageIO.read(image);

		ColorConvertOp grayscaleOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		grayscaleOp.filter(captchaImage, captchaImage);
		
		MedianFilterOp medianFilterOp = new MedianFilterOp(3);
		medianFilterOp.filter(captchaImage, captchaImage);
		
		ITesseract tess=new Tesseract();
		tess.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
		String text = tess.doOCR(image);
		
		System.out.println(text);
		captcha_box.sendKeys(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Click_Signin()
	{
		signin.click();
	}
}
