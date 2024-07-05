@PropWorth
Feature: Property worth calculator

  Background: 
    Given User is on propworth page

  @Functional @Positive @Functionality @List @NestedList @Tab @AutoSuggestion
  Scenario Outline: Calculate property worth with valid data
    Given User enters locality key "<loc_key>"
    And Selects locality "<locality>"
    And Selects propert type "<prop_type>" and bhk label "<bhk_label>"
    And Selects super area "<sup_area>" and floor "<floor>"
    And Selects open parking "<open_park>" and covered parking "<cov_park>"
    When User click on Check Now button
    Then User property worth is displayed

    Examples: 
      | loc_key | locality                   | prop_type     | bhk_label | sup_area  | floor  | open_park | cov_park |
      | delh    | Dwarka, New Delhi          | Builder Floor |         2 | 300 sqyrd | Ground |         1 |        2 |
      | noid    | Noida Extension, Noida     | Flat          |         1 | 300 sqm   |      3 |           |          |
      | bang    | Electronic City, Bangalore | House/Villa   |         3 | 100 sqft  |        |           |          |

  @Positive @Unit @AutoSuggestion
  Scenario Outline: Calculate property worth with unavailable locality
    Given User enters locality key "<loc_key>"
    When User selects locality "<locality>"
    Then Unavailability error message "<err_msg>" is displayed

    Examples: 
      | loc_key | locality             | err_msg                                         |
      | uttara  | Uttara Nagar, Nashik | Oops ! We don't have details for this selection |

  @Negative @Unit @Button
  Scenario Outline: Calculate property worth with invalid locality key
    Given User enters locality key "<loc_key>"
    When User clicks on send icon button
    Then Invalid key error message "<err_msg>" is displayed

    Examples: 
      | loc_key  | err_msg                                       |
      | 62448289 | Please enter a valid project or locality name |
      | asdajhgf | Please enter a valid project or locality name |

  @Negative @Unit @List @NestedList @Button
  Scenario Outline: Calculate property worth with invalid area
    Given User enters locality key "<loc_key>"
    And Selects locality "<locality>"
    And Selects propert type "<prop_type>" and bhk label "<bhk_label>"
    And Selects super area "<sup_area>" and floor ""
    When User click on Check Now button
    Then Area error message "<err_msg>" is displayed

    Examples: 
      | loc_key | locality          | prop_type     | bhk_label | sup_area | err_msg           |
      | delh    | Dwarka, New Delhi | Builder Floor |         2 |          | Please enter area |
