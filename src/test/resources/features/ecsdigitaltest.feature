Feature: ECSDigital Engineer in Test tech test. Below are a number of rows with integers.return the index of the
  array where the sum of integers at the index on the left is equal to the sum of integers on the right.

  Scenario: 01 - Below are a number of rows with integers.
    Given launch browser 'chrome'
    And user navigates to the URL 'http://localhost:3000/'
    When system calculates the results, enters values to the challenge fields and submits answers button
    Then system should be give the message

  Scenario: 02 - Below are a number of rows with integers.
    Given launch browser 'firefox'
    And user navigates to the URL 'http://localhost:3000/'
    When system calculates the results, enters values to the challenge fields and submits answers button
    Then system should be give the message