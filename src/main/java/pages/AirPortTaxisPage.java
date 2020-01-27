package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class AirPortTaxisPage {
	
	@FindBy(xpath = "//*[@id='cross-product-bar']/div/a[4]/span[1]") WebElement lnkAirporTaxi;
	
	@FindBy(xpath="//*[@id = 'pickupLocation']") WebElement txtPickup;
	
	@FindBy(xpath="//*[@class = 'rw-autocomplete__item-title ui-clyde gb-u-bold gb-u-m0']") WebElement drpdwnPickupDropAirport;
	
	
	@FindBy(xpath="//*[@id = 'dropoffLocation']") WebElement txtDrop;
	
	@FindBys(@FindBy(xpath="//*[@class = 'gb-u-width-100 gb-u-text-align-left rw-date-field gb-o-interactive-field']"))
	public List<WebElement> lnkCalenderTime;
	
	@FindBys(@FindBy(xpath="//*[@class='rw-c-date-picker gb-u-p']//tr/td"))
	@CacheLookup
	public List<WebElement> calendar;
	
	@FindBy(xpath="//*[@id = 'pickupHour']") WebElement drpdwnPickupHour;
	@FindBy(xpath="//*[@id = 'pickupMinute']") WebElement drpdwnPickupMinute;
	@FindBy(xpath="//*[@class = 'rw-time-picker__confirm']") WebElement btnCOnfirm;

	
	@FindBy(xpath="//*[@id= 'passengers']") WebElement drpdwnpassengers;
	
	@FindBys(@FindBy(xpath="//*[@class = 'gb-o-btn gb-u-ease-out-02 gb-o-btn--primary gb-o-btn--horizontal ui-congo gb-c-search-form__submit-button' and @type = 'submit']"))
	public List<WebElement> btnSUbmit;
		
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set the pickup location>.
	 */
	public void setPickup(String pickup)
	{
		txtPickup.clear();
		txtPickup.click();
		txtPickup.sendKeys(pickup);
		System.out.println("Selecting Pickup " + drpdwnPickupDropAirport.getText());
		drpdwnPickupDropAirport.click();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set the drop location>.
	 */
	public void setDrop(String drop)
	{
		txtDrop.click();
		txtDrop.sendKeys(drop);
		System.out.println("Selecting Drop location " + drpdwnPickupDropAirport.getText());
		drpdwnPickupDropAirport.click();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set the travel schedule>.
	 */
	public void setTravelDateAndTime(String travelDate, String hour, String minute)
	{
		lnkCalenderTime.get(0).click();
		for (int i = 0; i <= calendar.size(); i++) {
			String date =  calendar.get(i).getText().replaceAll("\\s", "");
			if (date.equalsIgnoreCase(travelDate)) {
				calendar.get(i).click();
				break;
			}
		}
		lnkCalenderTime.get(1).click();
		drpdwnPickupHour.sendKeys(hour);
		drpdwnPickupMinute.sendKeys(minute);
		btnCOnfirm.click();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set the no of travellers>.
	 */
	public void setPassenger(String passenger)
	{
		drpdwnpassengers.sendKeys(passenger);
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will click on airport taxi link>.
	 */
	public void clickAirportTaxi( )
	{
		lnkAirporTaxi.click();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will click search button>.
	 */
	public void clickBtn( )
	{
		btnSUbmit.get(0).click();
	}


}
