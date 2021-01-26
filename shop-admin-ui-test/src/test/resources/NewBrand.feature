Feature: NewBrand

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When I navigate to brand page
    And I click on create brand button
    And I provide name as "<name>"
    And I click on submit brand button

    Examples:
      | username | password | name |
      | admin | admin | admin |