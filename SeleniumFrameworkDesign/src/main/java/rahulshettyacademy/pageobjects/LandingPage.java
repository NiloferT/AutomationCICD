package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

//below we are inheriting methods of AbstractComponent parent class in to this child class
public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	//before you touch anything in the class
	//the constructor method is the first thing that will execute
	public LandingPage(WebDriver driver) {
		
		//to send child class variables to parent class we use super keyword
		//we want to send this driver from this child class to parent class
		//in parent class we will create a constructor to catch any variables we send 
		super(driver);
		
		this.driver=driver;
		
		//to initialize page factory objects
		PageFactory.initElements(driver, this);
			
	}
	
	//PageFactory
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//below code does the same operation as above line of code 
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//action method
	public ProductCatalogue loginApplication(String email, String pw) {
		
		userEmail.sendKeys(email);
		password.sendKeys(pw);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo() {
		
		//launch the browser with below URL
		driver.get("https://rahulshettyacademy.com/client");
	
		
		
	}
}

