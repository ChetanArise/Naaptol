package test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pojo.browser;
import pom.NaaptolCartPage;
import pom.NaaptolHomePage;
import pom.ProductDetailPage;
@Listeners (test.Listeners.class)
public class ProductDetailTest extends BaseTest
{
	 private WebDriver driver;

	@BeforeMethod
	 public void LaunchApplication()
	 {
	  driver =browser.openbrowser();
	 }
	@Test
	public void VerifyAddToCartUsingProductDetailPage()
	{
		test=reports.createTest("VerifyAddToCartUsingProductDetailPage");
		NaaptolHomePage naaptolHomePage= new NaaptolHomePage(driver);
		naaptolHomePage.SearchValidProduct("mobile phone");
		naaptolHomePage.ClickOnSearch();
		naaptolHomePage.ClickOnProduct();
		
	 Set<String> s= driver.getWindowHandles();
	 Iterator<String> i=s.iterator();
	 
	 while(i.hasNext())
	 {
		 String s1=i.next();
		 driver.switchTo().window(s1);
	 }
	 ProductDetailPage productDetailPage = new ProductDetailPage(driver);
	 String dpn = productDetailPage.GetProductName();
	 double dpp = productDetailPage.getProductPrice();
	 productDetailPage.ClickOnAddToCartButton(0);
	 
	 NaaptolCartPage naaptolCartPage = new NaaptolCartPage(driver);
	 String cpn=naaptolCartPage.getProductname(0);
	 double cpp=naaptolCartPage.GetProductPrice(1);
	 
	 Assert.assertEquals(cpn, dpn);
	 Assert.assertEquals(cpp, dpp);	
	}
	
}
