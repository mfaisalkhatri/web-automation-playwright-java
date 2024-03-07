package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
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
        //page.goBack(new Page.GoBackOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
       // page.goBack(new Page.GoBackOptions().setTimeout(50));
        assertEquals(mainPage.getPageHeader(), "Available Examples");

        page.goForward();
        //page.goForward(new Page.GoForwardOptions().setWaitUntil(WaitUntilState.LOAD));
        //page.goForward(new Page.GoForwardOptions().setTimeout(40));
        assertEquals(challengingDomPage.getPageHeader(), "Challenging DOM");

        page.reload();
        //page.reload(new Page.ReloadOptions().setTimeout(60));
        //page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.COMMIT));
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
