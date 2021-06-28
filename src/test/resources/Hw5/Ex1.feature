Feature: Testing JDI site

  Scenario: Different Element Page test
    Given I open Jdi site
    And I login as "Roman" Iovlev
    When I click on "Service" button in Header
    And I click on "Different Elements" in Service dropdown
    Then "Different Elements" page should be opened
    When I click on "Water" and "Wind" checkboxes
    And I click on "Selen" radiobutton
    And I click on dropdown menu
    And I click on "Yellow" string in dropdown menu
    Then 1 log row has "Water: condition changed to true" text in log section
    And 1 log row has "Wind: condition changed to true" text in log section
    And 1 log row has "metal: value changed to Selen" text in log section
    And 1 log row has "Colors: value changed to Yellow" text in log section
