package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.driver.DriverUtils;

public class LoginPageElements extends DriverUtils {

    public WebElement getSignInInputFieldEmail() {
        return driver().findElement(By.xpath("//a[@data-qa = 'signin-inputfield-emailaddress']"));
    }

    public WebElement getSignInInputFieldPassword() {
        return driver().findElement(By.xpath("//a[@data-qa = 'signin-inputfield-password']"));
    }

    public WebElement getSignInButton() {
        return driver().findElement(By.xpath("//a[@data-qa = 'signin-btn-signin']"));
    }
}
