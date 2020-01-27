package bookingtest;

import static org.testng.Assert.assertNotEquals;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import core.Base;
import pages.CarRentalPage;
import pages.NavigationPage;


public class CarRentalTest {
	
	private WebDriver driver;
	private NavigationPage objNavigatore;
	private CarRentalPage objCarRentalPage;
	private Base objBase;
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will launch the browser and sit booking.com>.
	 */
	@BeforeClass(groups = {"booking_search"})
	public void launch_browser()
	{
		objBase =  new Base();
		 
		driver = objBase.getWebDriver("chrome", "C:/chromedriver.exe");
		objBase.open(driver, "https://www.booking.com/");
		objNavigatore = new NavigationPage();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will search for rental cars with provided details>.
	 */
	 @Parameters({ "destination", "tourDuration"})
	 @Test(groups = {"booking_search"})
	 public void searchCarRental(String destination, int tourDuration) throws InterruptedException {
		 try {
			 objCarRentalPage = objNavigatore.navigateToCarRentalPage(driver);
			 Thread.sleep(2000);
			 objCarRentalPage.clickCarRental();
			 objCarRentalPage.setDestination(destination);
			 
			 List<Object> dates =objBase.returnDate(tourDuration);
			 
			 String startDate = (String) dates.get(0);
			 String endDate = (String) dates.get(1);
			 Boolean nextMonth = (boolean) dates.get(2);
			 String month = (String) dates.get(3);
			 
			 assertNotEquals(startDate, endDate);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,600)");
			 System.out.println("Selecting Start Date as : " + startDate + " " + month);
			 objCarRentalPage.setTravelDates(startDate, Integer.parseInt(startDate));
			 
	 		 objCarRentalPage = objNavigatore.navigateToCarRentalPage(driver);
			 System.out.println("Selecting End Date as : " + endDate );
			 if (nextMonth){
				 objCarRentalPage.setTravelEndDate(endDate, 35);
			 }
			 else {
				 objCarRentalPage.setTravelEndDate(endDate, Integer.parseInt(startDate));
			 } 
			 System.out.println("Clicking search button");
			 objCarRentalPage.clickBtn();
		 }
		catch (Exception e) {
			System.out.println(e);
		}
	 }
	 /**************************************************************************************
	  * @return
	  * @Functionality : <This function will quit the browser started by automation>.
	  */
	@AfterClass(groups = {"booking_search"})
	 public void tearDown() {
		 driver.quit();
		 
	 }


}
