package suites;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static utils.Constants.SUITE;
import static utils.driver.DriverParameters.isParametersSpecified;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:reports/runTest.html",
                "json:reports/runTest.json",
                "pretty",
                "summary"
        }
        , features = {"src/test/java/tests"}
        , tags = ("@Prod and not (@Ignore or @Ignored)")
        , glue = {"steps"}
)
public class runTest {

    @BeforeClass
    public static void actionsBeforeSuite() {
        SUITE = runTest.class.getSimpleName();
        assertTrue(">> Required Properties are not set <<", isParametersSpecified());
    }

    @AfterClass
    public static void actionsAfterSuite() {
    }

}
