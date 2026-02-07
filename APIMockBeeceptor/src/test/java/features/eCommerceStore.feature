Feature: As a front end consumer of e-commerce store, I want all endpoints in the mock to be tested

Scenario: Get a list of all registered users 
	Given I issue an API request "GetUsersAPI" with "GET" HTTP request
	Then the HTTP response will return status 200
	And We validate API response data against a specification

Scenario: Get a list of products available in the e-commerce store 
	Given I issue an API request "GetProductsAPI" with "GET" HTTP request
	Then the HTTP response will return status 200
	And We validate API response data against a specification

Scenario: Get a list of shopping buckets/carts 
	Given I issue an API request "GetCartsAPI" with "GET" HTTP request
	Then the HTTP response will return status 200
	And We validate API response data against a specification

Scenario: Get a list of orders placed by users and their status 
	Given I issue an API request "GetOrdersAPI" with "GET" HTTP request
	Then the HTTP response will return status 200
	And We validate API response data against a specification

Scenario Outline: Create a new order or update an existing
	Given I issue an API request "PutOrdersAPI" with "PUT" HTTP request
    And the user with id 1 is authenticated
    And the following items are added to the order
      | product_id | quantity |
      | 1          | 2        |
      | 3          | 1        |	
    Then the HTTP response will return status 200
	And We validate API response data against a specification
#	And We validate API response schema against a specification
