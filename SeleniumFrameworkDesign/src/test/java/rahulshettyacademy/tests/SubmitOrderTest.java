package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test (dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.AddProductToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage();
		
	
		Boolean match = cartpage.VerifyProductDisplay(productName);
		//validations cannot go in page object files
		//validation stay in test case
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckOut();
		checkoutPage.selectCountry("india");
		checkoutPage.submitOrder();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
		
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("nilofertailor@yahoo.com", "Nt269@91");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//create two dimensional array
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}

}

