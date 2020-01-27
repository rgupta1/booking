package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage {
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will initialize the HotelPage class elements>.
	 */
	public HotelPage navigateToHotelPage(WebDriver driver) {
		HotelPage objHotelPage = new HotelPage();		
		PageFactory.initElements(driver, objHotelPage);
		return objHotelPage;
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will initialize the AirPortTaxisPage class elements>.
	 */
	public AirPortTaxisPage navigateToAirPortTaxisPage(WebDriver driver) {
		AirPortTaxisPage objAirPortTaxisPage = new AirPortTaxisPage();		
		PageFactory.initElements(driver, objAirPortTaxisPage);
		return objAirPortTaxisPage;
	}
	
	/**************************************************************************************
	 * @return
	 * @Functionality : <This function will initialize the CarRentalPage class elements>.
	 */
	public CarRentalPage navigateToCarRentalPage(WebDriver driver) {
		CarRentalPage objCarRentalPage = new CarRentalPage();		
		PageFactory.initElements(driver, objCarRentalPage);
		return objCarRentalPage;
	}

}
