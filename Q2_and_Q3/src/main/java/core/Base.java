package core;

import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will calculate the return date>.
	 */
	public WebDriver getWebDriver(String strBrowserType, String dir_path) {
		switch (strBrowserType.toUpperCase()) {  
		case "CHROME":	
			System.setProperty( "webdriver.chrome.driver",
					"C:/chromedriver.exe" );
			ChromeOptions options = new ChromeOptions();
			return new ChromeDriver(options);

		default:
			throw new RuntimeException("Browser is not supported");

		}
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will open hit the URL>.
	 */
	public void open(WebDriver driver, String strLoginURL)   {
		System.out.println("Opening URL : " + strLoginURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(strLoginURL);
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will calculate the return date>.
	 */
	public List<Object> returnDate(int days)   {
		
		Boolean nextMonth = false;
		Date date = new Date();
		String month = date.toString().split(" ")[1];
		String startDate = date.toString().split(" ")[2];
		Integer endDate = Integer.parseInt(startDate) + days;
        Hashtable<String, Integer> cal = new Hashtable<String, Integer>(); 
        cal.put("Jan", 31);
        cal.put("Feb", 29);
        cal.put("Mar", 31);
        cal.put("Apr", 30);
        cal.put("May", 31);
        cal.put("Jun", 30);
        cal.put("Jul", 31);
        cal.put("Aug", 31);
        cal.put("Sep", 30);
        cal.put("Oct", 31);
        cal.put("Nov", 30);
        cal.put("Dec", 31);
        
        Set<String> calen = cal.keySet();
        
        // for-each loop
        for(String key : calen) {
            if ((key.equalsIgnoreCase(month)) && (endDate > cal.get(key))){
            	nextMonth = true;
            	endDate = endDate - cal.get(key);
            	assert endDate <= cal.get(key):"return date is more than next month.";
            	break;
            }
        }
        return Arrays.asList(startDate, endDate.toString(), nextMonth, month); 
	}
}
