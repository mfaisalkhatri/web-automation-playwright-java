package io.github.mfaisalkhatri.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClickOperationTests {

    private Playwright playwright;
    private Page       page;

    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create ();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
            .setChannel("chrome"));
        this.page = browser.newPage ();
    }

    @Test
    public void testLeftClick () {
        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Challenging DOM"))
            .click ();
        //Locator challenginDomLink = page.getByRole (AriaRole.LINK,
        //  new Page.GetByRoleOptions ().setName ("Challenging DOM"));
        //challenginDomLink.click ();

        assertThat (
            this.page.getByRole (AriaRole.HEADING, new Page.GetByRoleOptions ().setName ("Challenging DOM"))).isVisible ();
    }

    @Test
    public void testRightClick () {
        this.page.navigate ("https://the-internet.herokuapp.com/context_menu");
        this.page.locator ("#hot-spot")
            .click (new Locator.ClickOptions ().setButton (MouseButton.RIGHT));
        this.page.onDialog (dialog -> {
            String alertText = dialog.message ();

            assert alertText.equals ("You selected a context menu");
            dialog.accept ();
        });
    }

    @AfterClass
    public void tearDown() {
        this.page.close ();
        this.playwright.close ();
    }
}
