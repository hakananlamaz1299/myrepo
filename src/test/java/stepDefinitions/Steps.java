package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Steps {
	WebDriver driver;

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jforce_hakana\\Desktop\\deneme\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.shop.demoqa.com");
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String arg1)  {
		driver.navigate().to("http://shop.demoqa.com/?s=" + arg1 + "&post_type=product");
	}

	@When("^choose to buy the first item$")
	public void choose_to_buy_the_first_item() {
		List<WebElement> items = driver.findElements(By.cssSelector(".noo-product-inner"));
		items.get(0).click();

		driver.navigate().to("http://shop.demoqa.com/product/mustard-strappy-frill-ribbed-mini-dress/");	
	}

	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart(){
		WebElement colorTable = driver.findElement(By.cssSelector("table.variations"));

		WebElement colorElement = colorTable.findElement(By.cssSelector("#pa_color"));
		Select select = new Select(colorElement);
		select.selectByIndex(2);

		WebElement sizeElement = colorTable.findElement(By.cssSelector("#pa_size"));
		Select select2 = new Select(sizeElement);
		select2.selectByIndex(2);

		WebElement quantity = driver.findElement(By.cssSelector("button.qty-increase"));
		quantity.click();

		WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
		addToCart.click();
		
		WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
		cart.click();

		WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
		continueToCheckout.click();		
	}

	@When("^enter personal details on checkout page$")
	public void enter_personal_details_on_checkout_page() {
		WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
		firstName.sendKeys("Hakan");

		WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
		lastName.sendKeys("Anlamaz");

		List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-drop ul li"));
		for (WebElement country : countryList) {
			if (country.getText().equals("İstanbul")) {
				country.click();
				break;
			}
		}

		WebElement city = driver.findElement(By.cssSelector("#billing_city"));
		city.sendKeys("İstanbul");

		WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
		address.sendKeys("Fatih Sultan Mehmet Mah. Poligon Cd. Buyaka 2 Sitesi Kule:1 Kat:7");

		WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
		postcode.sendKeys("34000");

		WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
		emailAddress.sendKeys("hakan@gmail.com");

		WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
		phone.sendKeys("02124567855");
	}

//	@When("^select payment method as \"([^\"]*)\" payment$")
//	public void select_payment_method_as_payment(String arg1){
//		List<WebElement> paymentMethod = driver.findElements(By.cssSelector("ul.wc_payment_methods li"));
//		paymentMethod.get(0).click();
//	}

	@When("^place the order$")
	public void place_the_order() {
		WebElement acceptTC = driver.findElement(By.cssSelector("#terms"));
		acceptTC.click();

		WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
		placeOrder.submit();
	}	

}