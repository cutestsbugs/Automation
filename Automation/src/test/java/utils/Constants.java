package utils;

public class Constants {

    //------------------Default Timeouts-----------------------
    public static final int ZERO_SECOND = 0;
    public static final int ONE_SECOND = 1;
    public static final int TIMEOUT = 60;

    //----------------Data Resources--------------------------
    public static final String DATA_DIRECTORY = "src/test/java/utils/data/";
    public static final String RESOURCES_DIRECTORY = "src/test/java/utils/resources/";

    //--------------------Environment--------------------------
    public static String SUITE;
    public static final String BROWSER = System.getProperty("browser");
    public static final String ENVIRONMENT = System.getProperty("environment");
    public static final boolean REMOTE = Boolean.parseBoolean(System.getProperty("remote"));
    public static final String CHROME_DRIVER = System.getProperty("chrome.driver");
    public static final String FIREFOX_DRIVER = System.getProperty("firefox.driver");

    //---------------------Selenium REMOTE----------------------------
    public static final String HUB_SERVER = "https://selenium.hostinger.io/wd/hub";

}