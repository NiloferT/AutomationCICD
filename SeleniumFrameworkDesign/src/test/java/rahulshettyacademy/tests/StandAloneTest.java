package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		//add implicit wait on global level
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//launch the browser with below URL
		driver.get("https://rahulshettyacademy.com/client");
		
		//create object of LandingPage
		//LandingPage landingPage = new LandingPage(driver);
		
		//Enter User name
		driver.findElement(By.id("userEmail")).sendKeys("nilofertailor@yahoo.com");
		//Enter password
		driver.findElement(By.id("userPassword")).sendKeys("Nt269@91");
		//click on login button
		driver.findElement(By.id("login")).click();
		
		//for explicit wait 
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
		
		//apply explicit wait for the page contents to load 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//add the desired item in the cart
		//find a common class for all the products  and capture them in a list of elements
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//use java streams to traverse through each element 
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText()
		.equals(productName)).findFirst().orElse(null);
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//apply explicit wait until the "Added to Cart" toast appears on the screen when item is added to cart 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//apply wait until the spinner disappears from the screen
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click on the cart icon
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//from the cart list get the titles of items 
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//traverse the items in cartProducts
		//fetch each product and on that product, compare if you have the correct product 
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		
		//validation for items in the cart
		Assert.assertTrue(match);
		
		//click checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		//select your county from the drop down using actions class
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//selects your country
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//click on the submit button
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//validate the confirmation message
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		

	}

}
