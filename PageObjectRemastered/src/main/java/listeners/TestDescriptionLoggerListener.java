package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestDescriptionLoggerListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestDescriptionLoggerListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        String methodName = result.getMethod().getMethodName();

        if (description != null && !description.isEmpty()) {
            logger.info("=== STARTING TEST: " + description + " ===");
        } else {
            logger.info("=== STARTING TEST METHOD: " + methodName + " ===");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== PASSED: " + result.getMethod().getMethodName() + " ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("=== FAILED: " + result.getMethod().getMethodName() + " ===");
    }
}
