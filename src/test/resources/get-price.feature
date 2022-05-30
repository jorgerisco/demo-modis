Feature: Get final price (PVP) and the rate that applies to a product of a brand between specific dates
  Scenario Outline: As a user, I should be able to get price of a product
    When the user call to endpoint GET with params '<date>', '<brandId>' and '<productCode>'
    Then the user receives status code 200
    And verifies that the results correspond to expected result <price>
    Examples:
      | date                | brandId | productCode | price |
      | 2020-06-14 10:00:00 | 1       | 35455       | 35.50 |
      | 2020-06-14 16:00:00 | 1       | 35455       | 25.45 |
      | 2020-06-14 21:00:00 | 1       | 35455       | 35.50 |
      | 2020-06-15 10:00:00 | 1       | 35455       | 30.50 |
      | 2020-06-16 21:00:00 | 1       | 35455       | 38.95 |
      | 2020-06-14 10:00:00 | 2       | 35455       | 35.50 |
      | 2020-06-14 16:00:00 | 2       | 35455       | 25.45 |
      | 2020-06-15 10:00:00 | 3       | 35455       | 30.50 |
      | 2020-06-14 10:00:00 | 2       | 35456       | 35.50 |
      | 2020-06-14 16:00:00 | 2       | 35456       | 25.45 |
      | 2020-06-15 06:00:00 | 3       | 35456       | 30.50 |