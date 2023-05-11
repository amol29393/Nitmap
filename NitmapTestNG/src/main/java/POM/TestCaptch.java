package POM;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class TestCaptch {
   @SuppressWarnings("deprecation")
public static void main(String[] args) {
	   WebDriverManager.chromedriver().setup();
	   //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe"); // path to chrome driver
      WebDriver driver = new ChromeDriver();

      driver.get("https://testffc.nimapinfotech.com/"); // replace with the website URL that has the CAPTCHA
	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

      // Locate the CAPTCHA element
      WebElement captcha = driver.findElement(By.id("captcahCanvas"));

      // Get the CAPTCHA image source and store it in a string variable
      String captchaSrc = captcha.getAttribute("src");

      // Call a method to solve the CAPTCHA using an OCR library (such as Tesseract)
      String captchaText = solveCaptcha(driver, captchaSrc);

      // Enter the solved CAPTCHA value in the input field
      WebElement captchaInput = driver.findElement(By.xpath("id('captcahCanvas')/parent::div//input[@type=\\\"text\\\"]"));
      captchaInput.sendKeys(captchaText);

      
   }

   private static String solveCaptcha(WebDriver driver, String captchaId) {
	   ITesseract tesseract = new Tesseract();
	   System.setProperty("TESSDATA_PREFIX", "C:\\Users\\Amol\\eclipse-workspace\\NitmapTestNG\\tessdata\\eng.traineddata");


	    // Set the path to the Tesseract executable
	   // tesseract.setDatapath("C:\\Users\\Amol\\eclipse-workspace\\NitmapTestNG\\tessdata\\eng.traineddata");
	   
	   File file = new File("C:\\Users\\Amol\\eclipse-workspace\\NitmapTestNG\\tessdata\\eng.traineddata");
	   System.out.println(file.exists());

	    // Download the language data for the OCR engine
	    tesseract.setLanguage("eng");

	    try {
	        // Get the CAPTCHA element and take a screenshot
	        WebElement captchaElement = driver.findElement(By.id("captcahCanvas"));
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        // Get the position and dimensions of the CAPTCHA element
	        org.openqa.selenium.Point location = captchaElement.getLocation();
	        org.openqa.selenium.Dimension size = captchaElement.getSize();

	        // Crop the screenshot to only include the CAPTCHA element
	        BufferedImage captchaImage = ImageIO.read(screenshot);
	        BufferedImage croppedImage = captchaImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());

	        // Save the cropped image locally
	        File captchaFile = new File("captcha.png");
	        ImageIO.write(croppedImage, "png", captchaFile);

	        // Use Tesseract to recognize text from the CAPTCHA image
	        String captchaText = tesseract.doOCR(captchaFile);

	        // Remove any non-alphanumeric characters from the recognized text
	        captchaText = captchaText.replaceAll("[^a-zA-Z0-9]", "");

	        return captchaText;
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    } catch (TesseractException e2) {
	        e2.printStackTrace();
	    }

	    return null;
	}
	}
	
      // Code to solve the CAPTCHA using an OCR library goes here
      // This method should return the solved CAPTCHA value as a string

