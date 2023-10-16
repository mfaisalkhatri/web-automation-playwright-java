package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.browsers.BrowserManager;
import org.testng.annotations.*;

public class BaseTest {


    BrowserManager browserManager;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupTest(final String browser) {
        this.browserManager = new BrowserManager();
        this.browserManager.createBrowser((browser));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.browserManager.closeBrowser();
    }
}

