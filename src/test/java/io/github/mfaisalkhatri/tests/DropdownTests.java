package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class DropdownTests {

    private Page page;
    private Locator dropdownField;

    @BeforeClass
    public void setup() {
        final Playwright playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        this.page = browser.newPage();
        this.page.navigate("https://the-internet.herokuapp.com/dropdown");
        this.dropdownField = this.page.locator("#dropdown");

    }

    @Test
    public void testSelectByOption() {

        this.dropdownField.selectOption("Option 2");

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 2");

    }

    @Test
    public void testSelectByLabel() {
        this.dropdownField.selectOption(new SelectOption().setLabel("Option 1"));

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 1");

    }

    @Test
    public void testSelectByIndex() {
        this.dropdownField.selectOption(new SelectOption().setIndex(2));

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 2");

    }

    @Test
    public void testSelectByValue() {
        this.page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");

        this.dropdownField.selectOption(new SelectOption().setValue("Option 1"));

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 1");
    }

    @Test
    public void testMultiSelectOptions() {
        this.page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");

        this.dropdownField = this.page.locator("#multi-select");
        this.dropdownField.selectOption(new SelectOption[]{new SelectOption().setLabel("New York"), new SelectOption().setLabel("Texas")});

        assertThat(this.dropdownField).hasValues(new Pattern[]{Pattern.compile("New York"), Pattern.compile("Texas")});
    }

}
