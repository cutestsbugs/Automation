package utils.driver;

import static org.junit.Assert.fail;

public enum DriverLanguages {

    US("en-US", "MM-dd-yyyy"),
    GB("en-GB", "yyyy-MM-dd");

    public final String name;
    public final String datePattern;

    DriverLanguages(String name, String datePattern) {
        this.name = name;
        this.datePattern = datePattern;
    }

    public static DriverLanguages findLanguageByName(String searchForLanguage) {
        for (DriverLanguages lang : DriverLanguages.values()) {
            if (lang.name.contains(searchForLanguage)) {
                return lang;
            }
        }
        fail("Language with name: '" + searchForLanguage + "' wasn't found");
        return null;
    }
}