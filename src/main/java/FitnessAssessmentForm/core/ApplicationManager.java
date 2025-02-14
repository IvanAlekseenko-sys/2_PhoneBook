package FitnessAssessmentForm.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("chrome_headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            //driver = new ChromeDriver();
            throw new IllegalArgumentException("❌ Некорректный браузер: " + browser + ". Доступные варианты: chrome, firefox, edge, safari.");
        }
        driver.get("https://ivanalekseenko-sys.github.io/VSC-rep-Frontend/AIT-52.2/homework/lesson_02/index.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void stop() {
        driver.quit();
    }

    public String takeScreenshot() {
        // Check for alert before taking the screenshot
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Timeout for alert detection
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();  // or alert.dismiss(); based on your use case
            System.out.println("Alert was present and accepted.");
        } catch (NoAlertPresentException e) {
            // No alert, proceed with screenshot capture
        }

        // Capture screenshot
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }
}

