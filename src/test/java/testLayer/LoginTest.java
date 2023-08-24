package testLayer;

import java.io.FileOutputStream;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import basePackage.BaseHRMClass;
import pomPackage.PomLogin;
import testData.ExcelSheet;
import testData.ReadJson;

public class LoginTest extends BaseHRMClass {

	PomLogin plog;

	public LoginTest() {
		super();
	}

	@BeforeMethod()
	@Parameters("browserName") //take from TestNG.xml file
	public void initSetup(String browserName) throws Exception {
		plog = new PomLogin();

		intiation(browserName);
		screeshots("Login screen");		

		FileOutputStream out = new FileOutputStream("C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\environmentalVar\\Config.properties");
		prop.setProperty("browser", "Edge");
		prop.store(out, "changed browser to Edge");
	}

	@Test(priority=1)
	public void validateTitle() {
		String actualTitle = plog.getTitle();
		System.out.println("ActualTitle: " + actualTitle);
		log.info("ActualTitle: " + actualTitle + "Expected title: OrangeHRM");
		Assert.assertEquals(actualTitle, "OrangeHRM");
	}

	@Test(priority=2, dataProvider = "Details")
	public void Login(String name, String password) throws InterruptedException {
		Thread.sleep(2000);
		plog.typeusername(name);
		plog.typepassword(password);
		plog.clickLoginbtn();
		Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] Details() throws IOException, ParseException{
		String dataSource = prop.getProperty("dataSource");
		Object[][] result = null;			
		Object[][] usersProperties = new Object[1][2];

		if(dataSource.equals("1")) //user data from Details.xlsx file
		{
			result = ExcelSheet.readdata("Sheet1");
		}
		else if (dataSource.equals("2")){ //user data from Details.json file
			result = ReadJson.readdata();
		}
		else if (dataSource.equals("3")){ //user data from Config.properties file
			usersProperties[0][0] = prop.getProperty("username");
			usersProperties[0][1] = prop.getProperty("password");
			result = usersProperties;
		}

		return result;
	}

	@AfterMethod
	public void clos() throws InterruptedException {
		driver.close();
		Thread.sleep(2000);
	}
}
