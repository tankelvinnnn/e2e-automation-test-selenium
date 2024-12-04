@CakapPayment @CakapPrivate
Feature: Payment Private Functionality
  As a user, I want to log into the admin dashboard with valid credentials.

  @Positive
  Scenario: Successful payment of Cakap Private Package using new user account
    Given access private campaign link
    When student select the "Add On Package English 2X" package
    When create new student account
    Then final price should be same as package price
    When student select payment method "BNI Virtual Account"
    And check the tnc and click pay now button
    Then student should redirected to awaiting payment page

  @Positive
  Scenario Outline: Successful Payment of Cakap Private Package with sales discount
    Given access private campaign link
    When student select the "<package_name>" package
    Then final price should be same as package price
    When student select payment method "<payment_method>"
    And check the tnc and click pay now button
    Then student should redirected to awaiting payment page
  Examples:
  | package_name | payment_method |
  | English Discovery | BCA Virtual Account |
  | English Add On Package (4X) | BRI Virtual Account |

  @Positive @LastScenario
  Scenario Outline: Successful Payment of Cakap Private Package with voucher discount
    Given access private campaign link
    When student select the "<package_name>" package
    When student apply the voucher
    Then final price should be same as package price
    When student select payment method "<payment_method>"
    And check the tnc and click pay now button
    Then student should redirected to awaiting payment page
  Examples:
  | package_name | payment_method |
  | English Private Mini | Mandiri Virtual Account |
  | IN 1/3 English Private Daily 1x | CIMB Virtual Account |
  
  