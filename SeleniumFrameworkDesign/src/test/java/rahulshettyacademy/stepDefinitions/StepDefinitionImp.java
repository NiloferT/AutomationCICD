package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImp extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		
		landingPage = launchApplication();
	}
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		landingPage.loginApplication(username, password);
	}
	
	@When ("^I add the product (.+) from cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException 
	{
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.AddProductToCart(productName);
		
	}
	@When ("^checkout (.+) and submit the order$")
	public void chekout_submit_order(String productName)
	{
		
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckOut();
		checkoutPage.selectCountry("india");
		//checkoutPage.submitOrder();
		checkoutPage.submitOrder();
		
	}
	
	@Then ("{String} messsage is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	
	
}
