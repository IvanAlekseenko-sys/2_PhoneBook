package FitnessAssessmentForm.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    public WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInNameCellEmail() {
        type(By.id("name"),"Ivan");
        type(By.id("cell"),"+123-456-7890");
        type(By.id("email"),"test@test.com");
    }

    public void type(By locator, String text) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
}

