Feature: Patient Creation in EHR :: This test is mainly to check the app is able to create patient with valid mandatory credentials.

  Background:
  Given I am Launching the Login page in "qa" env with "" browser

  Scenario Outline: Create a patient with mandatory credentials and verify the function.
    Given I have entered valid facility and username and password from propertyFile
    And While click on the login button
    When While click on the New Patient Icon
    And Enter the patient details <residentType>,<fName>,<mName>,<gender>,<dobValue>,<langg>,<email>,<Phone>
    Then The demographic page should have patient <fName> & <mName> in Demographics with wait 15 sec
    Then close the browser
    Examples:
      | residentType | fName | mName  | gender | dobValue   | langg  | email              | Phone      |
      | Resident     | Rock  | danny  | Female | 5/Nov/2000 | Dutch  | sample@yopmail.com | 9869897696 |
      | Baby         | John  | Robert | Female | 5/Nov/2010 | German | test@yopmail.com   | 9869897126 |