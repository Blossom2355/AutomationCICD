package SeleniumPractice.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	
		WebDriver driver;
		public LandingPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		//WebElement userEmail= driver.findElement(By.id("userEmail"));
		//PageFactory
		
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		//driver.findElement(By.id("userPassword"))
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		//driver.findElement(By.id("login"))  
		@FindBy(id="login")       //if it is xpath then xpath= if it is css then css=
		WebElement submit;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		public ProductCatalogue  loginApplication(String email, String password) {
			
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);	
			submit.click();
			ProductCatalogue productCatalogue= new ProductCatalogue(driver);
			return productCatalogue;
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client/");
		}
		
		public String getErrorMessage() {
			
			waitforWebElementToAppear(errorMessage);
			return errorMessage.getText();
			
			
		}

	}


