package utils.driver;

import static org.junit.Assert.fail;
import static utils.Constants.*;
import static utils.ErrorMessages.*;

public class DriverParameters {

    /**
     * Checks if necessary properties are specified for the project to run
     *
     * @return Boolean
     */

    public static boolean isParametersSpecified() {

        if (REMOTE) {
            //Test will run on Remote
            if (BROWSER == null) {
                fail(BROWSER_PARAMETER());
                return false;
            }
            if (ENVIRONMENT == null) {
                fail(ENVIRONMENT_PARAMETER());
                return false;
            }
        } else {
            //Test will run on Locally
            if (ENVIRONMENT == null) {
                fail(ENVIRONMENT_PARAMETER());
                return false;
            }
            if (BROWSER == null) {
                fail(BROWSER_PARAMETER());
                return false;
            } else switch (BROWSER) {
                case "chrome":
                    if (CHROME_DRIVER == null) {
                        fail(DRIVER_ERROR("chrome.driver"));
                        return false;
                    }
                    break;
                case "firefox":
                    if (FIREFOX_DRIVER == null) {
                        fail(DRIVER_ERROR("firefox.driver"));
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
