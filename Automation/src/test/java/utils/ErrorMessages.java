package utils;

import org.openqa.selenium.WebElement;

public class ErrorMessages {

    public static String FIREFOX_NOT_STARTED(String type) {
        return ">>> " + type + " FIREFOX driver was not started! <<<";
    }

    public static String CHROME_NOT_STARTED(String type) {
        return ">>> " + type + " CHROME driver was not started! <<<";
    }

    public static String NO_SUCH_ELEMENT_ERROR(WebElement element) {
        return ">>> Element: " + element + " NOT FOUND! <<<";
    }

    public static String STALE_ELEMENT_ERROR(WebElement element) {
        return ">>> Element: " + element + " BECOME STALE! <<<";
    }

    public static String NO_SUCH_FRAME_ERROR(WebElement frame) {
        return ">>> Frame: " + frame + " NOT FOUND! <<<";
    }

    public static String NO_SUCH_WINDOW_ERROR(String window) {
        return ">>> Window " + window + " NOT FOUND! <<<";
    }

    public static String ELEMENT_COVERED_ERROR(WebElement element) {
        return ">>> Element '" + element + "' is covered and CAN'T BE CLICKED! <<<";
    }

    public static String NOT_INTERACTABLE_ELEMENT_ERROR(WebElement element) {
        return ">>> Element '" + element + "' is not interactable! <<<";
    }

    public static String DOMAIN_ERROR(String domainName, String domainStatus) {
        return ">>> " + domainName + " domain with '" + domainStatus + "' doesn't exist on this Account! <<<";
    }

    public static String ELEMENT_IS_NOT_DISPLAYED(WebElement element) {
        return ">>> Element: " + element + " WAS NOT DISPLAYED! <<<";
    }

    public static String CANNOT_GET_VALUE(WebElement element) {
        return ">>> Cannot get 'Value' from element " + element + " !<<<";
    }

    @SuppressWarnings("SameReturnValue")
    public static String BROWSER_PARAMETER() {
        return ">>> Parameter: '-Dbrowser=' is not specified or incorrect. It should be 'chrome' or 'firefox'! <<<";
    }

    @SuppressWarnings("SameReturnValue")
    public static String ENVIRONMENT_PARAMETER() {
        return ">>> Parameter: '-Denvironment=' is not specified or incorrect. It should be 'prod', 'stage' or 'dev! <<<";
    }

    public static String DRIVER_ERROR(String driver) {
        return ">>> Parameter: '-D" + driver + "=' is not specified or incorrect. It's path to your local driver! <<<";
    }
}
