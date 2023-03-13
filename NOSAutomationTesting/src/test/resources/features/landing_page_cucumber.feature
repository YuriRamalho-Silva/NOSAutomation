Feature: Test scenarios for landing page

  Scenario: create account with success
    Given I go to the registration page
    When I fill in the Name field with "Yuri Ramalho"
    And I fill in the Number field with "11969688292"
    And I fill in the Email field with "johndoe@email.com"
    And I fill in the Password field with "Yuri0!!0@@"
    And Accept the terms and conditions
    And Click in the button to create my account
    Then A message for validation of my number is displayed

  Scenario: create account with invalid parameters
    Given I go to the registration page
    When I fill in the Name field with "John 123"
    And I fill in the Number field with "0000"
    And I fill in the Email field with "yuri.com"
    And I fill in the Password field with "password"
    And Click in the button to create my account
    Then the fields number, email password and terms should be required again
