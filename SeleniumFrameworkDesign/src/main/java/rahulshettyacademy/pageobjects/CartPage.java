package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy (css = ".cartSection h3")
	private List <WebElement> productTitles; 
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	public boolean VerifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;		
	}
	
	public CheckoutPage goToCheckOut() {
		checkoutEle.click();
		
		return new CheckoutPage(driver);
	}

}
