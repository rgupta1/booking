package steps;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import core.Base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;
import pages.AirPortTaxisPage;
import pages.CarRentalPage;
import pages.HotelPage;
import pages.NavigationPage;


public class Steps {				
	private WebDriver driver;
	private NavigationPage objNavigatore;
	private HotelPage objHotelPage;
	private CarRentalPage objCarRentalPage;
	private Base objBase;
	private AirPortTaxisPage objAirPortTaxisPage;
     
	
	@Given("^Booking.com site should be up and running$")				
    public void site_should_be_up_and_running() throws UnknownHostException, IOException, Throwable							
    {	
		System.out.println("This step checks the connectivity with booking.com");
    }		
	
    @When("^Open the browser and launch the application$")				
    public void open_the_browser_and_launch_the_application() throws Throwable							
    {	
    	System.out.println("This Step opens the Firefox and launch the application.");
    	objBase =  new Base();
		driver = objBase.getWebDriver("chrome", "C:/chromedriver.exe");
		objBase.open(driver, "https://www.booking.com/");
		objNavigatore = new NavigationPage();					
    }		

    @When("^Search hotel by location name as \"([^\"]*)\"$")					
    public void sreach_hotel_by_location_name(String destination) throws Throwable 							
    {		
       System.out.println("This step sets the hotel location as " + destination);
       objHotelPage = objNavigatore.navigateToHotelPage(driver);
	   objHotelPage.setDestination(destination);
    }
    
    @And("^Set tour duration in days starting from today for \"([^\"]*)\" days$")					
    public void set_tour_dates(String travelDays) throws Throwable 							
    {		
    	System.out.println("This step sets the duration in days starting from today and total days are : "
    			+ "" + travelDays);
    	List<Object> dates =objBase.returnDate(Integer.parseInt(travelDays));
   	 	String startDate = (String) dates.get(0);
		String endDate = (String) dates.get(1);
		Boolean nextMonth = (boolean) dates.get(2);
		String month = (String) dates.get(3);
		 
		assertNotEquals(startDate, endDate);
		 
		System.out.println("	Selecting Start Date as : " + startDate + " " + month);
		objHotelPage.setTravelDates(startDate, Integer.parseInt(startDate));
		
		System.out.println("	Selecting End Date as   : " + endDate);
		 if (nextMonth){
			 objHotelPage.setTravelDates(endDate, 35);
		 }
		 else {
			 objHotelPage.setTravelDates(endDate, Integer.parseInt(startDate));
		 }
    }		
    
    @And("^Enter travellers details adults \"([^\"]*)\" child \"([^\"]*)\" and room \"([^\"]*)\"$")					
    public void enter_travellers_and_room(String adult, String child, String room) throws Throwable 							
    {		
    	System.out.println(String.format("This step sets travelers details as: \n	adults : %d \n	child :  "
    			+ "%d \n	rooms :  %d", Integer.parseInt(adult)
    			,Integer.parseInt(child),Integer.parseInt(room)));
		 objHotelPage.setAdultChildRoom(Integer.parseInt(adult)
	    			,Integer.parseInt(child),Integer.parseInt(room));
    }

    @Then("^Search the hotels$")					
    public void search_hotels() throws Throwable 							
    {    		
    	System.out.println("Clicking search button");
		objHotelPage.clickBtn();					
    }
    
    @Then("^Quit the browser$")					
    public void quit_browser() throws Throwable 							
    {    		
    	System.out.println("This step quits the browser");
    	driver.quit();				
    }
    
    @And("^Click on rental car link$")					
    public void click_rental_car_link() throws Throwable 							
    {		
    	 objCarRentalPage = objNavigatore.navigateToCarRentalPage(driver);
		 Thread.sleep(2000);
		 System.out.println("Clicking car rental link");
		 objCarRentalPage.clickCarRental();
    }
    
    @Then("^Set pickup location as \"([^\"]*)\"$")					
    public void set_pickup_location(String location) throws Throwable 							
    {		
    	System.out.println("This step sets the location as" + location);
    	objCarRentalPage.setDestination(location);
    }
    
    @And("^Set rental duration in days starting from today for \"([^\"]*)\" days$")					
    public void set_rental_car_duration(String travelDays) throws Throwable 							
    {		
    	System.out.println("This step sets the rental duration in days starting from today and total days are : "
    			+ "" + travelDays);
    	List<Object> dates =objBase.returnDate(Integer.parseInt(travelDays));
		 
		 String startDate = (String) dates.get(0);
		 String endDate = (String) dates.get(1);
		 Boolean nextMonth = (boolean) dates.get(2);
		 String month = (String) dates.get(3);
		 
		 assertNotEquals(startDate, endDate);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,600)");
		 System.out.println("	Selecting Start Date as : " + startDate + " " + month);
		 objCarRentalPage.setTravelDates(startDate, Integer.parseInt(startDate));
		 
		 objCarRentalPage = objNavigatore.navigateToCarRentalPage(driver);
		 System.out.println("	Selecting End Date as : " + endDate);
		 if (nextMonth){
			 objCarRentalPage.setTravelEndDate(endDate, 35);
		 }
		 else {
			 objCarRentalPage.setTravelEndDate(endDate, Integer.parseInt(startDate));
		 } 
    }
    
    @Then("^Search the rental cars$")					
    public void search_the_rental_cars() throws Throwable 							
    {    		
    	System.out.println("Clicking search button");
		objCarRentalPage.clickBtn();					
    }
    
    @And("^Click on airpot taxis link$")					
    public void click_airport_taxis_link() throws Throwable 							
    {		
		 objAirPortTaxisPage = objNavigatore.navigateToAirPortTaxisPage(driver);
		 Thread.sleep(5000);
		 System.out.println("Clicking Airport taxi link");
		 objAirPortTaxisPage.clickAirportTaxi();
    }
    
    @Then("^Set taxi pickup location as \"([^\"]*)\"$")					
    public void set_pick_location(String pckLocation) throws Throwable 							
    {	
    	 System.out.println("This step sets the pickup location as " + pckLocation);
		 objAirPortTaxisPage.setPickup(pckLocation);
    }
    
    @And("^Set drop location as \"([^\"]*)\"$")					
    public void set_drop_location(String drpLocation) throws Throwable 							
    {		
    	 System.out.println("This step sets the drop location as " + drpLocation);
		 objAirPortTaxisPage.setDrop(drpLocation);
    }
    
    @And("^Set travel date from current month as \"([^\"]*)\" and time as hours \"([^\"]*)\" minute \"([^\"]*)\"$")					
    public void set_travel_date(String travelDate, String hour, String minute) throws Throwable 							
    {		
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");
		 System.out.println("Selecting travel date : " + travelDate);
		 System.out.println(String.format("Travel time as :- %s h %s m", hour,minute));
		 objAirPortTaxisPage.setTravelDateAndTime(travelDate, hour, minute);
    }
    
    @And("^Set number of passenger as \"([^\"]*)\"$")					
    public void set_passenger(String passenger) throws Throwable 							
    {		
    	System.out.println("This step sets the passenger details as " + passenger);
    	objAirPortTaxisPage.setPassenger(passenger);
    }
    
    @And("^Search the taxis$")					
    public void search_taxis() throws Throwable 							
    {		
    	System.out.println("Clicking search button");
		objAirPortTaxisPage.clickBtn();
    }

}

