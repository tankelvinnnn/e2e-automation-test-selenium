@CakapAdmin @AdminLogin
Feature: Admin Login Functionality
  As a user, I want to log into the admin dashboard with valid credentials.

  @Positive 
  Scenario: Successful Login with valid account
    Given access admin dashboard page
    When user click on continue with google button
    And user insert the admin credentials with "kelvin@cakap.com" and "Cakap2024"
    Then user should be able to see admin dashboard display
  
  # The google block to login or error message got block from the options
  # @Negative 
  # Scenario: Failed to Login with not registered domain
  #   Given access admin dashboard page
  #   When user click on continue with google button
  #   And user insert the admin credentials with "testcakap2@gmail.com" and "Cakap2024"
  #   Then user should be see Account not register message