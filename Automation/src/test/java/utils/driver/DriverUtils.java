package utils.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotNull;
import static steps.Hooks.getScenario;
import static utils.ComponentUtils.maximizeWindow;
import static utils.Constants.*;
import static utils.WaitUtils.setImplicitWaitSeconds;
import static utils.WaitUtils.setPageLoadWaitSeconds;
import static utils.driver.DriverBuilder.createDriver;
import static utils.driver.DriverLanguages.findLanguageByName;

public class DriverUtils {

    /**
     * Driver Thread Object
     */
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    /**
     * Get Driver
     *
     * @return threadDriver
     */
    public static WebDriver driver() {
        return threadDriver.get();
    }

    /**
     * Set Driver
     *
     * @param driver WebDriver
     */
    public static void setDriver(WebDriver driver) {
        threadDriver.set(driver);
    }

    /**
     * Create Local or Remote driver
     */
    public static void startBrowser() {
        if (driver() == null) {
            setDriver(createDriver());
            assertNotNull("Driver was not created", driver());
            setPageLoadWaitSeconds(TIMEOUT);
            setImplicitWaitSeconds(TIMEOUT);
        } else {
            getScenario().log("Driver already running");
        }
    }

    /**
     * Quit Driver
     */
    public static void quitBrowser() {
        if (driver() != null) {
            driver().quit();
        }
        threadDriver.remove();
    }

    /**
     * Searches for language of driver.
     */
    public static DriverLanguages getDriverLanguage() {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        String language = (String) js.executeScript("return window.navigator.userLanguage || window.navigator.language");
        return findLanguageByName(language);
    }

}
