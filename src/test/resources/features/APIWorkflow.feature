Feature: Syntax HRM API Workflow

  Background:
    Given a JWT is generated

  @APIWorkflow
  Scenario: Creating an employee
    Given a request is prepared for creating an employee
    When a POST call is made to create and employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for the other calls

  @APIWorkflow
  Scenario: Retrieving created employees
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created employee
    Then the status code for this employee is 200
    And the retrieved employee id "employee.employee_id" should match the globally stored employee_id
    And the retrieved data at the "employee" object matches the data used to create an employee with employee_id "employee.employee_id"
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_status | emp_job_title   |
      | Bugs          | The             | Bunny        | 1989-12-21   | Male       | Employee   | Cloud Architect |

    @Dynamic
    Scenario: Create dynamic scenario
      Given a request is prepared for creating an employee with dynamic data "Firefist","Ace","D","M","1989-12-25","Employee","API Tester"
