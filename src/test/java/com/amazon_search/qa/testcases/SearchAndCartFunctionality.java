package com.amazon_search.qa.testcases;



import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon_searchandcart.qa.base.Base;





public class SearchAndCartFunctionality extends Base{
	
	public WebDriver driver;
	
	
	
	
	
	
	
	@BeforeMethod
	public void setup() {
		

		driver=initializeBrowserAndOpenApplicationURL("Chrome");
				
	}
	
	


	@Test(priority=1)
	public void verifyValidProductSearch() {
		
		
		
		
		//searching the valid  product over URL
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Apple iPhone 14 Pro Max (256 GB) - Deep Purple");
		
		//Clicking on search Button
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		//verifying the result
		Assert.assertTrue(driver.findElement(By.linkText("Apple iPhone 14 Pro Max (256 GB) - Deep Purple")).isDisplayed());
       
		
		
	}
	
	@Test(priority=2)
	public void verifyAddingProductToCart() throws InterruptedException {
		
		
		
		
		//searching the valid  product over search option
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Apple iPhone 14 Pro Max (256 GB) - Silver");
		
		//Clicking on search Button
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		//click on search Result
		driver.findElement(By.linkText("Apple iPhone 14 Pro Max (256 GB) - Silver")).click();
		
		//Switching the Tab
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));


		//Adding Product to cart
       driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
       
       //verify that product is added to cart
       String actualMessage= driver.findElement(By.xpath("//b[contains(text(),'Cart subtotal')]")).getText();
       Thread.sleep(3000);
       Assert.assertEquals(actualMessage,"Cart subtotal");
      
    
	}
	
	@Test(priority=3)
	public void verifyInvalidProductSearch() {
		
		
		
		
		//searching the valid  product over search option
       driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("00000000000000000000000000");
		
		//click on search button
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		//Verifying the results 
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'No results for')]")).isDisplayed());
       
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Try checking your spelling or use more general ter')]")).isDisplayed());
	       
	}

	@AfterMethod
	public void tearDownMethod() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
}
