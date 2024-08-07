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

    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }

}
