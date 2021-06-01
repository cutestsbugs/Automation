package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.driver.DriverUtils;

public class UserWebsiteElements extends DriverUtils {

    public WebElement getGalleryMenuLink() {
        return driver().findElement(By.xpath("//a[@href='/gallery']"));
    }

    public WebElement getBlogMenuLink() {
        return driver().findElement(By.xpath("//a[@href='/blog']"));
    }

    public WebElement getAboutMenuLink() {
        return driver().findElement(By.xpath("//a[@href='/about']"));
    }

    public WebElement getContactMenuLink() {
        return driver().findElement(By.xpath("//a[@href='/contact']"));
    }

    public WebElement getPostTitle() {
        return driver().findElement(By.xpath("//div[@class = 'block-blog-list-item__content']"));
    }
    public WebElement getBuilderGridElement() {
        return driver().findElement(By.xpath("//img[@data-qa = 'builder-gridelement-image']"));
    }

}
