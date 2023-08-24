package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtility;

public class BaseHRMClass {

	public static Properties prop = new Properties();
	public static TimeUtility timepage = new TimeUtility();
	public static WebDriver driver;
	public static Logger log = LogManager.getLogger(BaseHRMClass.class);

	//create constructor of class
	public BaseHRMClass() {

		try {
			FileInputStream file = new FileInputStream("C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\environmentalVar\\Config.properties");
			prop.load(file);

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException a) {
			a.printStackTrace();
		}

	}

	public static void intiation(String browserName) throws Exception {

		if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\utility\\geckodriver.exe");			
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\utility\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.Edge.driver", "C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\utility\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			throw new Exception("Browser name is incorrect");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
	}

	public static void  screeshots(String Filename) throws InterruptedException {
		Thread.sleep(2000);
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(file, new File("C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\screenshots\\Screenshots" + Filename + ".jpg"));
			log.info("Screenshot " + Filename + ".jpg");
		}
		catch(IOException e) {
			log.error("Error in capture screenshot: " + Filename + ".jpg");
			e.printStackTrace();
		}
	}

}














