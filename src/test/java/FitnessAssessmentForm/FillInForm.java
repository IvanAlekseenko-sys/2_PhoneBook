package FitnessAssessmentForm;

import FitnessAssessmentForm.core.BaseHelper;
import FitnessAssessmentForm.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FillInForm extends TestBase {

    private BaseHelper baseHelper;

    public FillInForm() {
        super("chrome");
        baseHelper = new BaseHelper(driver);

    }

    @BeforeMethod
    public void setUpTest() {
        baseHelper = new BaseHelper(driver);
    }

    @Test
    public void fillInFormTest() {
        System.out.println("driver = " + driver);
        baseHelper.fillInNameCellEmail();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.getElementById('bday').value = '1990-07-30';"
        );
        driver.findElement(By.id("fsport")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement benchPressOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("option[value='Bench press']")
                )
        );
        benchPressOption.click();
        driver.findElement(By.id("yes")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


    @Test(dataProvider = "fillInCSVFormTest", dataProviderClass = DataProviders.class)
    public void fillInCSVFormTest(String name, String phoneNumber, String email) {
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "document.getElementById('bday').value = '1990-07-30';"
        );
        driver.findElement(By.id("cell")).click();
        driver.findElement(By.id("cell")).clear();
        driver.findElement(By.id("cell")).sendKeys(phoneNumber);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("fsport")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement benchPressOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("option[value='Bench press']")
                )
        );
        benchPressOption.click();
        driver.findElement(By.id("yes")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

}
