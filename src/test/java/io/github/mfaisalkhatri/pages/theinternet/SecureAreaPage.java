package io.github.mfaisalkhatri.pages.theinternet;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SecureAreaPage {

    private final Page page;

    public SecureAreaPage(final Page page) {
        this.page = page;
    }

    public String pageHeader() {
        return this.page.getByText("Secure Area").textContent();
    }

    public String successMessage() {
        return this.page.locator("#flash").textContent();
    }

    public String pageSubHeader () {
        return this.page.locator("h4.subheader").innerText();
    }

    private Locator logoutBtn() {
        return this.page.locator("a.button");
    }

    public void logoutFromWebsite() {
        logoutBtn().click();
    }
}
