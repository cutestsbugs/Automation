package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.driver.DriverUtils;

public class HomePageElements extends DriverUtils {

        public WebElement getGetStartedButton() {
            return driver().findElement(By.xpath("//a[@data-qa = 'homepage-hero-btn']"));
        }

    public WebElement getSigInButton() {
        return driver().findElement(By.xpath("//a[@data-qa = 'header-link-signin']"));
    }
}
