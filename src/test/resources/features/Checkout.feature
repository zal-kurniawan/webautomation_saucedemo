Feature: Checkout Scenario

Scenario: User can checkout product successfully
    When User login with valid credentials
    Then User is navigated to the products page
    When User adds a product to the cart
    And User click on the cart icon
    Then User verify the product is added to the cart
    When User click on the checkout buton
    Then User navigated to the information page
    When User fill information
    And User click to the continue button
    Then User verify the product on the checkout page
    When User click on the finish button
    Then User verify the order is placed successfully

Scenario: User can't checkout product with invalid credentials
    When User login with invalid credentials
    Then User will see error message login

Scenario Outline: User can't checkout product with missing information
    When User login with valid credentials
    Then User is navigated to the products page
    When User adds a product to the cart
    And User click on the cart icon
    Then User verify the product is added to the cart
    When User click on the checkout buton
    Then User navigated to the information page
    When User fill information with "<firstname>", "<lastname>", and "<postalcode>"
    And User click to the continue button 
    Then User will see error messaage information

    Examples:
      | firstname               | lastname            |   postalcode  |
      | Joko                    |                     |   612092      |
      |                         | Darmono             |   612903      |
      | Adi                     | Ida                 |               |