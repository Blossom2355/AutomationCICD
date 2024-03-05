g dqwv`11`wd vvv	1wAe455r                    package SeleniumPractice.SeleniumrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumPractice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	 public ProductCatalogue productCatalogue;
	 public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		//code
		landingPage = launchApplication();
	}
	
	 @Given ("^Logged in with username {.+} and password {.+}$")
	 public void logged_in_username_and_password(String username, String password) {
		  productCatalogue=landingPage.loginApplication(username,password);
	 }
	 
	 @When("^When I add product {.+} from Cart $")
	 public void I_add_product_to_cart(String productName) {
		 List<WebElement> products= productCatalogue.getProductList();
			
			productCatalogue.addProductToCart(productName);
		 
	 }
	 
	 @When("^And Checkout {.+} and submit the order$")
	 public void checkout_submit_order(String productName) {
		 CartPage cartPage= productCatalogue.goToCartPage();
			
			
			
			Boolean match= cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			
			CheckoutPage checkoutPage= cartPage.goToCheckout();
			checkoutPage.selectCountry("india");
			confirmationPage= checkoutPage.submitOrder();
		 
	 }
	 
	 @Then("{string} message is displayed on ConirmationPage")
	 public void message_displayed_on_confirmation_page(String string) {
		 String confirmMessage= confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then("^\"([^\"*)\"message is displayed$")
	 public void something_message_is_displayed(String StrgArg1) throws Throwable{
		 Assert.assertEquals(StrgArg1, landingPage.getErrorMessage());
		 driver.close();	 }
	 

}
