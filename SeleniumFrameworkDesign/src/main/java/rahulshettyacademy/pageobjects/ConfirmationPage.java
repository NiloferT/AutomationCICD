package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//to initialize page factory objects
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String getConfirmationMessage() {
		
		return confirmMessage.getText();
	}
	
	

}
