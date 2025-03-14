package FitnessAssessmentForm;

import FitnessAssessmentForm.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase extends ApplicationManager {

    Logger logger = LoggerFactory.getLogger(FitnessAssessmentForm.TestBase.class);

    public TestBase(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        init();
    }


    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        //stop();
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + takeScreenshot() + "]");
        }
    }

}
