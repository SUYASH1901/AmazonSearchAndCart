package com.amazon_searchandcart.qa.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Base {
	
	
	WebDriver driver=null;
	
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			 driver=new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			
			 driver=new FirefoxDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("Edge")) {
			
			 driver=new EdgeDriver();
			
		}
		
		
        driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_28ors6rnjl_b&adgrpid=60611463244&hvpone=&hvptwo=&hvadid=617724335923&hvpos=&hvnetw=g&hvrand=9818270623233913154&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9300442&hvtargid=kwd-298319537966&hydadcr=15413_2322997");
		
		return driver;
		
		
	}

}
