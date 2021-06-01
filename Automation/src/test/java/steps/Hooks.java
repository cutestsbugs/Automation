package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ComponentUtils;

import static utils.UrlUtils.printInfo;
import static utils.driver.DriverUtils.quitBrowser;
import static utils.driver.DriverUtils.startBrowser;

public class Hooks {

    private static final ThreadLocal<String> threadTestName = new ThreadLocal<>();
    private static final ThreadLocal<Scenario> threadScenario = new ThreadLocal<>();
    private final ComponentUtils componentUtils = new ComponentUtils();

    public static String getTestName() {
        return threadTestName.get();
    }

    public static void setTestName(String testName) {
        threadTestName.set(testName);
    }

    public static Scenario getScenario() {
        return threadScenario.get();
    }

    public static void setScenario(Scenario scenario) {
        threadScenario.set(scenario);
    }

//--------------- WebDriver Hooks-------------------

    @Before
    public static void before(Scenario scenario) {
        setTestName(scenario.getName());
        startBrowser();
        setScenario(scenario);
        scenario.log("------------------------------\n");
        scenario.log("Test Started: " + getTestName() + "\n");
    }

    @After
    public void cleanup(Scenario scenario) {
        if (scenario.isFailed()) {
            printInfo();
            componentUtils.makeScreenshot(getTestName());
        }
        scenario.log("Test Status: " + getScenario().getStatus() + "\n");
        scenario.log("------------------------------\n");
        quitBrowser();
    }
//----------------------------------------------------------
}

