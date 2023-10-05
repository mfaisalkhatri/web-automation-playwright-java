package io.github.mfaisalkhatri.pages.theinternet;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FormAuthenticationPage {

    private final Page page;

    public FormAuthenticationPage(final Page page) {
        this.page = page;
    }

    public String pageHeader() {
        return this.page.locator("h2").innerHTML();
    }
    private Locator userNameField() {
        return this.page.getByLabel("Username");
    }

    private Locator passwordField() {
        return this.page.getByLabel("Password");
    }

    private Locator loginBtn() {
        return this.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    }

    public SecureAreaPage performLogin(final String userName, final String password) {
        userNameField().clear();
        userNameField().fill(userName);
        passwordField().clear();
        passwordField().fill(password);
        loginBtn().click();
        return new SecureAreaPage(this.page);
    }
}
