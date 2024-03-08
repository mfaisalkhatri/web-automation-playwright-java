package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlaywrightDemoTests {
    @Test
    public void testOnChromeHeadless() {

        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch();
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();
    }


    @Test
    public void testOnChrome() {

        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();
    }

    @Test
    public void testOnFirefoxHeadless() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.firefox().launch();
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();
    }

    @Test
    public void testOnFirefox() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();

    }

    @Test
    public void testOnFirefoxSlowMo() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();
    }

    @Test
    public void testOnChromeSlowMo() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        final Page page = browser.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
        browser.close();
    }

        @Test
        public void testOnEdge() {
            final Playwright playwright = Playwright.create();
            final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
                final Page page = browser.newPage();
                page.navigate("https://ecommerce-playground.lambdatest.io/");
                final String pageTitle = page.title();
                assertEquals(pageTitle, "Your Store");
                browser.close();
        }

    @Test
    public void testBrowserNavigation() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        final Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/");

        final String homePageHeader = page.locator("h2").textContent();
        assertEquals(homePageHeader, "Available Examples");

        page.navigate("https://the-internet.herokuapp.com/challenging_dom");
        final String challengingDomPageHeader = page.locator("h3").textContent();
        assertEquals(challengingDomPageHeader, "Challenging DOM");

        page.goBack();
        // page.goBack(new Page.GoBackOptions().setWaitUntil(WaitUntilState.COMMIT));
        // page.goBack(new Page.GoBackOptions().setTimeout(50));
        assertEquals(homePageHeader, "Available Examples");

        page.goForward();
        // page.goForward(new Page.GoForwardOptions().setWaitUntil(WaitUntilState.LOAD));
        //page.goForward(new Page.GoForwardOptions().setTimeout(40));
        assertEquals(challengingDomPageHeader, "Challenging DOM");

        page.reload();
        // page.reload(new Page.ReloadOptions().setTimeout(60));
        //page.reload(new Page.ReloadOptions().setWaitUntil(WaitUntilState.COMMIT));
        assertEquals(challengingDomPageHeader, "Challenging DOM");

        final String currentPageUrl = page.url();
        final String websiteLink = "https://the-internet.herokuapp.com/challenging_dom";
        assertEquals(currentPageUrl, websiteLink);

        browser.close();

    }
}

