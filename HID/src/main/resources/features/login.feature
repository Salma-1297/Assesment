Feature: Login to the application

  Scenario: Successful login
    Given I am on the login page
    When I enter username "devices@miquido.com" and password "Test1234"
   # And I click the login button
    #Then I should see the dashboard