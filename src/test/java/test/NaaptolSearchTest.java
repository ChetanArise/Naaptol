package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pojo.browser;
import pom.NaaptolHomePage;
@Listeners (test.Listeners.class)
public class NaaptolSearchTest extends BaseTest
{
    @BeforeMethod
    public void LaunchApplication()
    {
   	 driver =browser.openbrowser();
    }
    @Test
    public void VerifyIfUserIsAbleToPerformValidSearch()
    {
    	test=reports.createTest("VerifyIfUserIsAbleToPerformValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.SearchValidProduct("Mobile Phone");
    	naaptolHomePage.ClickOnSearch();
    	Assert.assertTrue(driver.getTitle().contains("Mobile Phone"));
    	Assert.assertTrue(naaptolHomePage.ListOfSearchedProducts()>0);
    }
    @Test
    public void VerifyIfUserIsAbleToPerformInValidSearch()
    {
    	test=reports.createTest("VerifyIfUserIsAbleToPerformInValidSearch");
    	NaaptolHomePage naaptolHomePage = new NaaptolHomePage(driver);
    	naaptolHomePage.SearchInvalidProduct("iPhone");
    	naaptolHomePage.ClickOnSearch();
    	Assert.assertTrue(driver.getTitle().contains("iPhone"));
    	Assert.assertEquals(naaptolHomePage.ListOfSearchedProducts(),0);
    }

}
