package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import utils.driver.DriverUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import static org.junit.Assert.fail;
import static steps.Hooks.getScenario;
import static utils.ComponentUtils.getPageTitle;
import static utils.ComponentUtils.switchToWindow;

public class UrlUtils extends DriverUtils {

    private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'Europe/Berlin'");

    /**
     * Output of all possible information if things go wrong
     */
    public static void printInfo() {

        time.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        long failTime = System.currentTimeMillis();
        if (getPageTitle().equals("403")) {
            fail("<<< Page is not accessible '403 Forbidden' >>>");
        } else {
            getScenario().log("Step failed at: " + time.format(failTime));
            try {
                getScenario().log("URL opened: " + getPageUrl());
            } catch (Exception e) {
                getScenario().log(">>> The currently opened URL could not be determined.<<<");
            }
        }
    }

    /**
     * Opens specified Url
     *
     * @param url address
     */
    public static void openUrl(String url) {
        try {
            getScenario().log("Opening URL: " + url);
            driver().get(url);
            getScenario().log("Opened URL: " + getPageUrl());
        } catch (TimeoutException timeout) {
            fail(url + " could not be opened, because TIMEOUT exception.");
        }
    }

    /**
     * Returns current Url address
     *
     * @return String
     */
    public static String getPageUrl() {
        return driver().getCurrentUrl();
    }

    /**
     * Opens Url in new Browser Tab
     */
    public void openUrlInNewTab(String url) {
        JavascriptExecutor js = (JavascriptExecutor) driver();
        String startTab = driver().getWindowHandle();
        js.executeScript("window.open()");
        ArrayList<String> newTab = new ArrayList<>(driver().getWindowHandles());
        newTab.remove(startTab);
        //Change focus to new tab
        switchToWindow(newTab.get(0));
        driver().navigate().to(url);
    }
}

