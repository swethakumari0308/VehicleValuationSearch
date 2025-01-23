package com.vehicle.valuation.search.pageobject;

import com.vehicle.valuation.search.base.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is used to search vehicle registration web elements.
 */
public class SearchVehiclePage extends WebDriverFactory {

    @FindBy(xpath = "//input[@id='vrm-input' and @name='vrm-input']")
    private WebElement vehicleRegNumber;

    @FindBy(xpath = "//button[@type='submit'][1]")
    private WebElement valueYourCar;

    public SearchVehiclePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 60), this);
    }

    public VehicleDetailsPage sendRegistrationNumber(String registrationNumber) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        vehicleRegNumber.sendKeys(registrationNumber);
        valueYourCar.click();

        return new VehicleDetailsPage();
    }

}
