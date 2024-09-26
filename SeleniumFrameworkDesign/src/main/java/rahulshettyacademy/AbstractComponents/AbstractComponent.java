package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

//This class will be the parent class 
//It is holding all reusable items 
//we will use concept called inheritance with keyword 'extends'
//to make sure all the other classes have access to items in this parent class
public class AbstractComponent {

	WebDriver driver;

	//we are creating this constructor to catch variables coming from child class 
	public AbstractComponent(WebDriver driver) {
		
		this.driver= driver;
		
		//to initialize page factory objects
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By findBy) {

		// for explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// apply explicit wait for the page contents to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		// for explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// apply explicit wait for the page contents to load
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	public CartPage goToCartPage() {
		
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrdersPage() {
		
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(element));
	}

}
