package pomPackage;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePackage.BaseHRMClass;


public class PomLogin extends BaseHRMClass {

	//object repository
	@FindBy(xpath = "//input[@name=\"username\"]") WebElement Username; //driver.findElement(By.xpath

	@FindBy(xpath = "//input[@name='password']") WebElement Password; 

	@FindBy(xpath = "//button[text()=' Login ']") WebElement Loginbtn; 

	//initiate page elements
	public PomLogin() {
		PageFactory.initElements(driver, this);
	}

	public void typeusername(String username) {
		Username.sendKeys(username);		
	}

	public void typepassword(String password) {
		Password.sendKeys(password);
	}

	public void clickLoginbtn() {
		//explicity wait
		WebDriverWait wait = new WebDriverWait(driver, timepage.pageloadtime); 
		WebElement logbtn = wait.until(ExpectedConditions.elementToBeClickable(Loginbtn));

		logbtn.click();
	}

	public String getTitle() {
		return driver.getTitle();
	}
}
