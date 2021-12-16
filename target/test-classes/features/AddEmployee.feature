Feature: Employee search

  Background:
    And user is logged in with valid admin credentials
    When user clicks on PIM option
    And user clicks on Add Employee button

  @1027
  Scenario: First scenario of adding the employee
    And user enters firstname, middlename, and lastname
    And user clicks on save button
    Then employee is added successfully

  @1027
  Scenario: Second scenario of adding employee
    And user enters firstname, middlename, and lastname
    When user deletes employee id
    And user clicks on save button
    Then employee is added successfully

  @1027
  Scenario: Third scenario to adding the employee
    And user enters firstname, middlename, and lastname
    And user selects checkbox
    When user enters username password and confirm password
    And user clicks on save button
    Then employee is added successfully

  @1028
  Scenario: Adding an employee from feature file
    And user enters "Fire" "Fist" and "Ace"
    And user clicks on save button
    Then employee is added successfully

  @examples
  Scenario Outline: adding an employee from feature file
    And user enters "<firstName>" "<middleName>" and "<lastName>" for an employee
    And user clicks on save button
    Then employee is added successfully
    Examples:
      | firstName | middleName | lastName   |
      | Dexter    | L          | Laboratory |
      | Donald    | M          | Duck       |
      | Scooby    | D          | Doo        |

  Scenario: Adding an employee using data table
    When I add multiple employees and verify them that user has been added successfully
      | firstName | middleName | lastName   |
      | Dexter    | L          | Laboratory |
      | Donald    | M          | Duck       |
      | Scooby    | D          | Doo        |
      | Bugs      | B          | Bunny      |
      | Mickey    | M          | Mouse      |

  @excel
  Scenario: Adding an employee from excel file
    When user adds multiple employees from excel file using "EmployeeSheet" sheet and verify the added employee

  @editing
  Scenario: Editing employee
    When user is on personal details, user should be able to edit all information
      | firstName | middleName | lastName |
      | Natsu     | D          | Dragneel |

  @db
  Scenario: Adding employee and validating in Database
    When user enters "Spider" "Spidey" and "Man"
    And captures employee id
    And user clicks on save button
    And employee is added successfully
    Then query database and get data
    And verify employee data is matched in ui and db