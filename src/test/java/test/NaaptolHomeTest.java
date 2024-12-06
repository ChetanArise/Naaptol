package test;


import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pojo.browser;
import pom.NaaptolHomePage;
import pom.NaaptolQuickView;
import pom.ProductDetailPage;
@Listeners (test.Listeners.class)
public class NaaptolHomeTest extends BaseTest
{
	 @BeforeMethod
	 public void LaunchApplication()
	 {
	  driver =browser.openbrowser();
	 }
	 @Test
	 public void VerifyOnClickingShoppingCategories()
	 {
		 test=reports.createTest("VerifyOnClickingShoppingCategories");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
		 naaptolHomePage.VerifyShoppingCategories(driver);
		 Assert.assertTrue(naaptolHomePage.ShoppingCategoryListDisplayed());
	 }
	 @Test
	 public void VerifyProductDetailsInQuickView()
	 {
		 test=reports.createTest("VerifyProductDetailsInQuickView");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.SearchValidProduct("mobile phone");
		 naaptolHomePage.ClickOnSearch();
		 naaptolHomePage.MouseHoverOnProduct(driver,0);
		 String hpn =naaptolHomePage.GetProductName();
		 
		 double hpp=naaptolHomePage.getProductPrice();
		 System.out.println(hpp);
		 		 
		 NaaptolQuickView naaptolQuickView = new NaaptolQuickView(driver);
		 naaptolQuickView.ClickOnQuickView();
		 String qpn=naaptolQuickView.GetQuickProductName();
		 double qpp =naaptolQuickView.GetQuickProductPrice();
		 
		 Assert.assertEquals(qpn,hpn);
		 Assert.assertEquals(qpp,hpp);
		 
	 }
	 @Test
	 public void verifyProductDetailsInNewTab()
	 {
		 test=reports.createTest("verifyProductDetailsInNewTab");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.SearchValidProduct("mobile phone");
		 naaptolHomePage.ClickOnSearch();
		 naaptolHomePage.MouseHoverOnProduct(driver,0);
		 String hpn =naaptolHomePage.GetProductName();
		 double hpp=naaptolHomePage.getProductPrice();
		 
		 naaptolHomePage.ClickOnProduct();
		 
		 Set<String> s= driver.getWindowHandles();
		 Iterator<String> i=s.iterator();
		 
		 while(i.hasNext())
		 {
			 String s1=i.next();
			 driver.switchTo().window(s1);
			 String title =driver.getTitle();
			 
			 if(title.contains("Buy Dual Sim"))
			 {
				 ProductDetailPage productDetailPage= new ProductDetailPage(driver);
				 String dpn = productDetailPage.GetProductName();
				 double dpp = productDetailPage.getProductPrice();
				Assert.assertEquals(dpn,hpn);
				Assert.assertEquals(hpp,dpp);
				break;
			 }
		 }
		 
	 }
	 @Test
	 public void VerifySortFeature() throws InterruptedException
	 {
		 test=reports.createTest("VerifySortFeature");
		 NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver); 
		 naaptolHomePage.SearchValidProduct("mobile phone");
		 naaptolHomePage.ClickOnSearch();
		 naaptolHomePage.ClickOnSort();
		 Thread.sleep(2000);
		 naaptolHomePage.SelectSortOption("rated");
		 
		
	 }
	 
	 

}
