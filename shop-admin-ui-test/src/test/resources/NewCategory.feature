Feature: NewCategory

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    Then name should be "<name>"
    When I navigate to category page
    And I click on create category button
    And I provide category name as "<name2>"
    And I click on submit category button

    Examples:
      | username | password | name | name2
      | admin | admin | admin | luxury