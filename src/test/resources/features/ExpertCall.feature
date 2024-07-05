@ExpertCall
Feature: Expert call booking

  Background: 
    Given User is on Advertisement Page

  @Functional @Functionality @Positive @Select @Input @Textarea
  Scenario Outline: Book an expert call with valid data
    Given User enters "<user_name>" name
    And Selects "<c_code>" country code
    And Enters "<mob_num>" mobile number
    And Enters "<email>" email
    And Enters "<city_key>" city key
    And Selects "<city>" city
    And Enter "<query>" query
    And Clicks on agreenment checkbox
    When User clicks on Get A Callback button
    Then Thank you message is displayed

    Examples: 
      | user_name | c_code  | mob_num    | email                | city_key | city        | query                     |
      | Andrew    | SGP +65 |   78766789 | andrew@example.dummy | singa    | Singasandra | A dummy query for testing |
      | Raghu     | IND +91 | 9999900000 | raghu@dummy.lew      | hyde     | Hyderabad   | A dummy query for testing |

  @Negative @Unit @Input
  Scenario Outline: Book an expert call with invalid name
    Given User enters "<user_name>" name
    When User clicks on Get A Callback button
    Then Error message "<err_msg>" is displayed for Username

    Examples: 
      | user_name                       | err_msg                                    |
      | An                              | Please enter a valid name.                 |
      |                             123 | Numbers or Special characters not allowed. |
      | Name3                           | Numbers or Special characters not allowed. |
      | A Very Very Long Long Name Name | Please enter a valid name.                 |

  @Negative @Unit @Input @Select
  Scenario Outline: Book an expert call with invalid phone
    Given User selects "<c_code>" country code
    And Enters "<mob_num>" mobile number
    When User clicks on Get A Callback button
    Then Error message "<err_msg>" is displayed for Mobile

    Examples: 
      | c_code  | mob_num | err_msg                                      |
      | IND +91 |     123 | Please enter a valid 10 digit mobile number. |
      | SGP +65 |   78989 | Please enter a valid mobile number.          |

  @Negative @Unit @Input
  Scenario Outline: Book an expert call with invalid email
    Given User enters "<email>" email
    When User clicks on Get A Callback button
    Then Error message "<err_msg>" is displayed for Email

    Examples: 
      | email        | err_msg                      |
      | asd          | Please enter valid Email Id. |
      | asd@         | Please enter valid Email Id. |
      | asd@jk       | Please enter valid Email Id. |
      | asd@jkil.    | Please enter valid Email Id. |
      | $asd@sdsa.dd | Please enter valid Email Id. |

  @Negative @Unit @Checkbox
  Scenario Outline: Book an expert call without agreeing to the terms
    When User clicks on Get A Callback button
    Then Error message "<err_msg>" is displayed for Agree to Terms

    Examples: 
      | err_msg                                      |
      | Please agree to the terms before proceeding. |
