@Navigation
Feature: Main navigation bar

  @Functional @Positive
  Scenario Outline: Verify dropdown on hovering main navigation links
    Given User is on home page
    When User hover "<nav_link>" navigation link and clicks "<dropdown_link>" dropdown link
    Then A webpage for "<dropdown_link>" is displayed

    Examples: 
      | nav_link          | dropdown_link             |
      | Buy               | Ready to Move             |
      | Home Loans        | Home Loans                |
      | Sell              | Property Valuation        |
      | Help              | Help Center               |
      | Sell              | Post Property             |
