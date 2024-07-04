package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextFieldTest {

    private Playwright playwright;
    private Page page;


    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void locateFirstNameByLabel() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator firstNameField = page.getByLabel("First name");
        firstNameField.fill("Faisal");
    }

    @Test
    public void locateFirstNameByPlaceholder() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator firstNameField = page.getByPlaceholder("Your first name *");
        firstNameField.fill("John");
    }
}
