package io.github.mfaisalkhatri.pages.theinternet;

import com.microsoft.playwright.Page;

public class MainPage {

    private final Page page;

    public MainPage(final Page page) {
        this.page = page;
    }

    public String getPageHeader() {
        return this.page.locator("h2").textContent();
    }

    public void clickLink(final String menuName) {
        this.page.getByText(menuName).click();
    }

}
