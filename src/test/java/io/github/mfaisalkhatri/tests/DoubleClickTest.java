package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.mfaisalkhatri.pages.guru99demosite.ButtonsPage;
import org.testng.annotations.Test;

public class DoubleClickTest {


    @Test
    public void testDoubleClick () {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setChannel("chrome"));
        final Page page = browser.newPage();
        page.navigate("https://demo.guru99.com/test/simple_context_menu.html");

        final ButtonsPage buttonsPage = new ButtonsPage(page);
        buttonsPage.doubleClickAndCheckAlertText("You double clicked me.. Thank You..");

    }
}
