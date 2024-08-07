package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RadioButtonTests {

    private Playwright playwright;
    private Page page;


    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void testRadioButtonIsChecked() {
        page.navigate("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        Locator maleRadioBtn = page.getByLabel("Male").first();
        maleRadioBtn.click();
        assertThat(maleRadioBtn).isChecked();
    }

    @Test
    public void testRadioButtonIsNotChecked() {
        page.navigate("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        Locator femaleRadioBtn = page.getByLabel("Female").first();
        femaleRadioBtn.click();
        assertThat(femaleRadioBtn).isChecked();

        Locator maleRadioBtn = page.getByLabel("Male").first();
        assertThat(maleRadioBtn).not().isChecked();
    }


    @AfterClass
    public void tearDown() {
        this.page.close();
        this.playwright.close();
    }

}
