package com.vehicle.valuation.search.pageobject;

import com.vehicle.valuation.search.base.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * This class is used to search web elements in vehicle details page.
 */
public class VehicleDetailsPage extends WebDriverFactory {

    @FindBy(xpath = "//*[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']")
    private WebElement registrationNumber;

    @FindBy(xpath = " //*[@data-cy='vehicleMakeAndModel']")
    private WebElement model;

    @FindBy(css = ".HeroVehicle__details-XpAI>li:first-of-type")
    private WebElement year;

    @FindBy(id = "e2e-valueDifferentVehicle")
    private WebElement valueDifferentCar;


    public VehicleDetailsPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 60), this);
    }

    public String getRegistrationNumber() {
        return registrationNumber.getText();
    }

    public String getModel() {
        return model.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public SearchVehiclePage clickBackButtonToSearchVehiclePage() {
        webDriver.navigate().back();
        webDriver.navigate().refresh();

        return new SearchVehiclePage();
    }

    public SearchVehiclePage refreshPage() {
        webDriver.navigate().refresh();

        return new SearchVehiclePage();
    }

    public Boolean isRegistrationNumberDisplayed() {
        try {
            webDriver.findElement(new By.ByXPath("//*[@class='VRM-module__vrm-hdeF VRM-module__regular-RiIR VRM-module__front-BTQb']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}


