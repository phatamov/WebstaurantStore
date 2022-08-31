Feature: As a user, I should be able to search and add selected items to cart

  Background: Common steps for all scenarios
    Given The user is on the homepage

  @SearchTest
  Scenario: Search and add selected items to cart
    When User searches for 'stainless work table' from Homepage Search Bar
    Then Verify every product in search results has the word 'table' in its title
    When User adds the last of found items from search results to Cart
    Then Verify the item is added to Cart
    When User clicks on Empty Cart button from View Cart page
    Then Verify Cart is empty