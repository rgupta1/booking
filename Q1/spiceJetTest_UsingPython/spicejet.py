from time import sleep
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.select import Select
from selenium.webdriver import ActionChains
from datetime import date


class SpiceJet:

    TITLE = "//*[@class = 'spicejet_logo']"
    FROM = "//*[@id = 'ctl00_mainContent_ddl_originStation1_CTXT']"
    TO = "//*[@id = 'ctl00_mainContent_ddl_destinationStation1_CTXT']"
    ROUND_TRIP = "//*[@id = 'ctl00_mainContent_rbtnl_Trip_1']"
    RETURN_DATE = "//input[@id='ctl00_mainContent_view_date2']"
    PASSENGER = "//div[@id='divpaxinfo']"
    DROP_DOWN = "//*[@id='ctl00_mainContent_ddl_Adult']"
    CURRENCY = "//*[@id='ctl00_mainContent_DropDownListCurrency']"
    SEARCH_BTN = "//*[@id='ctl00_mainContent_btn_FindFlights']"
    LOGIN_SIGNUP = "//a[@href='https://book.spicejet.com/Login.aspx']"
    MOUSE_HOVER = '//*[@id="menu-list-login"]//li'
    CALENDAR_DATA = "//table[@class='ui-datepicker-calendar']//tr//td"
    CALENDER = {'01': '31', '02': '29', '03': '31', '04': '30', '05': '31', '06': '30',
                '07': '31', '08': '31', '09': '30', '10': '31', '11': '30',  '12': '31'}

    def __init__(self, url, driver_path):
        self.url = url
        self.driver_path = driver_path
        self.driver = webdriver.ChromeOptions()
        self.driver.add_argument("--disable-infobars")
        self.driver.add_argument("--use-fake-device-for-media-stream")
        self.driver.add_argument("--use-fake-ui-for-media-stream")
        self.local_driver = webdriver.Chrome(self.driver_path, chrome_options=self.driver)
        self.local_driver.implicitly_wait(30)
        self.local_driver.maximize_window()
        self.local_driver.get(self.url)
        self.next_month = False

    def set_field(self, locator, element_value):
        """" This function set the values in textbox"""
        webelement = self.local_driver.find_element_by_xpath(locator)
        try:
            webelement.send_keys(Keys.CONTROL, 'a')
            webelement.clear()
            sleep(1)
            webelement.send_keys(element_value)
        except Exception as e:
            raise Exception("Could not write on the the element {} due to {}".
                            format(webelement, e))

    def get_web_element(self, locator, option):
        """" Get the web locator"""
        if option == "single":
            web_ele = self.local_driver.find_elements_by_xpath(locator)
        else:
            web_ele = self.local_driver.find_elements_by_xpath(locator)
        return web_ele

    def click(self, locator):
        """" This function provides functionality to
        click element"""
        try:
            webelement = self.local_driver.find_element_by_xpath(locator)
            webelement.click()
        except Exception as e:
            raise Exception("Could not click the element {} due to {}".
                            format(webelement, e))

    def select_from_dropdown(self, adult_count):
        """"This function selects currency and passenger"""
        dd_data = Select(self.local_driver.find_element_by_xpath(SpiceJet.DROP_DOWN))
        dd_data.select_by_visible_text(adult_count)
        dd_currency_data = Select(self.local_driver.find_element_by_xpath(SpiceJet.CURRENCY))
        print("INFO LOG: selecting currency")
        dd_currency_data.select_by_index(len(dd_currency_data.options) - 1)

    def calculate_return_date(self, days):
        """" This function calculates the return date"""
        today = date.today()
        month = today.strftime("%m")
        current_date = today.strftime("%d")
        return_date = int(current_date) + int(days)
        for key, value in SpiceJet.CALENDER.items():
            if month == key:
                if return_date > int(value):
                    return_date = return_date - int(value)
                    assert return_date <= int(value)
                    self.next_month = True
        return current_date, str(return_date)

    def select_travel_dates(self, travel_date, start_count):
        """" This function selects the travel date on browser"""
        calendar_data = self.local_driver.find_elements_by_xpath(SpiceJet.CALENDAR_DATA)

        for data in range(start_count, len(calendar_data)):
            text = calendar_data[data].text
            if text == travel_date:
                calendar_data[data].click()
                break

    def test_plan_travel(self, origin, dest):
        """" test case that will perform following steps:
            1.  Go to https://www.spicejet.com
            2. For round trip, Enter “From = ‘Goa’” and “To = ’Delhi’
            3. Select return date as 10 days from today and inclusive of the
            current date
            4. From the dropdown, select 3 adults and change the currency to
            last currency in the dropdown
            5. Click on search
            6. Using Action class, hover the mouse on top right text
            “Login/Signup”
            7. Print all the options in sequence
            8. Verify Title of the page using assertions
            9. Verify current URL
        """
        print("INFO LOG: Selecting radio button for round trip ")
        self.click(SpiceJet.ROUND_TRIP)
        print("INFO LOG: Selecting origin: " + origin)
        self.set_field(SpiceJet.FROM, origin)
        print("INFO LOG: Selecting destination: " + dest)
        self.set_field(SpiceJet.TO, dest)
        start_date, end_date = self.calculate_return_date('9')
        print("INFO LOG: Selecting travel start date: " + start_date)
        self.select_travel_dates(start_date, 0)
        sleep(1)
        self.click(SpiceJet.RETURN_DATE)
        print("INFO LOG: Selecting travel end date: " + end_date)
        if self.next_month:
            self.select_travel_dates(end_date, 35)
        else:
            self.select_travel_dates(end_date, 0)
        sleep(1)
        print("INFO LOG: selecting travellers")
        self.click(SpiceJet.PASSENGER)
        sleep(1)
        self.select_from_dropdown('3')
        print("INFO LOG: clicking search button")
        self.click(SpiceJet.SEARCH_BTN)

        element = self.get_web_element(SpiceJet.LOGIN_SIGNUP, 'single')
        hov_action = ActionChains(self.local_driver).move_to_element(element[0])
        hov_action.perform()
        elements = self.get_web_element(SpiceJet.MOUSE_HOVER, 'double')
        for data in elements:
            if data.text:
                print("Sequence:- " + data.text.strip())

        title = self.get_web_element(SpiceJet.TITLE, 'single')[0].get_attribute('title')
        print("INFO LOG: verifying title: " + title)
        assert title == "SpiceJet"
        print("INFO LOG: Current url:- " + self.local_driver.current_url)
        assert self.local_driver.current_url == 'https://book.spicejet.com/Select.aspx'

    # def test_spice_travel(self):
    #     try:
    #         obj = SpiceJet("https://www.spicejet.com", "c:\\chromedriver.exe")
    #         obj.test_plan_travel('GOA', 'DEL')
    #     except Exception as e:
    #         raise Exception("Could Not perform operation due to {}".format(e))
    #     finally:
    #         obj.local_driver.quit()


if __name__ == "__main__":
    try:
        obj = SpiceJet("https://www.spicejet.com", "c:\\chromedriver.exe")
        obj.test_plan_travel('GOA', 'DEL')
    except Exception as e:
        raise Exception("Could Not perform operation due to {}".format(e))
    finally:
        obj.local_driver.quit()
