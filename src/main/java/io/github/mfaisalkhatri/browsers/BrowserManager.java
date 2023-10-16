package io.github.mfaisalkhatri.browsers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {

    private final Playwright playwright;
    private Browser browser;
    private static final String HEADLESS = "headless";

    public BrowserManager() {
        this.playwright = Playwright.create();
    }

    public void createBrowser(final String browser) {

        switch (browser) {
            case "edge" -> setupEdgeBrowser();
            case "firefox" -> setupFirefoxBrowser();
            default -> setupChromeBrowser();
        }
    }

    private void setupChromeBrowser() {
        this.browser = this.playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty(HEADLESS))));
    }

    private void setupFirefoxBrowser() {
        this.browser = this.playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty(HEADLESS))));
    }

    private void setupEdgeBrowser() {
        this.browser = this.playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(System.getProperty(HEADLESS))).setChannel("msedge"));
    }

    public Page getPage() {
        return this.browser.newPage();
    }


    public void closeBrowser() {
        this.browser.close();
    }


}
