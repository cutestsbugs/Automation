package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Constants.*;
import static utils.ErrorMessages.CHROME_NOT_STARTED;
import static utils.ErrorMessages.FIREFOX_NOT_STARTED;

public class DriverBuilder extends DriverUtils {

    /**
     * Creates Driver
     *
     * @return Local or Remote Driver
     */

    public static WebDriver createDriver() {

        if (BROWSER.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-default-apps");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--enable-automation");
            chromeOptions.addArguments("--test-type");
            //chromeOptions.addArguments("--headless");
            //chromeOptions.addArguments("--window-size=1366,768");
            //chromeOptions.addArguments("--window-size=1920,1080");

            chromeOptions.setPageLoadStrategy(NORMAL);

            if (REMOTE) {
                try {
                    return new RemoteWebDriver(new URL(HUB_SERVER), chromeOptions);
                } catch (MalformedURLException malformedURLException) {
                    fail(CHROME_NOT_STARTED("Remote"));
                }
            } else {
                try {
                    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                    return new ChromeDriver(chromeOptions);
                } catch (Exception exception) {
                    fail(CHROME_NOT_STARTED("Local"));
                }
            }
        }
        return null;
    }
}
