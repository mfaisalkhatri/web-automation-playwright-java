package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MouseHoverTest {

    private Playwright playwright;
    private Page page;

    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void testMouseHover() {
        page.navigate("https://the-internet.herokuapp.com/hovers");
        Locator firstImage = page.locator("#content div.figure:nth-child(3)");
        firstImage.hover();
        Locator userNameText = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("name: user1"));
        assertThat(userNameText).hasText("name: user1");
    }

    @Test
    public void testMouseHoverOnMyAccountLink() {
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccountLink = page.locator("#widget-navbar-217834 > ul > li:nth-child(6) > a");
        myAccountLink.hover();
        Locator loginLink = page.locator("#widget-navbar-217834 > ul > li:nth-child(6) > ul > li:nth-child(1) > a");
        loginLink.click();
        Locator pageHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Returning Customer"));
        assertThat(pageHeader).hasText("Returning Customer");
    }

    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }
}