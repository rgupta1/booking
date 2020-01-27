package bookingtest;

import static org.testng.Assert.assertNotEquals;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.Base;
import pages.HotelPage;
import pages.NavigationPage;


public class HotelTest {
	
	private WebDriver driver;
	private NavigationPage objNavigatore;
	private HotelPage objHotelPage;
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
	 * @Functionality : <This function will search for hotels with provided details>.
	 */
	 @Test(groups = {"booking_search"})
	 @Parameters({ "dest", "travelDays", "adults", "child", "room"})
	 public void hotelSearch(String dest, int travelDays, int adults, int child, int room ) {
		 try {
			 objHotelPage = objNavigatore.navigateToHotelPage(driver);
			 objHotelPage.setDestination(dest);
			 
			 List<Object> dates =objBase.returnDate(travelDays);
			 
			 String startDate = (String) dates.get(0);
			 String endDate = (String) dates.get(1);
			 Boolean nextMonth = (boolean) dates.get(2);
			 String month = (String) dates.get(3);
			 
			 assertNotEquals(startDate, endDate);
			 
			 System.out.println("Selecting Start Date as : " + startDate + " " + month);
			 objHotelPage.setTravelDates(startDate, Integer.parseInt(startDate));
			 
			 System.out.println("Selecting End Date as : " + endDate);
			 if (nextMonth){
				 objHotelPage.setTravelDates(endDate, 35);
			 }
			 else {
				 objHotelPage.setTravelDates(endDate, Integer.parseInt(startDate));
			 }
			 System.out.println(String.format("Selecting adults as: %d \nChild as: %d \nRoom as: %d", adults,child,room));
			 objHotelPage.setAdultChildRoom(adults,child,room);
			 
			 System.out.println("Clicking search button");
			 objHotelPage.clickBtn();
		 }
		 catch (Exception e) {
			 System.out.println(e);		}

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
