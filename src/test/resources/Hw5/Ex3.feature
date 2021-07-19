Feature: Testing Jdi site

  Scenario: User Table Page test
    Given I open JDI test site
    And I login as user "Roman" Iovlev
    And I click on button "Service" in Header part of page
    And I click on "User Table" button in Service dropdown menu
    When I select 'vip' checkbox for "Sergey Ivan"
    Then 1 log row has text "Vip: condition changed to true" in log section