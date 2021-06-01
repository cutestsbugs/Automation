
package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverUtils;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.fail;
import static utils.Constants.TIMEOUT;
import static utils.ErrorMessages.NO_SUCH_ELEMENT_ERROR;
import static utils.ErrorMessages.STALE_ELEMENT_ERROR;

public class WaitUtils extends DriverUtils {

    /**
     * Waits until Element is Displayed
     *
     * @param element WebElement
     */
    public static void waitUntilElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (StaleElementReferenceException e) {
            fail(STALE_ELEMENT_ERROR(element));
        }
    }

    /**
     * Wait until Element becomes Clickable
     *
     * @param element WebElement
     */
    public static void waitUntilElementToBeClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (StaleElementReferenceException e) {
            fail(STALE_ELEMENT_ERROR(element));
        }
    }

    /**
     * Wait until Element becomes Invisible
     *
     * @param element WebElement
     */
    @Deprecated
    public static void waitUntilElementInvisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (StaleElementReferenceException e) {
            fail(STALE_ELEMENT_ERROR(element));
        }
    }

    /**
     * Wait until element becomes Stale
     *
     * @param element WebElement
     */
    public static void waitUntilElementDisappears(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Wait for specified amount of seconds
     *
     * @param timeInSeconds Seconds(s)
     */
    public static void waitForSeconds(int timeInSeconds) {
        try {
            SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set ImplicitWait in Seconds
     *
     * @param timeInSeconds Seconds
     */
    public static void setImplicitWaitSeconds(int timeInSeconds) {
        driver().manage().timeouts().implicitlyWait(timeInSeconds, SECONDS);
    }

    /**
     * Set ImplicitWait in Milliseconds
     *
     * @param timeInMilliseconds Milliseconds
     */
    public static void setImplicitWaitMilliseconds(int timeInMilliseconds) {
        driver().manage().timeouts().implicitlyWait(timeInMilliseconds, MILLISECONDS);
    }

    /**
     * Reset ImplicitWait in Seconds
     */
    public static void resetImplicitWait() {
        driver().manage().timeouts().implicitlyWait(TIMEOUT, SECONDS);
    }

    /**
     * Sets Page Load time
     *
     * @param timeInSeconds Seconds
     */
    public static void setPageLoadWaitSeconds(int timeInSeconds) {
        driver().manage().timeouts().pageLoadTimeout(timeInSeconds, SECONDS);
    }

    /**
     * Wait for exact Url to be
     *
     * @param Url Address
     */
    public static void waitForExactUrl(String Url) {
        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
        wait.until(ExpectedConditions.urlToBe(Url));
    }

    /**
     * Wait until element attribute will have specified value
     *
     * @param element       WebElement
     * @param attribute     Attribute. Example: style, class and etc.
     * @param expectedValue Expected value
     */
    public static void waitForElementAttributeToBe(WebElement element, String attribute, String expectedValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.attributeToBe(element, attribute, expectedValue));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (TimeoutException timeoutException) {
            fail("Element attribute: '" + attribute + "' is not equal to expected value: " + expectedValue);
        }
    }

    /**
     * Wait until element attribute will contain specified value
     *
     * @param element       WebElement
     * @param attribute     Attribute. Example: style, class and etc.
     * @param expectedValue Expected value
     */
    public static void waitForElementAttributeContains(WebElement element, String attribute, String expectedValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
            wait.until(ExpectedConditions.attributeContains(element, attribute, expectedValue));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (TimeoutException timeoutException) {
            fail("Element attribute: '" + attribute + "' does not have expected value: " + expectedValue);
        }
    }

    /**
     * Wait for Url to contains part of Url
     *
     * @param partOfUrl Address
     */
    public static void waitForUrlToContain(String partOfUrl) {
        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
        wait.until(ExpectedConditions.urlContains(partOfUrl));
    }

    /**
     * Wait for Element Value not to be empty
     *
     * @param element WebElement
     */
    public static void waitForElementValueNotToBeEmpty(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver(), TIMEOUT);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
    }
}