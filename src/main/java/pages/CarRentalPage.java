package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class CarRentalPage {
	
	@FindBy(xpath = "//*[@class = 'xpb__link']//span[text()='Car Rentals']") WebElement lnkCarRentals;
	
	@FindBy(xpath="//*[@id='ss_origin']") WebElement txtdest;
	@FindBys(@FindBy(xpath="//*[@class = 'c-autocomplete__list sb-autocomplete__list -visible']//li"))
	public List<WebElement> drpdwndest;
	
	
	@FindBys(@FindBy(xpath="//*[@class = 'sb-searchbox__input sb-date-field__field sb-date__field-svg_icon']"))
	@CacheLookup
	public List<WebElement> lblcalender;
	
	@FindBys(@FindBy(xpath="//*[@class='c2-calendar']//tr//td"))
	@CacheLookup
	public List<WebElement> calendar;
	
	
	@FindBys(@FindBy(xpath="//*[@class = 'bui-form__group']"))
	public List<WebElement> drpdownpickupdrptme;
	
	@FindBy(xpath="//*[@type= 'submit' and @class = 'sb-searchbox__button  js-sb-submit-button']") WebElement btnSearch;
	
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set the destination>.
	 */
	public void setDestination(String destination)
	{
		txtdest.click();
		txtdest.sendKeys(destination);
		for (WebElement loc : drpdwndest) {
			if(destination.equalsIgnoreCase(loc.getAttribute("data-label").split(",")[0])){
				System.out.println("Selecting Destination " + loc.getAttribute("data-label"));
				loc.click();	
			}
		}
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will set travel schedule>.
	 */
	public void setTravelDates(String startDate, int count)
	{
		lblcalender.get(0).click();
		for (int i = count; i <= calendar.size()-1; i++) {
			String date =  calendar.get(i).getText().replaceAll("\\s", "");
			if (date.equalsIgnoreCase(startDate)) {
				calendar.get(i).click();
				break;
			}
		}
	}
	
	/**************************************************************************************
	 * @return
	 * @throws InterruptedException 
	 * @Functionality : <This function will set end date>.
	 */
	public void setTravelEndDate(String travlDate, int count) throws InterruptedException
	{
		int temp = 0;
		Thread.sleep(2000);
//		lblcalender.get(1).click();
		for (int i = count; i <= calendar.size()-1; i++) {
			String date =  calendar.get(i).getText().replaceAll("\\s", "");
			
			if (((date.equalsIgnoreCase(travlDate)) && (count == 35)) && (temp == 0 )){
				temp = 1;
				continue;
			}
			if ((temp == 1) && (date.equalsIgnoreCase(travlDate))){
				calendar.get(i).click();
				break;
			}
			if (((date.equalsIgnoreCase(travlDate)) && (count != 35)) && (temp == 0 )){
				calendar.get(i).click();
				break;
			}
			
		}
	}
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will click the car rental link>.
	 */
	public void clickCarRental( )
	{
		lnkCarRentals.click();
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will click the car rental link>.
	 */
	public void clickBtn( )
	{
		btnSearch.click();
	}

}
