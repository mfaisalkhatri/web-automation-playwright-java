package io.github.mfaisalkhatri;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlaywrightDemoTests {

    @Test
    public void testOnChromeHeadless() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.chromium().launch();
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnChrome() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnFirefoxHeadless() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.firefox().launch();
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnFirefox() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnFirefoxSlowMo() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnChromeSlowMo() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

    @Test
    public void testOnEdge() {
        try (final Playwright playwright = Playwright.create()) {
            final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
            final Page page = browser.newPage();
            page.navigate("https://ecommerce-playground.lambdatest.io/");
            final String pageTitle = page.title();
            assertEquals(pageTitle, "Your Store");
            browser.close();
        }
    }

}
