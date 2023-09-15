package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Page;
import io.github.mfaisalkhatri.pages.theinternet.ChallengingDomPage;
import io.github.mfaisalkhatri.pages.theinternet.MainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BrowserNavigationTests extends BaseTest {

    @Test
    public void testBrowserNavigation() {
        final Page page = this.browserManager.getPage();
        page.navigate("https://the-internet.herokuapp.com/");
        final MainPage mainPage = new MainPage(page);
        assertEquals(mainPage.getPageHeader(), "Available Examples");
        mainPage.clickLink("Challenging DOM");

        final ChallengingDomPage challengingDomPage = new ChallengingDomPage(page);
        assertEquals(challengingDomPage.getPageHeader(), "Challenging DOM");

        page.goBack();
        assertEquals(mainPage.getPageHeader(), "Available Examples");

        page.goForward();
        assertEquals(challengingDomPage.getPageHeader(), "Challenging DOM");

        page.reload();
        assertEquals(challengingDomPage.getPageHeader(), "Challenging DOM");

    }


    @Test
    public void testTitleAndCurrentUrl() {
        final String websiteLink = "https://the-internet.herokuapp.com/";
        final Page page = this.browserManager.getPage();
        page.navigate(websiteLink);
        assertEquals(page.url(), websiteLink);

        final String pageTitle = page.title();
        assertEquals(pageTitle, "The Internet");
    }

}
