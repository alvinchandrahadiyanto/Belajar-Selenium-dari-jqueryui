Feature: Checkout

  Scenario: Successful checkout
    Given saya mengisi firstName dengan "Budi"
    And saya mengisi lastName dengan "Setiawan"
    And saya mengisi postalCode dengan "1234"
    When saya melanjutkan proses hingga checkout
    Then saya mendapatkan pesan "Thank you for your order!"