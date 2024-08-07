package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

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

        this.page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
        final Locator dropdownField = this.page.locator("#select-demo");

        dropdownField.selectOption(new SelectOption().setValue("Wednesday"));

        final Locator daySelected = this.page.locator(".pt-10 p");
        assertThat(daySelected).hasText("Day selected :- Wednesday");
    }

    @Test
    public void testMultiSelectOptions() {
        this.page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");

        final Locator dropdownField = this.page.locator("#multi-select");
        dropdownField.selectOption(new SelectOption[]{new SelectOption().setLabel("New York"), new SelectOption().setLabel("Texas"), new SelectOption().setValue("California"), new SelectOption().setIndex(7)});

        assertThat(dropdownField).hasValues(new Pattern[]{Pattern.compile("California"), Pattern.compile("New York"), Pattern.compile("Texas"), Pattern.compile("Washington")});
    }

    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }
}