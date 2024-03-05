package SeleniumPractice.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumPractice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumPractice.SeleniumFrameworkDesign.TestComponents.Retry;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.OrderPage;
import SeleniumPractice.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;


public class StandAloneTest2 extends BaseTest {
	
	//public String productName= "ZARA COAT 3";

	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
				
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		
	//	List<WebElement> products= productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(input.get("product"));
		
		CartPage cartPage= productCatalogue.goToCartPage();
		
		
			
		Boolean match= cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage= cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage= checkoutPage.submitOrder();
		String confirmMessage= confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}
	
	// To verify ZARA COAT 3 is displaying in the orders page.
	@Test(dependsOnMethods= {"submitOrder"}, retryAnalyzer=Retry.class)
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("blossom.manchanda@gmail.com", "Blossom@23");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "blossom.manchanda@gmail.com");
//		map.put("password", "Blossom@23");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "blossom.basrai@gmail.com");
//		map1.put("password", "Blossom@23");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumPractice\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)} };
		
	}
}


