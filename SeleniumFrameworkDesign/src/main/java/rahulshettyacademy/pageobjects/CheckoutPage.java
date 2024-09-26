package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//to initialize page factory objects
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		//select your county from the drop down using actions class
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
			
		//selects your country
		selectCountry.click();	
	}
	
	public ConfirmationPage submitOrder() {
		
		//click on the submit button which will lead to confirmation page
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
