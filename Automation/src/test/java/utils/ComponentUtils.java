package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.Select;
import utils.driver.DriverUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static steps.Hooks.getScenario;
import static utils.Constants.*;
import static utils.ErrorMessages.*;
import static utils.WaitUtils.*;

public class ComponentUtils extends DriverUtils {

    /**
     * Switch to specified iFrame by Name
     *
     * @param frameElement Frame Name
     */
    public static void switchToFrame(WebElement frameElement) {
        try {
            switchToDefaultContent();
            driver().switchTo().frame(frameElement);
        } catch (NoSuchFrameException e) {
            fail(NO_SUCH_FRAME_ERROR(frameElement));
        }
    }

    /**
     * Switches to specified Browser Window
     *
     * @param window Window
     */
    public static void switchToWindow(String window) {
        try {
            driver().switchTo().window(window);
        } catch (NoSuchWindowException e) {
            fail(NO_SUCH_WINDOW_ERROR(window));
        }
    }

    /**
     * Clear the cookies
     */
    public static void clearCookies() {
        driver().manage().deleteAllCookies();
    }

    /**
     * Add specific Cookie
     *
     * @param domain     Cookie Domain
     * @param cookieName Cookie name
     * @param value      Cookie value
     */

    public static void addCookie(String domain, String cookieName, String value) {
        Cookie cookie = new Cookie.Builder(cookieName, value)
                .domain(domain)
                .build();
        driver().manage().addCookie(cookie);
        getScenario().log(">>> Cookie: " + cookieName + ":" + value + " was added. <<<");
    }

    /**
     * Maximizes browser Window
     */
    public static void maximizeWindow() {
        driver().manage().window().maximize();
    }

    /**
     * Switch to Default content
     */
    public static void switchToDefaultContent() {
        driver().switchTo().defaultContent();
    }

    /**
     * Select option by Displayed text
     *
     * @param element WebElement
     * @param text    String
     */
    public static void selectByDisplayedText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Selects option by Value
     *
     * @param element WebElement
     * @param value   String
     */
    public static void selectByStringValue(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Select option by Int value
     *
     * @param element WebElement
     * @param value   Integer
     */
    public static void selectByIntValue(WebElement element, int value) {
        try {
            selectByStringValue(element, String.valueOf(value));
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Enter text into Input field
     *
     * @param element WebElement
     * @param text    String
     */
    public static void enterTextToField(WebElement element, String text) {
        try {
            scrollToElement(element);
            waitUntilElementToBeClickable(element);
            element.click();
            if (getValueFromElement(element).length() > 0) {
                while (getValueFromElement(element).length() > 0) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
            }
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Checks if element is available at all
     *
     * @param elementAvailability lambda expression of element. example: () -> getWebElement()
     * @return boolean saying if element is visible or not.
     */
    public static boolean isElementAvailable(ElementAvailability elementAvailability) {
        setImplicitWaitSeconds(ZERO_SECOND);
        try {
            elementAvailability.element();
            resetImplicitWait();
            return true;
        } catch (Exception e) {
            resetImplicitWait();
            return false;
        }
    }

    /**
     * Returns if Element is Displayed or not
     *
     * @param element WebElement
     */
    public static void isElementDisplayed(WebElement element) {
        try {
            waitUntilElementDisplayed(element);
            assertTrue(ELEMENT_IS_NOT_DISPLAYED(element), element.isDisplayed());
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Click on the WebElement
     *
     * @param element WebElement
     */
    public static void clickElement(WebElement element) {
        try {
            waitUntilElementDisplayed(element);
            scrollToElement(element);
            waitUntilElementToBeClickable(element);
            element.click();
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        } catch (ElementClickInterceptedException e) {
            fail(ELEMENT_COVERED_ERROR(element));
        } catch (ElementNotInteractableException e) {
            fail(NOT_INTERACTABLE_ELEMENT_ERROR(element));
        }
    }

    /**
     * Returns String from Element Value attribute
     *
     * @param element WebElement
     * @return String from 'value' attribute
     */
    public static String getValueFromElement(WebElement element) {
        try {
            return element.getAttribute("value");
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
            return CANNOT_GET_VALUE(element);
        }
    }

    /**
     * Returns String from Element Style attribute
     *
     * @param element WebElement
     * @return String from 'style' attribute
     */
    public static String getStyleFromElement(WebElement element) {
        try {
            return element.getAttribute("style");
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
            return null;
        }
    }

    /**
     * Returns String from Element Class attribute
     *
     * @param element WebElement
     * @return String from 'class' attribute
     */
    public static String getClassFromElement(WebElement element) {
        try {
            return element.getAttribute("class");
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
            return null;
        }
    }

    /**
     * Returns String from Element href attribute
     *
     * @param element WebElement
     * @return String from 'href' attribute
     */
    public static String getHrefFromElement(WebElement element) {
        return element.getAttribute("href");
    }

    /**
     * Closes current browser Tab and switches to initial Tab
     */
    public static void closeTab() {
        driver().close();
        switchToFirstTab();
    }

    /**
     * Refresh open browser Tab
     */
    public static void refreshPage() {
        driver().navigate().refresh();
    }

    /**
     * Get handle of currently opened Browser tab
     */
    public static String getActualHandleTab() {
        return driver().getWindowHandle();
    }

    /**
     * Switches to Newest browser Tab (when new Tab was opened)
     */
    public static void switchToNewestTab() {
        ArrayList<String> handles = new ArrayList<>(driver().getWindowHandles());
        switchToWindow(handles.get(handles.size() - 1));
    }

    /**
     * Get a name of current IFrame
     */
    public static String getIFrameName() {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        return (String) js.executeScript("return self.name");
    }

    /**
     * Asserts if List contains specified string
     *
     * @param expected Expected text
     * @param list     List of Strings
     */
    public static void assertListContainsString(String expected, List<String> list) {
        assertTrue("List did not contained " + expected + " text", list.stream().anyMatch(i -> i.contains(expected)));
    }

    /**
     * Get current tab Title
     *
     * @return Tab title
     */
    public static String getPageTitle() {
        try {
            return driver().getTitle();
        } catch (NullPointerException e) {
            return "403";
        }
    }

    /**
     * Switches to First browser tab
     */
    public static void switchToFirstTab() {
        ArrayList<String> handles = new ArrayList<>(driver().getWindowHandles());
        switchToWindow(handles.get(0));
    }

    /**
     * Scrolls element to screen center
     *
     * @param element WebElement
     */
    public static void scrollToElement(WebElement element) {
        //Possible options are: "start", "center", "end", or "nearest". Defaults to "center".
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver();
            //js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
            js.executeScript("arguments[0].scrollIntoView({block: \"center\"});", element);
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Returns Input field value by JavaScript
     *
     * @param element WebElements
     * @return Sting value
     */
    public static String getInputValueWithJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        return (String) js.executeScript("return arguments[0].value", element);
    }

    /**
     * Scrolls to specified location of page (Top or Bottom)
     *
     * @param position Values are Top or Bottom
     */
    public static void scrollToPagePosition(String position) {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        if (position.equalsIgnoreCase("Top")) {
            js.executeScript("window.scrollTo(0,0)");
        } else if (position.equalsIgnoreCase("Bottom")) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } else {
            fail("Could not scroll Page to " + position + ", because such Position does not exist");
        }
    }

    /**
     * Press on back button in the browser
     */
    public static void goBackOnePage() {
        driver().navigate().back();
    }

    /**
     * Pass file path to Input element
     *
     * @param element  WebElement
     * @param fileName String
     */
    public static void uploadFile(WebElement element, String fileName) {
        try {
            LocalFileDetector detector = new LocalFileDetector();
            String path = new File(DATA_DIRECTORY).getAbsolutePath() + "/" + fileName;
            File file = detector.getLocalFile(path);
            element.sendKeys(file.getAbsolutePath());
        } catch (NoSuchElementException e) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Get current date in US format MM/dd/YYYY
     *
     * @return String
     */
    public static String getCurrentDateUSFormat() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        return timeFormatter.format(currentDate);
    }

    /**
     * Get current date in EU format yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDateEUFormat() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();
        return timeFormatter.format(currentDate);
    }

    /**
     * Generates Random number in the scope of 9 integers
     *
     * @return String
     */
    public static String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(999999999);
        return Integer.toString(number);
    }

    /**
     * Accept OK on Alert popup
     */
    public static void acceptAlert() {
        Alert alert = driver().switchTo().alert();
        alert.accept();
        switchToFirstTab();
    }

    /**
     * Hover over WebElement
     *
     * @param element WebElement
     */
    public static void hoverOverElement(WebElement element) {
        try {
            Actions action = new Actions(driver());
            action.moveToElement(element).perform();
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Returns Long number where in which location page is Scrolled
     *
     * @return Long Number which indicates number by Y axi (vertical)
     */
    public static Long getPageScrollLocation() {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        return (Long) js.executeScript("return window.pageYOffset;");
    }

    /**
     * Test that Scrolling is working when Specified button was pressed
     *
     * @param element WebElement
     */
    public static void isScrollWorking(WebElement element) {
        try {
            scrollToElement(element);
            Long positionBefore = getPageScrollLocation();
            clickElement(element);
            waitForSeconds(ONE_SECOND);
            Long positionAfter = getPageScrollLocation();
            assertNotEquals("Scroll was not working when '" + element.getText() + "' button have been clicked.", positionBefore, positionAfter);
        } catch (NoSuchElementException noSuchElementException) {
            fail(NO_SUCH_ELEMENT_ERROR(element));
        }
    }

    /**
     * Sets specific Attribute and it's Value for Element
     *
     * @param element   WebElements
     * @param attribute HTML Attribute (div, span and etc.)
     * @param value     HTML Attribute value
     */
    public static void setElementAttributeValue(WebElement element, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
    }

    /**
     * Checks if Test are running on Panel and Development environment
     *
     * @return True or False
     */
    public static Boolean isPanelDev() {
        return ENVIRONMENT.equals("dev") && SUITE.contains("panel");
    }

    /**
     * Scrolls to end of page by looping over browser height
     */
    public void scrollToPageEndDynamically() {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        int browserHeight = driver().manage().window().getSize().height;
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        int scrollIterator = 1;
        while (true) {
            do {
                js.executeScript("window.scrollTo(0, " + (browserHeight * scrollIterator) + ")");
                scrollIterator++;
                waitForSeconds(ONE_SECOND);
            } while ((long) browserHeight * scrollIterator < lastHeight);

            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }
    }

    /**
     * Makes Screenshot of Browser window
     */
    public void makeScreenshot(String screenshotName) {
        getScenario().log(">>> Visual information below <<<");
        byte[] screenshot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
        getScenario().attach(screenshot, "image/png", "Failing Screenshot: " + screenshotName);
    }

    public interface ElementAvailability {
        void element();
    }
}