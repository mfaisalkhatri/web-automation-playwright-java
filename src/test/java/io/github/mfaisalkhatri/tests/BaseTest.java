package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.browsers.BrowserManager;
import io.github.mfaisalkhatri.browsers.Browsers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {


    BrowserManager browserManager;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupTest(final String browser) {
        this.browserManager = new BrowserManager();
        this.browserManager.createBrowser(Browsers.valueOf(browser.toUpperCase()));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.browserManager.closeBrowser();
    }
}

