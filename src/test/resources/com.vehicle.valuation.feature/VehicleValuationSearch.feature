@vehicleDetailsSearch
Feature:Perform Vehicle Details Search

  Scenario Outline: Read the vehicle registration details from input text file and perform vehicle valuation
    Given Read vehicle registration details from input text file <INPUT_FILE>
    When  Navigate website and search vehicle valuation details
    Then  Compare the output from the car valuation website with the <OUTPUT_FILE>
    Examples:
      | INPUT_FILE         | OUTPUT_FILE         |
      | car_input_V5.txt   | car_output_V5.txt   |