package io.github.mfaisalkhatri.pages.theinternet;

import com.microsoft.playwright.Page;

public class ChallengingDomPage {

    private final  Page page;

    public ChallengingDomPage(final Page page) {
        this.page = page;
    }

    public String getPageHeader () {
        return this.page.locator("h3").textContent();
    }

}
