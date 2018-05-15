package com.tests;

import java.math.BigDecimal;

public class SafariUsage {
	
	public static void main(String[] args) {
		String mesaj="You have reached 5 of 6 point(s), (80.00%)";
		String no=mesaj.substring(mesaj.indexOf( '(' ),mesaj.indexOf( '%' )).replaceAll("[^\\d.]", "") ;
		BigDecimal money = new BigDecimal(no);
		System.out.println(money);
		
		/*BigDecimal value=new BigDecimal(80);
		if(exactScore.compareTo(value)>=0){
			System.out.println("da cursul ar trebui sa fie ok");
		}*/
	}
	 
	/*@Test
	public void safariUsage() throws Exception {
	
		
		// Change this to match the location of your server
	    URL server = new URL("http://172.22.4.79:82/wd/hub");

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setBrowserName("firefox");
	    capabilities.setCapability("name", "Emilian");
	    System.out.println("Connecting to " + server);

	    WebDriver driver = new RemoteWebDriver(server, capabilities);

	    driver.get("http://www.google.com");

	    driver.quit();
		
	}*/
//	
//	public static void main(String[] args)
//	{
//	//	WebDriver driver = new SafariDriver();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://store.demoqa.com");
// 
//		//Find some element on DemoQa.com
//		WebElement element = driver.findElement(By.id("login"));
//		element.click();
// 
//	}
 
}