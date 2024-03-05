
@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page 

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productname> from Cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConirmationPage

    Examples: 
      | name  											| 	password | productName |
      |blossom.manchanda@gmail.com  | Blossom@23 | ZARA COAT 3 |