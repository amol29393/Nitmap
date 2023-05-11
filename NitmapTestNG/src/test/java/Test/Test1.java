package Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import Base.BaseClass;
import net.sourceforge.tess4j.TesseractException;

public class Test1 extends BaseClass{

	
	@Test
	public void add_Cust_test() throws IOException, TesseractException
	{
		p1.Enter_email("email");
		p1.Enter_pass("pass");
		p1.Enter_Captcha();
		p1.Click_Signin();
		
		p2.Add_CustDetails();
	}
	
	
	
	
}
