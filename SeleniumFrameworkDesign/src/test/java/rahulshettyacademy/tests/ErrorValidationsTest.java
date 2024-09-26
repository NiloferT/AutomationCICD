package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.ReTry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = ReTry.class)
	
	public void submitOrder() throws IOException, InterruptedException {
		
		
		String productName = "ZARA COAT 3";
		//insert invalid email or password, expected result: test will fail
		landingPage.loginApplication("nilofrtailor@yahoo.com", "t269@91");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	
	public void LoginErrorValidation() throws IOException, InterruptedException {

		
		landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		//landingPage.loginApplication("nilofrtailor@yahoo.com", "t269@91");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		//ProductCatalogue productCatalogue = landingPage.loginApplication("nilofrtailor@yahoo.com", "t269@91");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.AddProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}
	
	

}

