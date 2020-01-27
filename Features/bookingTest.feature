Feature: Search Hotel, Rental Cars and Airport taxis on booking.com

	Scenario: Searching hotel on booking.com with given details
	Given Booking.com site should be up and running
	When Open the browser and launch the application
	And Search hotel by location name as "Abu Dhabi"
	And Set tour duration in days starting from today for "5" days
	And Enter travellers details adults "4" child "2" and room "2"
	Then Search the hotels
	And Quit the browser

	Scenario: Searching Airport taxis on booking.com with given details
	Given Booking.com site should be up and running
	When Open the browser and launch the application
	And Click on airpot taxis link
	Then Set taxi pickup location as "PNQ" 
	And  Set drop location as "BLR"
	And  Set travel date from current month as "29" and time as hours "12" minute "15"
	And Set number of passenger as "6"
	Then Search the taxis
	And Quit the browser
	
	Scenario: Searching rental car on booking.com with given details
	Given Booking.com site should be up and running
	When Open the browser and launch the application
	And Click on rental car link
	Then Set pickup location as "New Delhi" 
	And Set rental duration in days starting from today for "5" days
	Then Search the rental cars
	And Quit the browser