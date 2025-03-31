Feature: Login

  Scenario: Successful login
    Given I am on the login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click login button
    Then I succesfully login and get "Swag Labs"