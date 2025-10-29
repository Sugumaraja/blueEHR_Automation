Feature: Login function and Page Navigation Check.
  This test is mainly to check the app is login with valid credentials.
  and navigating to the next page.
   and while try to login with invalid credentials getting error message.

  Background:
  Given I am Launching the Login page in "qa" env with "chrome" browser

    Scenario: Successful Login with valid credentials.
      Given I have entered valid facility and username and password from propertyFile
      When While click on the login button
      Then The user should get logged in succesfully
      Then close the browser


  Scenario Outline: While login with invalid credentials check the error message
    Given I have entered the <facility> and <username> and <password>
    When While click on the login button
    Then The app should contain <error_message>
    Then close the browser
    Examples:
      | facility      | username | password | error_message                |
      | supporttest18 | zhadmin  | Abcd@123 | invalid username or password |
      | supporttest9  | zhadmin  | Newpass  | invalid username or password |
