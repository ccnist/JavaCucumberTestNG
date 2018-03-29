Feature: Verify Links on page

  @project
  Scenario: Verify links on page
    Given I navigated to home page
    And I read url from data source
    When I visit link in data source
    And I write link details to data source on page
