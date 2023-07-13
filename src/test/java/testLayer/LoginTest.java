package testLayer;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import basePackege.BaseHRMClass;
import pomPackage.PomLogin;
import testData.ExcelSheet;
import testData.ReadJson;

public class LoginTest extends BaseHRMClass {

	PomLogin log;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod()
	@Parameters("browserName")
	public void initSetup(String browserName) throws Exception {
		intiation(browserName);
		screeshots("Login");
		log = new PomLogin();
	}
	
	@Test(priority=1)
	public void validateTitle() {
		String actualTitle = log.verifyTitle();
		System.out.println("actualTitle" + actualTitle);
		Assert.assertEquals(actualTitle, "OrangeHRM");
	}
	
	@DataProvider
	public Object[][] Details() throws IOException, ParseException{
		String dataSource = prop.getProperty("dataSource");
		Object[][] result = null;
		
		//if config key=1 user data extract from 'Details.xlsx' Excel file 
		//if config key=2 user data extract from 'Details.json' json file
		if(dataSource.equals("1"))
		{
			result = ExcelSheet.readdata("Sheet1");
		}
		else if (dataSource.equals("2")){
			result = ReadJson.readdata();
		}		
		
		return result;
	}
	
	@Test(priority=2, dataProvider = "Details")
	public void Login(String name, String password) throws InterruptedException {
		Thread.sleep(2000);
		log.typeusername(name);
		log.typepassword(password);
		Thread.sleep(2000);
		log.clickLoginbtn();
	}
	
	@AfterMethod
	public void clos() {
		driver.close();
	}
}
