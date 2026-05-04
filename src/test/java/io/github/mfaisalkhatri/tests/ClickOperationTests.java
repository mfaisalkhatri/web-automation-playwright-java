package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class ClickOperationTests {

    private Playwright playwright;
    private Page       page;

    @BeforeMethod
    public void setup () {
        this.playwright = Playwright.create ();
        Browser browser = this.playwright.chromium ()
            .launch (new BrowserType.LaunchOptions ().setHeadless (false)
                .setChannel ("chrome"));
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

        assertThat (this.page.getByRole (AriaRole.HEADING,
            new Page.GetByRoleOptions ().setName ("Challenging DOM"))).isVisible ();
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

    @Test
    public void testDoubleClick () {
        this.page.navigate ("https://demo.guru99.com/test/simple_context_menu.html");

        Locator alertBtn = this.page.locator ("#authentication > button");
        this.page.onDialog (dialog -> {
            String text = "You double clicked me.. Thank You..";
            assertEquals (dialog.message (), text);
            dialog.accept ();
        });
        alertBtn.dblclick ();
    }

    @Test
    public void testMouseHover () {
        this.page.navigate ("https://automationteststore.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("  Apparel & accessories"))
            .hover ();
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Shoes")
                .setExact (true))
            .click ();
        assertThat (
            this.page.getByRole (AriaRole.HEADING, new Page.GetByRoleOptions ().setName ("Shoes"))).isVisible ();
    }

    @Test
    public void testForceMouseClick () {
        this.page.navigate ("https://automationteststore.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Specials"))
            .click (new Locator.ClickOptions ().setForce (true));
        assertThat (this.page.getByRole (AriaRole.HEADING,
            new Page.GetByRoleOptions ().setName ("Special Offers"))).isVisible ();
    }

    @Test
    public void testPositionBasedClick () {
        this.page.navigate ("https://the-internet.herokuapp.com/horizontal_slider");
        Locator slider = this.page.getByRole (AriaRole.SLIDER);
        BoundingBox box = slider.boundingBox ();
        double width = box.width;
        double height = box.height;

        double x = width * 0.5;
        double y = height * 0.5;

        slider.click (new Locator.ClickOptions ().setPosition (x, y));
        this.page.waitForTimeout (3000);

    }

    @Test
    public void testClickWithModifiers () {
        this.page.navigate ("https://jqueryui.com/selectable/");
        FrameLocator frame = this.page.frameLocator (".demo-frame");

        Locator items = frame.locator ("#selectable li");

        items.nth (0)
            .click (new Locator.ClickOptions ().setModifiers (List.of (KeyboardModifier.CONTROL)));

        items.nth (3)
            .click (new Locator.ClickOptions ().setModifiers (List.of (KeyboardModifier.CONTROL)));

        this.page.waitForTimeout (2000);

        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("File Download")
                .setExact (true))
            .click (new Locator.ClickOptions ().setModifiers (List.of (KeyboardModifier.SHIFT)));

        this.page.waitForTimeout (2000);

    }

    @Test
    public void testDelayClick () {
        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Challenging DOM"))
            .click (new Locator.ClickOptions ().setDelay (2000));
        assertThat (this.page.getByRole (AriaRole.HEADING,
            new Page.GetByRoleOptions ().setName ("Challenging DOM"))).isVisible ();
    }

    @Test
    public void testDragAndDropActions () {
        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Drag and Drop"))
            .click ();
        this.page.locator ("#column-a")
            .dragTo (this.page.locator ("#column-b"));
        assertThat (this.page.locator ("#column-a")).containsText ("B");

    }

    @Test
    public void testDragAndDropManually () {
        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Drag and Drop"))
            .click ();
        this.page.locator ("#column-a")
            .hover ();
        this.page.mouse ()
            .down ();
        this.page.locator ("#column-b")
            .hover ();
        this.page.mouse ()
            .up ();
        assertThat (this.page.locator ("#column-a")).containsText ("B");
    }

    @Test
    public void testProgrammaticClick () {
        this.page.navigate ("https://the-internet.herokuapp.com/");
        this.page.getByRole (AriaRole.LINK, new Page.GetByRoleOptions ().setName ("Checkboxes"))
            .dispatchEvent ("click");

        assertThat (this.page.getByRole (AriaRole.HEADING)).containsText ("Checkboxes");

    }

    @AfterMethod
    public void tearDown () {
        this.page.close ();
        this.playwright.close ();
    }
}