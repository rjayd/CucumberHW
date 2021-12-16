Feature: Job Title List Verification and Office Location Verification

  @job
  Scenario: Job Title List Verification
    Given user is able to login as Admin
    When user clicks on Job Title under Jobs
    And when user query database and get data
    Then user should be able to verify the list against the SQL list

    @location
    Scenario: Office Location Verification
      Given user is able to login as Admin
      When user clicks on location under Organization
      Then user should verify result with SQL