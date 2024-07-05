@Advertisement @MultiWindow
Feature: Property Advertisement

  Background: 
    Given User is on Advertisement Page

  @Positive @Functional @List @Tab
  Scenario Outline: Find advertisement packages as an Individual
    Given User selects I am a "Individual"
    When User selects I am looking to "<looking_to>"
    And Selects My Property is "<prop_type>"
    And Clicks on Show Me Packages button
    Then Advertisement packages are displayed

    Examples: 
      | looking_to | prop_type     |
      | Rent       | PG / Flatmate |
      | Sell       | Commercial    |

  @Positive @Functional @Filter @Tab
  Scenario Outline: Find advertisement packages using filters
    Given User selects I am a "<i_am>"
    When User selects dealing in "<dealing_in>"
    And Clicks on Show Me Packages button
    Then Advertisement packages are displayed

    Examples: 
      | i_am    | dealing_in  |
      | Agent   | Residential |
      | Builder | Commercial  |

  @Positive @Functional @NoTearDown @Cart @Multiwindow
  Scenario Outline: Add an advertisement package to cart
    Given User selects I am a "<i_am>"
    When User selects I am looking to "<looking_to>"
    And Selects My Property is "<prop_type>"
    And Clicks on Show Me Packages button
    And Selects "<pkg>" package
    And Click on Add to My Orders button
    Then "<pkg>" details are displayed
    And Cart item count is "<cart_count>"

    Examples: 
      | i_am       | looking_to | prop_type  | pkg    | cart_count |
      | Individual | Sell       | Commercial | Silver |          1 |
      | Individual | Sell       | Commercial | Gold   |          2 |
