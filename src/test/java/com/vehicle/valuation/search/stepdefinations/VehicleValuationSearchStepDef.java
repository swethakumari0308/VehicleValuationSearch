package com.vehicle.valuation.search.stepdefinations;

import com.vehicle.valuation.search.base.WebDriverFactory;
import com.vehicle.valuation.search.pageobject.SearchVehiclePage;
import com.vehicle.valuation.search.pageobject.VehicleDetailsPage;
import com.vehicle.valuation.search.utils.ReadInputFile;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class VehicleValuationSearchStepDef extends WebDriverFactory {

    private List<String> vehicleRegNumbers;
    private Map<String, List<String>> vehicleList;
    private SearchVehiclePage searchVehiclePage;
    private VehicleDetailsPage vehicleDetailspage;


    @Before
    public void setup() {
        WebDriverFactory.initializeBrowser();
    }

    @Given("Read vehicle registration details from input text file (.*)")
    public void read_vehicle_registration_details_from_input_text_file_car_input_txt(String inputFile) throws IOException {
        vehicleRegNumbers = ReadInputFile.getVehicleRegistrationNumbers(inputFile);
    }

    @When("Navigate website and search vehicle valuation details")
    public void navigate_website_and_search_vehicle_valuation_details() {
        vehicleList = new HashMap<String, List<String>>();
        searchVehiclePage = new SearchVehiclePage();
        vehicleDetailspage = new VehicleDetailsPage();
        for (String registrationNumber : vehicleRegNumbers) {
            List<String> vehicleDetailList = new ArrayList<String>();
            vehicleDetailspage = searchVehiclePage.sendRegistrationNumber(registrationNumber);
            if (vehicleDetailspage.isRegistrationNumberDisplayed()) {
                vehicleDetailList.add(vehicleDetailspage.getRegistrationNumber());
                vehicleDetailList.add(vehicleDetailspage.getModel());
                vehicleDetailList.add(vehicleDetailspage.getYear());
                vehicleList.put(registrationNumber, vehicleDetailList);

                searchVehiclePage = vehicleDetailspage.clickBackButtonToSearchVehiclePage();
            } else {
                System.out.println("Vehicle Registration details are not found :" + registrationNumber);
                searchVehiclePage = vehicleDetailspage.refreshPage();
            }

        }
    }

    @Then("Compare the output from the car valuation website with the (.*)")
    public void compare_the_output_from_car_valuation_website_with_the_output_text_file(String outputFile) throws IOException {
        Map<String, List<String>> expectedDetails = ReadInputFile.getExpectedDetails(outputFile);

        for (Map.Entry<String, List<String>> vehicleExpectedDetails : expectedDetails.entrySet()) {
            System.out.println("Expected Vehicle details : " + vehicleExpectedDetails.getKey() + ", Value : " + vehicleExpectedDetails.getValue());
        }
        for (Map.Entry<String, List<String>> vehicleActualDetails : vehicleList.entrySet()) {
            System.out.println("Actual Vehicle details : " + vehicleActualDetails.getKey() + ", Value : " + vehicleActualDetails.getValue());
        }
        assertEquals("Vehicle registration details are not matched with output vehicle details", expectedDetails, vehicleList);
    }

    @After
    public void tearDown() {
        WebDriverFactory.close();
    }

}