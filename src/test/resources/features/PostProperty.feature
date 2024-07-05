@PostProperty @MultiWindow
Feature: Post a Property

  Background: 
    Given User is on Post Property Launcher page
    When User selects "Owner" user type
    And Selects "Sell" intention
    And Enters "IND +91" country code and "9809807890" mobile number
    And Clicks on Start Now button
    Then Post Property form is displayed

  @Unit @Positive @Negative @Input @Select @Radio
  Scenario Outline: Verify Post Property Form personal details
    Given User selects Owner user type
    When User enters name "<name>"
    And Enters email "<email>"
    And Click on Login & Post Property button
    Then Country code "IND +91" and mobile number "9809807890" is pre-filled
    And "<err_count>" errors are displayed for "<section>"

    Examples: 
      | name        | email              | err_count | section          |
      | Raman Singh | raman@demo.test    |         0 | Personal Details |
      | Ayush       | ayush.hi@demo.test |         0 | Personal Details |
      |             | raman@demo.test    |         1 | Personal Details |
      | Raman Singh |                    |         1 | Personal Details |
      |             |                    |         2 | Personal Details |
      |         123 | Asd@               |         2 | Personal Details |

  @Unit @Positive @Radio @Select
  Scenario Outline: Verify Post Property Form property details
    Given User selects "<prop_intent>" property intent
    When Selects "<prop_type>" property type
    And Click on Login & Post Property button
    Then "<err_count>" errors are displayed for "<section>"

    Examples: 
      | prop_intent | prop_type | err_count | section          |
      | Sale        | Penthouse |         0 | Property Details |
      | Rent/ Lease | Villa     |         0 | Property Details |

  @Unit @Positive @List @NestedList
  Scenario Outline: Verify Post Property Form property features
    Given Selects "Flat/ Apartment" property type
    When User selects "<bed>" bedrooms
    And Selects "<balc>" balconies
    And Selects "<floor_num>" floor number
    And Selects "<floors>" floors
    And Selects "<furn>" furnished
    And Selects "<bath>" bathroom
    And Click on Login & Post Property button
    Then "<err_count>" errors are displayed for "<section>"

    Examples: 
      | bed | balc | floor_num | floors | furn        | bath | err_count | section           |
      |   1 |    2 | Ground    |      6 | Unfurnished |    2 |         0 | Property Features |
      |   7 |    5 |         4 |      1 | Furnished   |    4 |         0 | Property Features |
      |   1 |    2 | Ground    |      6 | Unfurnished |    1 |         0 | Property Features |
      |     |    2 |           |      2 |             |    7 |         0 | Property Features |

  @Unit @Positive @Tabs @Image @File @Input
  Scenario Outline: Verify Post Property Form image upload
    Given Selects "Flat/ Apartment" property type
    And Selects "<category>" image upload tab
    When User uploads "<img>" image "<count>" times
    Then "<count>" images are uploaded in "<category>" category

    Examples: 
      | category | img          | count |
      | exterior | exterior.jpg |     2 |
      | exterior | bedroom.jpg  |     1 |

  @Unit @Positive @Radio @Select
  Scenario Outline: Verify Post Property Form property availability
    Given Selects "Flat/ Apartment" property type
    When Selects "<status>" possession status
    And Selects availability month "<avail_m>" and year "<avail_y>" or "<age>" construction age
    And Click on Login & Post Property button
    Then "<err_count>" errors are displayed for "<section>"

    Examples: 
      | status             | avail_m | avail_y | age              | err_count | section                                 |
      | Under Construction | March   |    2024 |                  |         0 | Transaction Type, Property Availability |
      | Ready to Move      |         |         | New Construction |         0 | Transaction Type, Property Availability |

  @Unit @Negative @Checkbox
  Scenario: Verify Post Property Form without agreeing to terms
    Given Terms & Conditions checkbox is checked by default
    When User unchecks Terms & Conditions checkbox
    And Click on Login & Post Property button
    Then Terms & Conditions error is displayed

  @Functionality @Functional @Positive @Iframe @Input @Radio @Select @Image @Checkbox
  Scenario: Verify Post Property Form as owner
    Given User selects Owner user type
    When User enters name "Raghu"
    And Enters email "demo@test.raghu"
    And Country code "IND +91" and mobile number "9809807890" is pre-filled
    And Selects "Sale" property intent
    And Selects "Flat/ Apartment" property type
    And Selects "1" bedrooms
    And Selects "2" balconies
    And Selects "Ground" floor number
    And Selects "6" floors
    And Selects "Furnished" furnished
    And Selects "2" bathroom
    And Selects "exterior" image upload tab
    And Uploads "exterior.jpg" image "1" times
    And Selects "Under Construction" possession status
    And Selects availability month "June" and year "2023" or "" construction age
    And Terms & Conditions checkbox is checked by default
    And Unchecks exclusive and whatsapp checkbox
    And Click on Login & Post Property button
    Then OTP verification form is displayed
