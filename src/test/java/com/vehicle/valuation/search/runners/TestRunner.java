package com.vehicle.valuation.search.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "target/test-classes"},
        plugin = {
                "pretty",
                "html:target/Valuation-Test-Report",
                "json:target/Valuation-Test-Report/cucumber.json",
                "rerun:target/Valuation-Test-Report/rerun.txt"},
        tags = "@vehicleDetailsSearch",
        glue = {"com/vehicle/valuation/search/stepdefinations/"})
public class TestRunner {
}
