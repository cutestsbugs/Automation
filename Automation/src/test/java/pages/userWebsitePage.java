package pages;

import elements.UserWebsiteElements;

import static utils.ComponentUtils.clickElement;
import static utils.ComponentUtils.isElementDisplayed;
import static utils.WaitUtils.waitForUrlToContain;

public class userWebsitePage {

    private final UserWebsiteElements userwebsite = new UserWebsiteElements();

    public void clickMenu(String menuName) {

        if (menuName.toLowerCase().contains("blog")) {
            clickElement(userwebsite.getBlogMenuLink());
            waitForUrlToContain("/blog");
            isElementDisplayed(userwebsite.getPostTitle());
        }

        if (menuName.toLowerCase().contains("gallery")) {
            clickElement(userwebsite.getGalleryMenuLink());
            waitForUrlToContain("/gallery");
            isElementDisplayed(userwebsite.getBuilderGridElement());
        }

        if (menuName.toLowerCase().contains("about")) {
            clickElement(userwebsite.getAboutMenuLink());
            waitForUrlToContain("/about");
            isElementDisplayed(userwebsite.getBuilderGridElement());
        }

        if (menuName.toLowerCase().contains("contact")) {
            clickElement(userwebsite.getContactMenuLink());
            waitForUrlToContain("/contact");
        }

    }
}