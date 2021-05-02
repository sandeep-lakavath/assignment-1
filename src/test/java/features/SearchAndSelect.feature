#Author: Abishek Thorikonda
#Keywords Summary : Search and Select flights

@search @select
Feature: Search and Select flights
  I Search fights and select lowest fare

  #@search
  #Scenario: As a user I should get an error message "Please enter a valid city" when do not enter "to" city
  #	Given I navigate to JetBlue website
    #When I enter From city as "New York"
    #And I click on Search flights
    #Then I should get an error message "Please enter a valid city."
  #
  #@search
  #Scenario: As a user I should see Return Date prior to Depart date is disabled
  #	Given I navigate to JetBlue website
    #When I enter From city as "New York"
    #And I enter To city as "Toronto"
    #And I select Depart date
    #And I try to select Return Date prior to Depart date
    #Then Return Date prior to Depart date should be disabled
    #
  @search
  Scenario: As a user I should see Return Date prior to Depart date is disabled
  	Given I navigate to JetBlue website
    When I enter From city as "New York"
    And I enter To city as "Phoenix, AZ (PHX)"
    And I select Depart date
    And I select Return date
    And I click on Search flights
    Then I should land on flights selection page
    When I select lowest fare for departure
    And I select lowest fare for return
    And click on continue
    Then I should naviate to travelers info page 
 