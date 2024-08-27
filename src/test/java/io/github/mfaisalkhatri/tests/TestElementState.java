package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestElementState {

    private Playwright playwright;
    private Page page;


    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void testElementIsDisabled() {
        page.navigate("https://the-internet.herokuapp.com/jqueryui/menu#");
        Locator disabledMenu = page.locator("#ui-id-1 > a");
        assertThat(disabledMenu).isDisabled();
    }

    @Test
    public void testElementIsEnabled() {
        page.navigate("https://the-internet.herokuapp.com/jqueryui/menu#");
        Locator enabledMenu = page.locator("#ui-id-3 > a");
        assertThat(enabledMenu).isEnabled();
    }

    @Test
    public void testElementIsDisplayed() {
        page.navigate("https://www.lambdatest.com/selenium-playground/");

        Locator radioButtonLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Radio Buttons Demo"));
        radioButtonLink.click();
        Locator pageHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Radio button Demo"));
        assertThat(pageHeading).isVisible();
        assertThat(pageHeading).equals("Radio button Demo");
    }

    @Test
    public void testElementIsSelected() {
        page.navigate("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        Locator maleRadioButton = page.getByLabel("Male").first();
        maleRadioButton.click();
        assertThat(maleRadioButton).isChecked();
    }

    @Test
    public void testRadioButtonIsDisabled() {
        page.navigate("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        Locator disabledRadioButton = page.getByLabel("Disabled Radio Button").first();
        assertThat(disabledRadioButton).isDisabled();
    }
    @Test
    public void testTextBoxIsEditable() {
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        Locator enterMessagField = page.getByPlaceholder("Please enter your Message");
        assertThat(enterMessagField).isEditable();
    }

    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }

}
