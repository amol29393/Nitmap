package POM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.utility;

public class add_customer {

	 @FindBy(xpath="//*[@id=\"kt_aside_menu\"]/ul/li[2]/a/span") private WebElement My_Customer;
	 
	 @FindBy(xpath="//button//span[text()=' New Customer ']") private WebElement New_Cust_Button;
	
	 @FindBy(xpath="//*[@id=\"mat-input-72\"]") private WebElement Customer_Name;
	 
	 @FindBy(xpath="//button//span[text()=' Save ']") private WebElement Save_Button;
	 
	 
	 public  add_customer(WebDriver driver) {
    	 
	       PageFactory.initElements(driver,this); 
		}
	 
	 
	 public void Add_CustDetails() throws IOException
	 {
		 My_Customer.click();
		 New_Cust_Button.click();
		 Customer_Name.sendKeys(utility.Get_Customer_Name("Name"));
		 Save_Button.click();
	 }
}
