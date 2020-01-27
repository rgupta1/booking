package pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HotelPage {
	@FindBy(xpath="//*[@id='ss']") WebElement txtdest;
	@FindBy(xpath="//*[@class = 'xpb__link selected']") WebElement lblstays;
	
	@FindBys(@FindBy(xpath="//*[@class = 'c-autocomplete__list sb-autocomplete__list sb-autocomplete__list-with_photos -visible']//li"))
	public List<WebElement> drpdwndest;
	
	@FindBys(@FindBy(xpath="//*[@class = 'bui-calendar__main b-a11y-calendar-contrasts']//tr//td"))
	public List<WebElement> calendar;
	
	@FindBy(xpath="//*[@id = 'xp__guests__toggle']") WebElement drpdwnadults;
	
	@FindBys(@FindBy(xpath="//*[@class = 'bui-button bui-button--secondary bui-stepper__add-button ']"))
	public List<WebElement> btncountadultChildRoom;
	
	@FindBys(@FindBy(xpath="//*[@class = 'bui-stepper__display']"))
	public List<WebElement> lblcountadultChildRoom;
	
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
	public void setTravelDates(String travlDate, int count )
	{
		for (int i = count; i <= calendar.size(); i++) {
			String date =  calendar.get(i).getText().replaceAll("\\s", "");
			if (date.equalsIgnoreCase(travlDate)) {
				calendar.get(i).click();
				break;
			}
		}
	}
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will travellers details and room>.
	 */
	public void setAdultChildRoom(int adult, int child, int room)
	{
		drpdwnadults.click();
		int startCountAdult = Integer.parseInt(lblcountadultChildRoom.get(0).getText());
		for (int i = startCountAdult; i < adult; i++) {
			btncountadultChildRoom.get(0).click();
		}
		int startCountChild = Integer.parseInt(lblcountadultChildRoom.get(1).getText());
		for (int i = startCountChild; i < child; i++) {
			btncountadultChildRoom.get(1).click();
		}
		int startCountRoom = Integer.parseInt(lblcountadultChildRoom.get(2).getText());
		for (int i = startCountRoom; i < room; i++) {
			btncountadultChildRoom.get(2).click();
		}
	}
	
	public void clickBtn( )
	{
		btnSearch.click();
	}
	
}
