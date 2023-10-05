package io.github.mfaisalkhatri.pages.theinternet;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SecureAreaPage {

    private final Page page;

    public SecureAreaPage(final Page page) {
        this.page = page;
    }

    public String pageHeader() {
        return this.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Secure Area").setExact(true)).textContent();
    }

    public String successMessage() {
        return this.page.locator("#flash").textContent();
    }

    public String pageSubHeader () {
        return this.page.locator("h4.subheader").innerText();
    }

    public Locator logoutBtn() {
        return this.page.locator("a.button");
    }

    public void logoutFromWebsite() {
        logoutBtn().click();
    }
}
