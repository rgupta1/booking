package bookingtest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import core.Base;
import pages.AirPortTaxisPage;
import pages.NavigationPage;


public class AirPortTaxisTest {
	
	private WebDriver driver;
	private NavigationPage objNavigatore;
	private AirPortTaxisPage objAirPortTaxisPage;
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
	 * @Functionality : <This function will search for airport taxi with provided details>.
	 */
	 @Parameters({ "pickup","drop", "travelDate", "hour", "minute", "passenger"})
	 @Test(groups = {"booking_search"})
	 public void bookAirportTaxi(String pickup, String drop, String travelDate,
			 String hour, String minute, String passenger) throws InterruptedException {
		 try {
			 objAirPortTaxisPage = objNavigatore.navigateToAirPortTaxisPage(driver);
			 Thread.sleep(5000);
			 objAirPortTaxisPage.clickAirportTaxi();
			 objAirPortTaxisPage.setPickup(pickup);
			 objAirPortTaxisPage.setDrop(drop);
			 
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,500)");
			 System.out.println("Selecting travel date : " + travelDate);
			 objAirPortTaxisPage = objNavigatore.navigateToAirPortTaxisPage(driver);
			 System.out.println(String.format("Travel time as :- %s h %s m", hour,minute));
			 objAirPortTaxisPage.setTravelDateAndTime(travelDate, hour, minute);
			 objAirPortTaxisPage.setPassenger(passenger);
			 System.out.println("Clicking search button");
			 objAirPortTaxisPage.clickBtn();
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
