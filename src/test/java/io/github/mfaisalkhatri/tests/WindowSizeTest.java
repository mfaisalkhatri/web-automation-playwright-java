package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import io.github.mfaisalkhatri.browsers.BrowserManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WindowSizeTest {

    @Test
    public void testDifferentWindowSize() {

        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setChannel("chrome"));
        final Page page = browser.newPage();
        page.setViewportSize(1024,768);
        page.navigate("https://www.lambdatest.com/selenium-playground/");

        final Locator pageHeader = page.locator(".wrapper h1");
        final String pageHeaderText = pageHeader.textContent();
        assertEquals(pageHeaderText, "Selenium Playground");
        browser.close();

    }

    @Test
    public void testDifferentWindowSizeMethodTwo() {

        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setChannel("chrome"));

        final BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(600,800));
        final Page page = context.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/");

        final Locator pageHeader = page.locator(".wrapper h1");
        final String pageHeaderText = pageHeader.textContent();
        assertEquals(pageHeaderText, "Selenium Playground");
        browser.close();

    }

}
