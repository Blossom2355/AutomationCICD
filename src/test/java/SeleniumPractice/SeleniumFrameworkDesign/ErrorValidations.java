package SeleniumPractice.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumPractice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;


public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName= "ZARA COAT 3";
				
		landingPage.loginApplication("blossom.manchada@gmail.com", "Blossom23");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
		
		@Test(groups= {"ErrorHandling"})
		public void ProductErrorValidation() throws IOException {
			// TODO Auto-generated method stub
			String productName= "ZARA COAT 3";
					
			ProductCatalogue productCatalogue=landingPage.loginApplication("blossom.manchanda@gmail.com", "Blossom@23");
			
			
		//	List<WebElement> products= productCatalogue.getProductList();
			
			productCatalogue.addProductToCart(productName);
			
			CartPage cartPage= productCatalogue.goToCartPage();
							
			Boolean match= cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
			
					

		}
		
		

	}



