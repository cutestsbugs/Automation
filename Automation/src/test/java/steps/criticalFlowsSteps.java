package steps;

import elements.HomePageElements;
import elements.LoginPageElements;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.userWebsitePage;

import static utils.ComponentUtils.clickElement;
import static utils.ComponentUtils.enterTextToField;
import static utils.WaitUtils.waitForUrlToContain;
import static utils.driver.DriverUtils.driver;

public class criticalFlowsSteps {

    private final HomePageElements homePage = new HomePageElements();
    private final LoginPageElements loginPage = new LoginPageElements();
    private final userWebsitePage userWebsite = new userWebsitePage();

    @When("user opens Home page {string}")
    public void userOpensHomePage(String url) {
        driver().get(url);
        //clickElement(homePage.getGetStartedButton());
    }

    @And("user clicks Sign in button")
    public void userClicksSignInButton() {
        clickElement(homePage.getSigInButton());
        waitForUrlToContain("https://zyro.com/signin");

    }

    @And("user Sign in with Email {string} and Password {string}")
    public void userSignInWithEmailAndPassword(String email, String password) {
        enterTextToField(loginPage.getSignInInputFieldEmail(), email);
        enterTextToField(loginPage.getSignInInputFieldPassword(), password);
        clickElement(loginPage.getSignInButton());
    }

    @When("user opens website page {string}")
    public void userOpensWebsitePage(String url) {
        driver().get(url);

    }

    @And("user clicks on {string} link")
    public void userClicksOnLink(String menuName) {
       userWebsite.clickMenu(menuName);

    }
}
