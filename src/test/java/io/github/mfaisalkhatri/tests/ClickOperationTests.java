package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ClickOperationTests {

    private Playwright playwright;
    private Page       page;

    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create ();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
            .setChannel("chrome"));
        this.page = browser.newPage ();
    }

    


    @AfterClass
    public void tearDown() {
        this.page.close ();
        this.playwright.close ();
    }
}
