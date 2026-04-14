package io.github.mfaisalkhatri.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropdownTests {

    private Playwright playwright;
    private Page page;


    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void testSelectByOption() {

        this.page.navigate("https://the-internet.herokuapp.com/dropdown");
        final Locator dropdownField = this.page.locator("#dropdown");

        dropdownField.selectOption("Option 2");

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 2");

    }

    @Test
    public void testSelectByLabel() {

        this.page.navigate("https://the-internet.herokuapp.com/dropdown");
        final Locator dropdownField = this.page.locator("#dropdown");

        dropdownField.selectOption(new SelectOption().setLabel("Option 1"));

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 1");

    }

    @Test
    public void testSelectByIndex() {

        this.page.navigate("https://the-internet.herokuapp.com/dropdown");
        final Locator dropdownField = this.page.locator("#dropdown");

        dropdownField.selectOption(new SelectOption().setIndex(2));

        final String dropdownFieldSelectedValue = this.page.locator("#dropdown [selected='selected']").innerText();
        assertEquals(dropdownFieldSelectedValue, "Option 2");

    }
    @Test
    public void testSelectByValue() {

        this.page.navigate ("https://the-internet.herokuapp.com/dropdown");

        this.page.getByRole (AriaRole.COMBOBOX)
            .selectOption ("Option 2");

        assertThat (page.getByRole (AriaRole.COMBOBOX)).containsText ("Option 2");
    }

    @Test
    public void testMultiSelectOptions() {
        this.page.navigate("https://testautomationpractice.blogspot.com/");

        final Locator dropdownField = this.page.getByRole (AriaRole.LISTBOX, new Page.GetByRoleOptions ().setName (
            "Sorted List:"));
        dropdownField.selectOption (new SelectOption [] {new SelectOption ().setLabel ("Cat"),
            new SelectOption ().setLabel ("Cheetah"), new SelectOption ().setIndex (2)});

        assertThat(dropdownField).hasValues (new Pattern[]{Pattern.compile ("cat"),
            Pattern.compile ("cheetah"), Pattern.compile ("deer")});
    }

    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }
}