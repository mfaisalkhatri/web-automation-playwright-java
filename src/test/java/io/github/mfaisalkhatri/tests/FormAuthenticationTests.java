package io.github.mfaisalkhatri.tests;

import io.github.mfaisalkhatri.pages.theinternet.FormAuthenticationPage;
import io.github.mfaisalkhatri.pages.theinternet.MainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FormAuthenticationTests extends BaseTest {

    @Test
    public void testLoginFunctionality() {

        final String userName = "tomsmith";
        final String password = "SuperSecretPassword!";
        final var page = this.browserManager.getPage();
        page.navigate("https://the-internet.herokuapp.com/");

        final var mainPage = new MainPage(page);
        mainPage.clickLink("Form Authentication");

        final var formAuthenticationPage = new FormAuthenticationPage(page);
        assertEquals(formAuthenticationPage.pageHeader(), "Login Page");
        final var securePage = formAuthenticationPage.performLogin(userName, password);
        assertTrue(securePage.successMessage().contains("You logged into a secure area!"));
        assertEquals(securePage.pageHeader(), " Secure Area");
        assertEquals(securePage.pageSubHeader(), "Welcome to the Secure Area. When you are done click logout below.");

        securePage.logoutBtn().isEnabled();
        securePage.logoutFromWebsite();
    }
}
