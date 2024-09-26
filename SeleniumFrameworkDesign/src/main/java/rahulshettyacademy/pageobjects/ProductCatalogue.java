package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	//before you touch anything in the class
	//the constructor method is the first thing that will execute
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
			
	}
	
	//add the desired item in the cart
	//find a common class for all the products  and capture them in a list of elements
	//List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	//below action method will get the product list
	public List<WebElement> getProductList() {
		
		//System.out.println("Waiting for product list to appear...");
		waitForElementToAppear(productsBy);
		//System.out.println("Product list found.");
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText()
		.equals(productName)).findFirst().orElse(null);
		
		return prod;
		
		
	}
	
	public void AddProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}
	

	}
	
	


